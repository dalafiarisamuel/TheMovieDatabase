package com.devtamuno.themoviedatabase.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color

//light theme colors
val ColorWhite = Color(0xFFFFFFFF)


// dark theme colors
val ColorMatteBlack = Color(0xFF212121)

val String.color
    get() = Color(android.graphics.Color.parseColor(this))


val appWhite
    @Composable
    @ReadOnlyComposable
    get() = if (isSystemInDarkTheme()) ColorWhite else ColorMatteBlack

val appDark
    @Composable
    @ReadOnlyComposable
    get() = if (isSystemInDarkTheme()) ColorMatteBlack else ColorWhite
