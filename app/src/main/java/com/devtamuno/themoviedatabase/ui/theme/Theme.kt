package com.devtamuno.themoviedatabase.ui.theme

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

private val DarkColorPalette = darkColors(
    primary = ColorWhite,
    primaryVariant = ColorWhite,
    secondary = ColorMatteBlack
)

private val LightColorPalette = lightColors(
    primary = ColorMatteBlack,
    primaryVariant = ColorMatteBlack,
    secondary = ColorWhite
)

val LocalMovieDimens = staticCompositionLocalOf { movieDimens }

val LocalNavController =
    compositionLocalOf<NavHostController> { error("Nav controller isn't present") }

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TheMovieDatabaseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes
    ) {
        CompositionLocalProvider(
            LocalOverscrollConfiguration provides null,
            LocalMovieDimens provides movieDimens,
            LocalNavController provides rememberNavController(),
            content = content
        )

    }
}

object TheMovieDatabaseTheme {
    val dimens: Dimens
        @Composable
        get() = LocalMovieDimens.current
}

@Stable
@Immutable
data class Dimens(
    val standard25: Dp,
    val standard50: Dp,
    val standard75: Dp,
    val standard100: Dp,
    val standard125: Dp,
    val standard150: Dp,
    val standard175: Dp,
    val standard200: Dp,
    val standard225: Dp,
    val standard250: Dp,
    val standard275: Dp,
    val standard300: Dp,
    val standard400: Dp,
    val standard500: Dp,
    val standard600: Dp,
    val standard700: Dp,
    val standard800: Dp,
)

private val movieDimens = Dimens(
    standard25 = 4.dp,
    standard50 = 8.dp,
    standard75 = 12.dp,
    standard100 = 16.dp,
    standard125 = 20.dp,
    standard150 = 24.dp,
    standard175 = 28.dp,
    standard200 = 32.dp,
    standard225 = 36.dp,
    standard250 = 40.dp,
    standard275 = 44.dp,
    standard300 = 48.dp,
    standard400 = 64.dp,
    standard500 = 80.dp,
    standard600 = 96.dp,
    standard700 = 112.dp,
    standard800 = 128.dp,
)