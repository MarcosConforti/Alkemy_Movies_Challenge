package com.example.alkemymovieschallenge.domain.list

import com.example.alkemymovieschallenge.domain.model.DomainTvModel

data class TvList(
     val popularTv:List<DomainTvModel>,
     val airingToday:List<DomainTvModel>,
     val onTheAir:List<DomainTvModel>,
     val topRated:List<DomainTvModel>,
    // val allSeries:List<DomainTvModel>

     )
