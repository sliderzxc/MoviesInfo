package com.test.movieapplication.utils.settings.color

import com.test.movieapplication.R
import com.test.movieapplication.utils.settings.color.ColorConstants.AQUA_COLOR
import com.test.movieapplication.utils.settings.color.ColorConstants.BLACK_COLOR
import com.test.movieapplication.utils.settings.color.ColorConstants.COBALT_COLOR
import com.test.movieapplication.utils.settings.color.ColorConstants.DEEP_YELLOW_COLOR
import com.test.movieapplication.utils.settings.color.ColorConstants.GREEN_COLOR
import com.test.movieapplication.utils.settings.color.ColorConstants.NAVY_COLOR
import com.test.movieapplication.utils.settings.color.ColorConstants.OLIVE_COLOR
import com.test.movieapplication.utils.settings.color.ColorConstants.PURPLE_COLOR
import com.test.movieapplication.utils.settings.color.ColorConstants.SCARLET_COLOR
import com.test.movieapplication.utils.settings.color.ColorConstants.SIGNAL_ORANGE_COLOR

fun checkWhichThemeIsChecked(color: Int) : Int {
    return when(color) {
        GREEN_COLOR -> R.style.Theme_MovieApplication_Green
        PURPLE_COLOR -> R.style.Theme_MovieApplication_Purple
        COBALT_COLOR -> R.style.Theme_MovieApplication_Cobalt
        SCARLET_COLOR -> R.style.Theme_MovieApplication_Scarlet
        DEEP_YELLOW_COLOR -> R.style.Theme_MovieApplication_DeepYellow
        SIGNAL_ORANGE_COLOR -> R.style.Theme_MovieApplication_SignalOrange
        OLIVE_COLOR -> R.style.Theme_MovieApplication_Olive
        AQUA_COLOR -> R.style.Theme_MovieApplication_Aqua
        NAVY_COLOR -> R.style.Theme_MovieApplication_Navy
        BLACK_COLOR -> R.style.Theme_MovieApplication_Black
        else -> 0
    }
}