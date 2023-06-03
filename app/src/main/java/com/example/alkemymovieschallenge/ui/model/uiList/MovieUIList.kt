package com.example.alkemymovieschallenge.ui.model.uiList

import com.example.alkemymovieschallenge.ui.model.MoviesUIModel

data class MovieUIList(
     val popular:List<MoviesUIModel>,
     val upComing:List<MoviesUIModel>,
     val nowPlaying:List<MoviesUIModel>,
     val topRated:List<MoviesUIModel>
)
