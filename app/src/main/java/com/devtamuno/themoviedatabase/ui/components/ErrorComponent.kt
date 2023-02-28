package com.devtamuno.themoviedatabase.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devtamuno.themoviedatabase.ui.theme.TheMovieDatabaseTheme
import com.devtamuno.themoviedatabase.ui.theme.appWhite

@Composable
fun ErrorComponent(modifier: Modifier = Modifier, message: String) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Rounded.Warning,
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .padding(top = 16.dp),
            tint = appWhite
        )
        Text(
            text = message,
            color = appWhite,
            modifier = Modifier.padding(top = TheMovieDatabaseTheme.dimens.standard75)
        )

    }
}

@Preview
@Composable
private fun ErrorComponentPreview() {
    ErrorComponent(message = "An error occurred")
}
