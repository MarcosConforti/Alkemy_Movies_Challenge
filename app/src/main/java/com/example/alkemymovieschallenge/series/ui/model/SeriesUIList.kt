package com.example.alkemymovieschallenge.series.ui.model

import com.example.alkemymovieschallenge.core.ui.UIModel

data class SeriesUIList(
    val popularTv:List<UIModel>,
    val airingToday:List<UIModel>,
    val onTheAir:List<UIModel>,
    val topRated:List<UIModel>,
     )
