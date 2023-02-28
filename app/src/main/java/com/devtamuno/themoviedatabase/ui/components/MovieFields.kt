package com.devtamuno.themoviedatabase.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devtamuno.themoviedatabase.R
import com.devtamuno.themoviedatabase.ui.data.MovieDetail
import com.devtamuno.themoviedatabase.ui.theme.appWhite

@Composable
fun MovieFields(movieDetail: MovieDetail, modifier: Modifier) {
    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = modifier) {

        MovieField(stringResource(R.string.release_date), movieDetail.releaseDate)
        MovieField(
            stringResource(R.string.duration),
            stringResource(R.string.duration_minutes, movieDetail.runtime.toString())
        )
        MovieField(stringResource(R.string.vote_average), movieDetail.voteAverage.toString())
        MovieField(stringResource(R.string.votes), movieDetail.voteCount.toString())
    }
}

@Preview
@Composable
private fun MovieField(name: String = "Release Date", value: String = "12-12-2023") {
    Column {
        Text(
            text = name,
            color = appWhite,
            fontSize = 13.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = value,
            color = appWhite,
            fontSize = 12.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 4.dp)
        )
    }
}