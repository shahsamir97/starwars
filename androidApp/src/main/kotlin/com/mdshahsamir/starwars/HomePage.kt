package com.mdshahsamir.starwars

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

        Scaffold { contentPadding ->
            Column(
                modifier = Modifier
                    .padding(contentPadding)
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .safeContentPadding()
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                when (val state = uiState) {
                    is FilmListUiState.Loading -> CircularProgressIndicator()

                    is FilmListUiState.Success -> {
                        LazyColumn {
                            items(state.films) { film ->
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            color = MaterialTheme.colorScheme.surface,
                                            shape = MaterialTheme.shapes.medium
                                        )
                                ) {
                                    film.title?.let {
                                        Text(
                                            modifier = Modifier.padding(16.dp),
                                            text = it,
                                            style = MaterialTheme.typography.titleLarge
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(8.dp))
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
}