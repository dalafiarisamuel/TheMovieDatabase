package com.devtamuno.themoviedatabase.tools

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@OptIn(ExperimentalCoroutinesApi::class)
object TestDispatchers {
    val io = UnconfinedTestDispatcher()
    val main = UnconfinedTestDispatcher()
    val default = UnconfinedTestDispatcher()
}
