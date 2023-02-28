package com.devtamuno.themoviedatabase.ui.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.devtamuno.themoviedatabase.R
import com.devtamuno.themoviedatabase.ui.data.Movie
import com.devtamuno.themoviedatabase.ui.theme.TheMovieDatabaseTheme
import com.devtamuno.themoviedatabase.ui.theme.appWhite


@Composable
fun MovieDisplayBottomSheet(
    modifier: Modifier = Modifier,
    movie: Movie,
    onMovieClicked: (Movie) -> Unit = {}
) {

    Column(
        modifier = modifier
            .padding(
                vertical = TheMovieDatabaseTheme.dimens.standard125,
                horizontal = TheMovieDatabaseTheme.dimens.standard100
            )
            .fillMaxWidth()
            .wrapContentHeight()
            .verticalScroll(rememberScrollState())

    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {

            PosterImage(
                movie = movie,
                onMovieClicked = onMovieClicked
            )

            Text(
                text = movie.name,
                color = appWhite,
                fontSize = 25.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(TheMovieDatabaseTheme.dimens.standard75)
                    .align(Alignment.CenterVertically)
            )

        }

        Text(
            text = stringResource(id = R.string.overview),
            color = appWhite,
            fontSize = 13.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = TheMovieDatabaseTheme.dimens.standard100)
        )

        Text(
            text = movie.overview,
            color = appWhite,
            fontSize = 16.sp,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            softWrap = true,
            fontStyle = FontStyle.Normal,
            modifier = Modifier.align(Alignment.Start)

        )

    }

}

@Composable
private fun PosterImage(
    modifier: Modifier = Modifier,
    movie: Movie,
    onMovieClicked: (Movie) -> Unit,
) {

    Box(
        modifier = modifier
            .wrapContentSize()
    ) {

        Card(
            elevation = 4.dp,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.wrapContentSize()
        ) {
            Image(
                painter = rememberAsyncImagePainter(movie.posterPath),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .height(210.dp)
                    .width(150.dp)
                    .clickable { onMovieClicked.invoke(movie) }
            )
        }

        if (movie.isRatingUpto8()) {
            Image(
                painter = painterResource(id = R.drawable.round_stars),
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp)
                    .testTag("rating")
                    .align(Alignment.Center)
            )
        }

    }
}