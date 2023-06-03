package com.example.alkemymovieschallenge.ui.model.uiList

import com.example.alkemymovieschallenge.domain.model.DomainTvModel

data class SeriesUIList(
     val popularTv:List<DomainTvModel>,
     val airingToday:List<DomainTvModel>,
     val onTheAir:List<DomainTvModel>,
     val topRated:List<DomainTvModel>,
    // val allSeries:List<DomainTvModel>

     )
