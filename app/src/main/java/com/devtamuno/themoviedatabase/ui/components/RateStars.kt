package com.devtamuno.themoviedatabase.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.devtamuno.themoviedatabase.R

@Composable
fun RateStars(voteAverage: Double, modifier: Modifier) {

    Row(modifier) {
        val maxVote = 10
        val starCount = 5
        repeat(starCount) { starIndex ->
            val voteStarCount = voteAverage / (maxVote / starCount)
            val asset = when {
                voteStarCount >= starIndex + 1 -> painterResource(id = R.drawable.round_star_rate)
                voteStarCount in starIndex.toDouble()..(starIndex + 1).toDouble() -> painterResource(
                    id = R.drawable.baseline_star_half
                )
                else -> painterResource(id = R.drawable.round_star_outline)
            }
            Icon(painter = asset, contentDescription = null, tint = Color(0xFFEDD94C))
            Spacer(modifier = Modifier.width(4.dp))
        }
    }

}