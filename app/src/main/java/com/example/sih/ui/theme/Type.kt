package com.example.sih.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.sih.R

// If you only have Regular and Bold, point Medium and SemiBold at Regular.
val Devanagari = FontFamily(
    Font(R.font.notosansdevanagari_regular, FontWeight.Normal),
    Font(R.font.notosansdevanagari_medium, FontWeight.Medium),
    Font(R.font.notosansdevanagari_semibold, FontWeight.SemiBold),
    Font(R.font.notosansdevanagari_bold, FontWeight.Bold),
)

/** Tabular figures — without this, "64–115" jitters as digits change width. */
private const val TNUM = "tnum"

object T {

    val Display = TextStyle(
        fontFamily = Devanagari,
        fontWeight = FontWeight.Bold,
        fontSize = 64.sp,
        lineHeight = 66.sp,
        letterSpacing = (-1.9).sp,
        fontFeatureSettings = TNUM,
    )

    val H1 = TextStyle(
        fontFamily = Devanagari,
        fontWeight = FontWeight.SemiBold,
        fontSize = 27.sp,
        lineHeight = 38.sp,
        letterSpacing = (-0.4).sp,
    )

    val H2 = TextStyle(
        fontFamily = Devanagari,
        fontWeight = FontWeight.SemiBold,
        fontSize = 21.sp,
        lineHeight = 30.sp,
        letterSpacing = (-0.2).sp,
    )

    val BodyL = TextStyle(
        fontFamily = Devanagari,
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
        lineHeight = 27.sp,
    )

    val Body = TextStyle(
        fontFamily = Devanagari,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 24.sp,
    )

    val Meta = TextStyle(
        fontFamily = Devanagari,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.4.sp,
    )

    /** The small unit that follows a Display figure, e.g. "mm". */
    val Unit = TextStyle(
        fontFamily = Devanagari,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 22.sp,
    )
}

val ClimoraTypography = Typography(
    displayLarge = T.Display,
    headlineLarge = T.H1,
    headlineMedium = T.H2,
    bodyLarge = T.BodyL,
    bodyMedium = T.Body,
    labelMedium = T.Meta,
)