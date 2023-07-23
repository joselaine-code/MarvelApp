package com.joselaine.marvelapp.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.joselaine.marvelapp.R
import com.joselaine.marvelapp.presentation.ui.theme.RedMarvel

@Composable
fun MarvelTopBar() {
    val topAppBarHeight = 56.dp
    val backgroundColor = RedMarvel
    val elevation = 4.dp
    val logoSize = 120.dp

    TopAppBar(
        modifier = Modifier.height(topAppBarHeight),
        backgroundColor = backgroundColor,
        elevation = elevation
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.marvel_logo),
                contentDescription = "Marvel Logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(logoSize)
            )
        }
    }
}