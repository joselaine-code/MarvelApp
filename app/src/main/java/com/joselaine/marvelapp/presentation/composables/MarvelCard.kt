package com.joselaine.marvelapp.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.joselaine.marvelapp.presentation.models.CharacterItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarvelCard(
    modifier: Modifier = Modifier,
    characterItem: CharacterItem,
    clickOnCharacter: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp),
        onClick = { clickOnCharacter() },
        shape = RoundedCornerShape(8.dp),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            val imagePainter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(characterItem.imageUrl)
                    .crossfade(true)
                    .build(),
            )

            Image(
                painter = imagePainter,
                contentDescription = "Imagem ${characterItem.name}",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = OverlayAlpha))
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = characterItem.name,
                    style = TextStyle(color = Color.White, fontSize = 20.sp),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

private const val OverlayAlpha = 0.5f


@Preview
@Composable
fun OverlayCardPreview() {
    val characterItem = CharacterItem(
        name = "Exemplo",
        imageUrl = "https://i.annihil.us/u/prod/marvel/i/mg/b/b0/4ce59ea2103ac.jpg"
    )

    MarvelCard(characterItem = characterItem) {}
}