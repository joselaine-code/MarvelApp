package com.joselaine.marvelapp.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.joselaine.marvelapp.domain.models.MarvelCharacter
import com.joselaine.marvelapp.presentation.models.CharacterItem

@Composable
fun MarvelList(
    characterPagingData: LazyPagingItems<MarvelCharacter>,
    onRetry: () -> Unit,
    clickOnCharacter: (Int) -> Unit
) {
    Column {
        LazyColumn(
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(
                count = characterPagingData.itemCount,
                key = characterPagingData.itemKey(),
                contentType = characterPagingData.itemContentType()
            ) { index ->
                val character = characterPagingData[index]
                MarvelCard(
                    modifier = Modifier.width(400.dp),
                    characterItem = CharacterItem(
                        name = character?.name ?: "",
                        imageUrl = character?.imageUrl
                    )
                ) {
                    character?.id?.let { idString -> clickOnCharacter(idString.toInt()) }
                }
            }

            when (characterPagingData.loadState.refresh) {
                is LoadState.Error -> {
                    item { MarvelError(onRetry) }
                }

                is LoadState.Loading -> {
                    item { MarvelLoading() }
                }

                else -> {}
            }

            when (characterPagingData.loadState.append) {
                is LoadState.Error -> {
                    item { MarvelError(onRetry) }
                }

                is LoadState.Loading -> {
                    item { MarvelLoading() }
                }

                else -> {}
            }
        }
    }
}