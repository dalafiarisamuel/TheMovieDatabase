package com.devtamuno.themoviedatabase.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.devtamuno.themoviedatabase.ui.theme.ColorMatteBlack

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MoviePoster(modifier: Modifier, posterUrl: String) {
    val isScaled = remember { mutableStateOf(false) }
    val isShowProgress by remember { mutableStateOf(MutableTransitionState(true)) }
    val scale =
        animateFloatAsState(
            targetValue = if (isScaled.value) 1.2f else 1f, animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow,
                visibilityThreshold = 0.001f
            )
        ).value


    val painter = rememberAsyncImagePainter(posterUrl)

    if (painter.state is AsyncImagePainter.State.Success) {
        isShowProgress.targetState = false
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {

        Card(
            elevation = 24.dp,
            shape = RoundedCornerShape(8.dp),
            modifier = modifier.scale(scale),
            onClick = { isScaled.value = !isScaled.value }
        ) {
            Image(
                painter = painter,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .width(350.dp)
                    .height(500.dp)
            )
        }

        AnimatedVisibility(
            modifier = Modifier.align(Alignment.Center),
            visibleState = isShowProgress,
            enter = fadeIn(initialAlpha = 0.4f),
            exit = fadeOut(tween(durationMillis = 250))

        ) { CircularProgressIndicator(color = ColorMatteBlack) }

    }


}