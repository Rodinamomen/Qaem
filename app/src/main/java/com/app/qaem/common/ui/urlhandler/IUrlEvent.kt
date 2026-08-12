package com.app.qaem.common.ui.urlhandler

sealed interface IUrlEvent {
    val url: String

    data class OpenUrl(override val url: String) : IUrlEvent
}