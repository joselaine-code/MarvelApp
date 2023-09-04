package com.joselaine.marvelapp.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.joselaine.marvelapp.domain.models.MarvelCharacter
import com.joselaine.marvelapp.presentation.ui.theme.RedMarvel
import com.joselaine.marvelapp.presentation.viewmodels.DetailsViewModel

@Composable
fun MarvelDetails(
    viewModel: DetailsViewModel,
    data: MarvelCharacter,
) {
    val isFavoriteState by viewModel.isFavorite.collectAsState(initial = false)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CharacterImage(data.imageUrl)
        Spacer(modifier = Modifier.height(16.dp))
        FavoriteButton(isFavoriteState, viewModel)
        CharacterName(data.name)
        CharacterDescription(data.description)
    }
}

@Composable
private fun CharacterImage(imageUrl: String) {
    val imagePainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
    )
    Image(
        painter = imagePainter,
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    )
}

@Composable
private fun FavoriteButton(isFavorite: Boolean, viewModel: DetailsViewModel) {
    val buttonText = if (isFavorite) "Remover dos Favoritos" else "Adicionar aos Favoritos"
    val icon = if (isFavorite) Icons.Filled.FavoriteBorder else Icons.Filled.Favorite

    Button(
        onClick = {
            if (isFavorite) viewModel.removeFavorite() else viewModel.addFavorite()
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = RedMarvel)
    ) {
        Icon(
            imageVector = icon,
            tint = Color.White,
            contentDescription = "Favorito Botão",
            modifier = Modifier.padding(end = 10.dp)
        )
        Text(text = buttonText, color = Color.White)
    }
}

@Composable
private fun CharacterName(name: String) {
    Text(
        text = name,
        style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
        color = Color.Black,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
private fun CharacterDescription(description: String) {
    Text(
        text = description,
        style = MaterialTheme.typography.body1,
        color = Color.Gray,
        modifier = Modifier.padding(16.dp)
    )
}