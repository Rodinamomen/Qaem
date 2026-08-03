package com.app.qaem.common.ui.eventcontoller

import kotlinx.coroutines.flow.Flow

interface IEventController<Event> {
    val event: Flow<Event>
    suspend fun emit(event: Event)
}