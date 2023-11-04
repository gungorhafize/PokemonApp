package com.hafize.pokemonapp.ui.pokemondetail

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pokemon.data.remote.responses.Pokemon
import com.example.pokemon.data.remote.responses.Type
import com.hafize.pokemonapp.util.Constant.DecimetersToFoot
import com.hafize.pokemonapp.util.Constant.HectogramToPounds
import com.hafize.pokemonapp.util.Constant.IMAGE_REQUEST_URL
import com.hafize.pokemonapp.util.Resource
import com.hafize.pokemonapp.util.parseStatToAbbr
import com.hafize.pokemonapp.util.parseStatToColor
import com.hafize.pokemonapp.util.parseTypeToColor
import com.hafize.pokemonapp.util.parseTypeToIcon
import com.hafize.pokemonapp.util.reformatNum
import java.util.*

@Composable
fun PokemonDetailScreen(
    dominantColor: Int,
    name: String,
    number: Int,
    navController: NavController,
    topPadding: Dp = 20.dp,
    pokemonImageSize: Dp = 200.dp,
    viewModel: PokemonDetailViewModel = hiltViewModel()
) {
    val pokemonDetail = produceState<Resource<Pokemon>>(initialValue = Resource.Loading()) {
        value = viewModel.getPokemonDetail(name)
    }.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp)
            .background(Color(dominantColor))
    ) {
        TopBar(navController = navController)
        PokemonDetailStateWrapper(
            pokemonDetail = pokemonDetail,
            number = number,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = topPadding + pokemonImageSize / 2,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
                .shadow(10.dp, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colors.surface),
            loadingModifier = Modifier
                .size(100.dp)
                .padding(
                    top = topPadding + pokemonImageSize / 2,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
        )
    }
}

@Composable
fun TopBar(
    navController: NavController,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Navigate Up",
            tint = Color.Black,
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    navController.popBackStack()
                }
        )
        Icon(
            imageVector = Icons.Default.FavoriteBorder,
            contentDescription = "Add to Favorites",
            tint = Color.Black,
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    //ToDo: save pokemon
                }
        )
    }
}

@Composable
fun PokemonDetailStateWrapper(
    pokemonDetail: Resource<Pokemon>,
    modifier: Modifier = Modifier,
    number: Int,
    loadingModifier: Modifier = Modifier
) {
    when (pokemonDetail) {
        is Resource.Success -> {
            PokemonDetailSection(
                pokemonDetail = pokemonDetail.data!!,
                number = number,
                modifier = Modifier.offset(y = (-20).dp)
            )
        }
        is Resource.Error -> {
            Text(
                text = pokemonDetail.message!!,
                modifier = modifier
            )
        }
        is Resource.Loading -> {
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary,
                modifier = loadingModifier
            )
        }
    }
}

@Composable
fun PokemonDetailSection(
    pokemonDetail: Pokemon,
    number: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                )
                .align(Alignment.BottomCenter)
        ) {
            PokemonAbout(pokemonDetail = pokemonDetail)
            Spacer(modifier = Modifier.height(16.dp))
            PokemonBaseStats(pokemonDetail = pokemonDetail)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopStart)
        ) {
            PokemonNumberAndName(
                number = number,
                name = pokemonDetail.name
            )
            Spacer(modifier = Modifier.height(8.dp))

            PokemonTypes(types = pokemonDetail.types)
            Spacer(modifier = Modifier.height(16.dp))

            PokemonImage(number = number)
        }
    }
}

@Composable
fun PokemonNumberAndName(
    number: Int,
    name: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(text = name, fontWeight = FontWeight.Medium, fontSize = 28.sp)
        Text(text = reformatNum(number), fontWeight = FontWeight.Medium, fontSize = 16.sp)
    }
}

@Composable
fun PokemonTypes(types: List<Type>) {
    Row(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (type in types) {
            Row(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clip(CircleShape)
                    .background(parseTypeToColor(type))
                    .padding(start = 8.dp, top = 2.dp, end = 12.dp, bottom = 2.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = parseTypeToIcon(type)),
                    contentDescription = "type",
                    modifier = Modifier.size(16.dp),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = type.type.name.capitalize(Locale.ROOT),
                    color = Color.White,
                    fontSize = 15.sp
                )
            }
        }
    }
}

@Composable
fun PokemonImage(
    number: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("$IMAGE_REQUEST_URL$number.png")
                .crossfade(true)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(275.dp)
        )
    }
}

@Composable
fun PokemonAbout(
    pokemonDetail: Pokemon
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
    ) {
        Text(
            text = "About",
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(8.dp))
        PokemonDetailRow(
            title = "Height",
            data = "${String.format("%.2f", pokemonDetail.height * DecimetersToFoot)} ft"
        )
        PokemonDetailRow(
            title = "Weight",
            data = "${String.format("%.2f", pokemonDetail.weight * HectogramToPounds)} lb"
        )
        var abilities = ""
        pokemonDetail.abilities.forEach { ability ->
            abilities += if (ability == pokemonDetail.abilities.last()) {
                ability.ability.name
            } else {
                "${ability.ability.name}, "
            }
        }
        PokemonDetailRow(title = "Abilities", data = abilities)
    }
}

@Composable
fun PokemonDetailRow(
    title: String,
    data: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            color = Color.DarkGray,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(0.3f),
            fontSize = 14.sp
        )
        Text(
            text = data,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(0.7f),
            fontSize = 14.sp
        )
    }
}

@Composable
fun PokemonBaseStats(
    pokemonDetail: Pokemon,
    animDelayPerItem: Int = 100
) {
    val maxBaseStat = remember {
        pokemonDetail.stats.maxOf { it.baseStat }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Base stats",
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(8.dp))
        for (i in pokemonDetail.stats.indices) {
            val stat = pokemonDetail.stats[i]
            PokemonStat(
                statName = parseStatToAbbr(stat),
                statValue = stat.baseStat,
                statMaxValue = maxBaseStat,
                statColor = parseStatToColor(stat),
                animDelay = i * animDelayPerItem
            )
        }
    }
}

@Composable
fun PokemonStat(
    statName: String,
    statValue: Int,
    statMaxValue: Int,
    statColor: Color,
    height: Dp = 10.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }
    val currentPercent = animateFloatAsState(
        targetValue = if (animationPlayed) {
            statValue / statMaxValue.toFloat()
        } else 0f,
        animationSpec = tween(
            animDuration,
            animDelay
        )
    )
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = statName,
            color = Color.DarkGray,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(0.2f),
            fontSize = 14.sp
        )
        Row(
            modifier = Modifier.weight(0.8f)
        ) {
            Text(
                text = (currentPercent.value * statMaxValue).toInt().toString(),
                color = Color.DarkGray,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(0.1f),
                fontSize = 14.sp
            )
            Box(
                modifier = Modifier
                    .weight(0.8f)
                    .height(height)
                    .clip(CircleShape)
                    .background(
                        if (isSystemInDarkTheme()) {
                            Color(0xFF505050)
                        } else {
                            Color.LightGray
                        }
                    )
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(currentPercent.value)
                        .clip(CircleShape)
                        .background(statColor)
                        .padding(horizontal = 8.dp)
                ) {}
            }
        }
    }

}