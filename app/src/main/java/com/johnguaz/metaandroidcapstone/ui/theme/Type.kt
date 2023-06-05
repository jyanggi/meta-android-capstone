package com.johnguaz.metaandroidcapstone.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.johnguaz.metaandroidcapstone.R


val Karla = FontFamily(
    Font(R.font.karla_regular)
)


val Markazi = FontFamily(
    Font(R.font.markazi_text_regular)
)

val Typography = Typography(
    h1 = TextStyle(
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        color = LittleLemonColor.charcoal,
        fontFamily = Karla
    ),
    h2 = TextStyle(
        color = LittleLemonColor.charcoal,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = Karla
    ),
    body1 = TextStyle(
        color = LittleLemonColor.green,
        fontFamily = Karla
    ),
    body2 = TextStyle(
        fontWeight = FontWeight.Bold,
        color = LittleLemonColor.green,
        fontFamily = Karla
    ),
    button = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = Karla
    )
)