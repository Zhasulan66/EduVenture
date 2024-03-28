package com.example.eduventure.common

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.eduventure.R

class Constants {

    companion object {

        val INTER_FONT_FAMILY = FontFamily(
            Font(R.font.inter_regular),
            Font(R.font.inter_bold, FontWeight.Bold),
            Font(R.font.inter_semibold, FontWeight.SemiBold),
            Font(R.font.inter_medium, FontWeight.Medium),
            Font(R.font.inter_light, FontWeight.Light),
            Font(R.font.inter_extralight, FontWeight.ExtraLight),
        )
    }
}