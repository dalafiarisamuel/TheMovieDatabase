package com.devtamuno.themoviedatabase.ui.tools

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.devtamuno.themoviedatabase.ui.theme.TheMovieDatabaseTheme


fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.getString(@StringRes resId: Int) =
    activity.getString(resId)

fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.getString(
    @StringRes resId: Int,
    vararg formatArgs: Any
) = activity.getString(resId, *formatArgs)

fun ComposeContentTestRule.setTestContent(content: @Composable ColumnScope.() -> Unit) =
    setContent {
        TheMovieDatabaseTheme {
            Column(modifier = Modifier.fillMaxSize()) {
                content()
            }
        }
    }
