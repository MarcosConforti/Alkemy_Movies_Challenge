package com.example.alkemymovieschallenge.movies.ui.model

import com.example.alkemymovieschallenge.core.ui.UIModel

data class MovieUIList(
    val popular:List<UIModel>,
    val upComing:List<UIModel>,
    val nowPlaying:List<UIModel>,
    val topRated:List<UIModel>
)
