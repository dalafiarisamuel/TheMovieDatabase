package com.devtamuno.themoviedatabase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.devtamuno.themoviedatabase.ui.screen.AppScreen
import com.devtamuno.themoviedatabase.ui.theme.TheMovieDatabaseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent { TheMovieDatabaseTheme { AppScreen() } }
    }
}
