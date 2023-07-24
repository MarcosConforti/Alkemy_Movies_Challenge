package com.example.alkemymovieschallenge.ui.model.uiList

import com.example.alkemymovieschallenge.ui.model.UIModel

data class MovieUIList(
     val popular:List<UIModel>,
     val upComing:List<UIModel>,
     val nowPlaying:List<UIModel>,
     val topRated:List<UIModel>
)
