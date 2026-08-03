package com.app.qaem.common.ui.language

interface ILanguageEvent {
    val languageCode: String

    data class ChangeLanguage(override val languageCode: String) : ILanguageEvent
}