package com.mdshahsamir.starwars

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mdshahsamir.starwars.presentation.FilmListUiState
import com.mdshahsamir.starwars.presentation.FilmListViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun HomePage(
    viewModel: FilmListViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me KMP!")
            }

            when (val state = uiState) {
                is FilmListUiState.Loading -> CircularProgressIndicator()// Show loader
                is FilmListUiState.Success -> {
                    LazyColumn {
                        items(state.films) { film ->
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colorScheme.surface)
                            ) {
                                film.title?.let { Text(it, style = MaterialTheme.typography.titleLarge) }
                            }
                        }
                    }
                }
                is FilmListUiState.Error -> {
                    Text("Error: ${state.message}")
                }
            }
        }
    }
}