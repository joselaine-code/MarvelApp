package com.joselaine.marvelapp.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.joselaine.marvelapp.R
import com.joselaine.marvelapp.presentation.ui.theme.RedMarvel

@Composable
fun MarvelError(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = stringResource(R.string.ocorreu_um_erro))
        Button(
            onClick = { onClick() },
            colors = ButtonDefaults.buttonColors(containerColor = RedMarvel)
        ) {
            Text(text = stringResource(R.string.tentar_novamente))
        }
    }
}