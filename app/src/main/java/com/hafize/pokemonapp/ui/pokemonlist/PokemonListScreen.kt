package com.hafize.pokemonapp.ui.pokemonlist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hafize.pokemonapp.R
import com.hafize.pokemonapp.data.model.PokemonItemEntry
import com.hafize.pokemonapp.util.reformatNum

@Composable
fun PokemonListScreen(
    navController: NavController
) {
    Scaffold(
        backgroundColor = Color.White,
    ) {
        Column {
            Spacer(modifier = Modifier.height(16.dp))
            TopBar(modifier = Modifier)
            Spacer(modifier = Modifier.height(16.dp))
            PokemonList(navController = navController)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PokemonList(
    navController: NavController,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val pokemonList by remember { viewModel.pokemonList }
    val endReached by remember { viewModel.endReached }
    val loadError by remember { viewModel.loadError }
    val isLoading by remember { viewModel.isLoading }
    val isSearching by remember { viewModel.isSearching }

    val itemCount = pokemonList.size
    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        itemsIndexed(pokemonList) { index, item ->
            if (index >= itemCount - 1 && !endReached && !isLoading && !isSearching) {
                LaunchedEffect(key1 = true) {
                    viewModel.loadPokemonPaginated()
                }
            }
            Pokemon(
                entry = item,
                viewModel = viewModel,
                navController = navController
            )
        }
    }
    Box(
        contentAlignment = Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if (isLoading) {
            CircularProgressIndicator(color = MaterialTheme.colors.primary)
        }
        if (loadError.isNotEmpty()) {
            RetrySection(error = loadError) {
                viewModel.loadPokemonPaginated()
            }
        }
    }
}

@Composable
fun TopBar(modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(colorResource(id = R.color.purple_500))
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Pokemon", fontWeight = FontWeight.Medium, fontSize = 28.sp, color = Color.White)

    }
}

@Composable
fun Pokemon(
    entry: PokemonItemEntry,
    viewModel: PokemonListViewModel,
    navController: NavController
) {
    val dominantSurfaceColor = MaterialTheme.colors.surface
    var dominantColor by remember {
        mutableStateOf(dominantSurfaceColor)
    }

    Column(
        modifier = Modifier
            .fillMaxHeight(0.1f)
            .fillMaxWidth()
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(8.dp))
            .background(color = dominantColor, shape = RoundedCornerShape(8.dp))
            .clickable {
                navController.navigate(
                    "pokemon_detail_screen/${dominantColor.toArgb()}/${entry.name}/${entry.number}"
                )
            }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = reformatNum(entry.number),
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 12.dp, top = 12.dp)
            )
            Text(
                text = entry.name,
                fontWeight = FontWeight.Medium,
                fontSize = 17.sp,
                modifier = Modifier.padding(start = 12.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(entry.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "",
                contentScale = ContentScale.FillHeight,
                onSuccess = { it ->
                    viewModel.calcDominantColor(it.result.drawable) {
                        dominantColor = it
                    }
                },
                modifier = Modifier.size(125.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}


@Composable
fun RetrySection(
    error: String,
    onRetry: () -> Unit
) {
    Column {
        Text(text = error, color = Color.Red, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { onRetry() },
            modifier = Modifier.align(CenterHorizontally)
        ) {
            Text(text = "Retry")
        }
    }
}