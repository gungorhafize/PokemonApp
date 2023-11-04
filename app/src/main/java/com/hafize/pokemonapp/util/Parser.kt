package com.hafize.pokemonapp.util

import androidx.compose.ui.graphics.Color
import com.example.pokemon.data.remote.responses.Stat
import com.example.pokemon.data.remote.responses.Type
import com.hafize.pokemonapp.R
import com.hafize.pokemonapp.ui.theme.AtkColor
import com.hafize.pokemonapp.ui.theme.DefColor
import com.hafize.pokemonapp.ui.theme.HPColor
import com.hafize.pokemonapp.ui.theme.SpAtkColor
import com.hafize.pokemonapp.ui.theme.SpDefColor
import com.hafize.pokemonapp.ui.theme.SpdColor
import com.hafize.pokemonapp.ui.theme.TypeBug
import com.hafize.pokemonapp.ui.theme.TypeDark
import com.hafize.pokemonapp.ui.theme.TypeDragon
import com.hafize.pokemonapp.ui.theme.TypeElectric
import com.hafize.pokemonapp.ui.theme.TypeFairy
import com.hafize.pokemonapp.ui.theme.TypeFighting
import com.hafize.pokemonapp.ui.theme.TypeFire
import com.hafize.pokemonapp.ui.theme.TypeFlying
import com.hafize.pokemonapp.ui.theme.TypeGhost
import com.hafize.pokemonapp.ui.theme.TypeGrass
import com.hafize.pokemonapp.ui.theme.TypeGround
import com.hafize.pokemonapp.ui.theme.TypeIce
import com.hafize.pokemonapp.ui.theme.TypeNormal
import com.hafize.pokemonapp.ui.theme.TypePoison
import com.hafize.pokemonapp.ui.theme.TypePsychic
import com.hafize.pokemonapp.ui.theme.TypeRock
import com.hafize.pokemonapp.ui.theme.TypeSteel
import com.hafize.pokemonapp.ui.theme.TypeWater
import java.util.*

fun parseTypeToColor(type: Type): Color {
    return when(type.type.name.lowercase(Locale.ROOT)) {
        "normal" -> TypeNormal
        "fire" -> TypeFire
        "water" -> TypeWater
        "electric" -> TypeElectric
        "grass" -> TypeGrass
        "ice" -> TypeIce
        "fighting" -> TypeFighting
        "poison" -> TypePoison
        "ground" -> TypeGround
        "flying" -> TypeFlying
        "psychic" -> TypePsychic
        "bug" -> TypeBug
        "rock" -> TypeRock
        "ghost" -> TypeGhost
        "dragon" -> TypeDragon
        "dark" -> TypeDark
        "steel" -> TypeSteel
        "fairy" -> TypeFairy
        else -> Color.Black
    }
}



fun parseStatToAbbr(stat: Stat): String {
    return when(stat.stat.name.lowercase(Locale.ROOT)) {
        "hp" -> "HP"
        "attack" -> "Atk"
        "defense" -> "Def"
        "special-attack" -> "SpAtk"
        "special-defense" -> "SpDef"
        "speed" -> "Spd"
        else -> ""
    }
}

fun parseStatToColor(stat: Stat): Color {
    return when(stat.stat.name.lowercase(Locale.ROOT)) {
        "hp" -> HPColor
        "attack" -> AtkColor
        "defense" -> DefColor
        "special-attack" -> SpAtkColor
        "special-defense" -> SpDefColor
        "speed" -> SpdColor
        else -> Color.White
    }
}

fun reformatNum(num: Int): String {
    val numToString = num.toString()
    return when (num.toString().length) {
        1 -> "#00$numToString"
        2 -> "#0$numToString"
        else -> "#$numToString"
    }
}

fun parseTypeToIcon(type: Type): Int {
    return when(type.type.name.toLowerCase(Locale.ROOT)) {
        "fire" -> R.drawable.ic_fire
        "water" -> R.drawable.ic_water
        "electric" -> R.drawable.ic_electric
        "grass" -> R.drawable.ic_grass
        "ice" -> R.drawable.ic_ice
        "fighting" -> R.drawable.ic_fighting
        "poison" -> R.drawable.ic_poison
        "ground" -> R.drawable.ic_ground
        "flying" -> R.drawable.ic_flying
        "psychic" -> R.drawable.ic_psychic
        "bug" -> R.drawable.ic_bug
        "rock" -> R.drawable.ic_rock
        "ghost" -> R.drawable.ic_ghost
        "dragon" -> R.drawable.ic_dragon
        "dark" -> R.drawable.ic_dark
        "steel" -> R.drawable.ic_steel
        "fairy" -> R.drawable.ic_fairy
        else -> R.drawable.ic_normal
    }
}