package com.example.authenticationapp.ui.theme

import com.example.authenticationapp.R.font as font
import androidx.compose.material3.Typography
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// https://developer.android.com/develop/ui/compose/text/fonts#load-variable
@OptIn(ExperimentalTextApi::class)
val customFontFamily =
    FontFamily(
        Font(font.source_code_pro_light, FontWeight.Light),
        Font(font.source_code_pro_regular, FontWeight.Normal),
        Font(font.source_code_pro_italic, FontWeight.Normal, FontStyle.Italic),
        Font(font.source_code_pro_medium, FontWeight.Medium),
        Font(font.source_code_pro_bold, FontWeight.Bold),
    )

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 34.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        letterSpacing = 0.sp
    ),
    bodySmall = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        letterSpacing = 0.sp
    )
)