package com.devtamuno.themoviedatabase.ui.data.mapper

interface UiMapper<In, Out> {
    fun mapToUi(input: In): Out
}
