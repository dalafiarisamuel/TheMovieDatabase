package com.devtamuno.themoviedatabase.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.devtamuno.themoviedatabase.ui.data.Movie
import com.devtamuno.themoviedatabase.ui.theme.ColorMatteBlack


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieComponent(
    modifier: Modifier,
    movie: Movie?,
    onMovieClicked: (Movie?) -> Unit,
) {

    val isShowProgress by remember { mutableStateOf(MutableTransitionState(true)) }
    val painter = rememberAsyncImagePainter(movie?.posterPath)

    when (painter.state) {
        is AsyncImagePainter.State.Loading,
        is AsyncImagePainter.State.Empty -> { /*default state*/
        }
        is AsyncImagePainter.State.Error,
        is AsyncImagePainter.State.Success -> {
            isShowProgress.targetState = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {

        Card(
            elevation = 4.dp,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .combinedClickable(onClick = { onMovieClicked(movie) })

        ) {
            Image(
                painter = painter,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp)
            )
        }

        AnimatedVisibility(
            modifier = Modifier.align(Alignment.Center),
            visibleState = isShowProgress
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(35.dp)
                    .align(Alignment.Center),
                color = ColorMatteBlack
            )
        }

    }
}