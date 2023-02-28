package com.devtamuno.themoviedatabase.tools

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalCoroutinesApi::class)
fun <T> Flow<T>.test(): List<T> {
    val values = mutableListOf<T>()
    this
        .onEach(values::add)
        .launchIn(CoroutineScope(TestDispatchers.main))
    return values
}
