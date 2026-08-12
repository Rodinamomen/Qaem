package com.app.qaem.common.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptionsBuilder
import com.app.qaem.R
import com.app.qaem.common.data.model.QaemException
import com.app.qaem.common.domain.Resource
import com.app.qaem.common.domain.model.IErrorKey
import com.app.qaem.common.domain.model.RequestErrorKeyValues
import com.app.qaem.common.ui.eventcontoller.IEventController
import com.app.qaem.common.ui.extension.UIText
import com.app.qaem.common.ui.language.ILanguageEvent
import com.app.qaem.common.ui.loading.ILoadingEvent
import com.app.qaem.common.ui.message.IMessageEvent
import com.app.qaem.common.ui.message.MessageType
import com.app.qaem.common.ui.navigation.IDestination
import com.app.qaem.common.ui.navigation.INavigator
import com.app.qaem.common.ui.urlhandler.IUrlEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

abstract class BaseVM<State, Action>(state: State) : ViewModel(), KoinComponent {
    /** Because state flow always has a state even if the flow emits to it on the background stateflow will hold the latest value always
    // Collect as StateWithLifeCycle collects the data not in the background
    //With a regular Flow the value is lost:
    //Flow emits value
    //UI is in background → collectAsStateWithLifecycle stopped collecting
    //Nobody is listening → emission is gone
    //UI comes to foreground → gets NEXT emission, missed the previous one
    //With StateFlow it is never lost:
    //Flow emits value → StateFlow stores it
    //UI is in background → collectAsStateWithLifecycle stopped collecting
    //StateFlow holds the value waiting
    //UI comes to foreground → StateFlow delivers the stored value immediately
    App in background:
    Flow emits → StateFlow receives and STORES the latest value
    UI is not collecting → nobody reads it yet, but value is SAFE in StateFlow
    App comes to foreground:
    collectAsStateWithLifecycle resumes → reads the stored value from StateFlow
    Value is never lost as long as it's the latest
    But if multiple values emitted in background, only last one survives
     */
    private val _state = MutableStateFlow(state)
    val state = _state.asStateFlow()
    private val navigator: INavigator by inject()
    private val messageEvent: IEventController<IMessageEvent> by inject(named("MessageEvent"))
    private val loadingEvent: IEventController<ILoadingEvent> by inject(named("LoadingEvent"))
    private val languageEvent: IEventController<ILanguageEvent> by inject(named("LanguageEvent"))
    private val urlEvent: IEventController<IUrlEvent> by inject(named("UrlEvent"))
    abstract fun onActionTrigger(action: Action)
    fun updateState(update: State.() -> State) {
        _state.update { it.update() }
    }

    fun fireLoading(loadingType: ILoadingEvent) = viewModelScope.launch { loadingEvent.emit(loadingType) }

    fun fireMessage(messageType: IMessageEvent) = viewModelScope.launch { messageEvent.emit(messageType) }

    fun fireNavigate(destination: IDestination, builder: NavOptionsBuilder.() -> Unit = {}) =
        viewModelScope.launch { navigator.navigate(destination, builder = builder) }

    suspend fun fireNavigateUp() {
        navigator.navigateUp()
    }

    fun fireLanguageEvent(language: String) {
        viewModelScope.launch {
            languageEvent.emit(ILanguageEvent.ChangeLanguage(language))
        }
    }

    fun fireUrlEvent(url: String) {
        viewModelScope.launch {
            urlEvent.emit(IUrlEvent.OpenUrl(url))
        }
    }

    fun <Result> Flow<Resource<Result>>.collectResource(
        onSuccess: suspend (Result) -> Unit = {},
        onFailure: suspend (QaemException) -> Unit = {},
        onLoading: suspend (Boolean) -> Unit = {},
    ) = viewModelScope.launch {
        this@collectResource.collect { resource ->
            when (resource) {
                is Resource.Failure -> {
                    handleExceptions(resource.exception, ::onRequestValidation)
                    onFailure(resource.exception)
                }

                is Resource.Loading -> onLoading(resource.isLoading)
                is Resource.Success -> onSuccess(resource.model)
            }
        }
    }

    private fun handleExceptions(
        exception: QaemException,
        onRequestValidation: (Map<IErrorKey, UIText>) -> Unit = {},
    ) {
        when (exception) {
            is QaemException.Client.ResponseValidation -> onRequestValidation(
                exception.errors.mapValues { UIText.DynamicString(it.value) }
            )

            is QaemException.Client.Unhandled -> handleExceptionMessages(message = exception.message)

            is QaemException.Local.IOOperation -> handleExceptionMessages(message = exception.message)

            is QaemException.Local.RequestValidation -> {
                onRequestValidation(
                    exception.errors
                        .mapValues { requestErrorMap[it.value] ?: UIText.StringResource(R.string.unknown_error) }
                )
            }

            is QaemException.Local.Unhandled -> handleExceptionMessages(message = exception.message)

            is QaemException.Network.Repeatable -> handleExceptionMessages(message = exception.message)

            is QaemException.Network.Unhandled -> handleExceptionMessages(message = exception.message)

            is QaemException.Server.InternalServerError -> handleExceptionMessages(message = exception.message)
            is QaemException.Client.Unauthorized -> handleExceptionMessages(message = exception.message)
        }
    }

    open fun onRequestValidation(errors: Map<IErrorKey, UIText>) {}

    private fun handleExceptionMessages(message: String?) {
        Log.d("TAG", "handleExceptionMessages: $message")
        fireMessage(
            messageType = IMessageEvent.Snackbar(
                message = message?.let { UIText.DynamicString(it) }
                    ?: UIText.StringResource(R.string.something_wrong),
                messageType = MessageType.ERROR
            )
        )
    }

    companion object {
        private val requestErrorMap = mapOf<RequestErrorKeyValues, UIText>()
    }
}