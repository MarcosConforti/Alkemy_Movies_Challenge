package com.example.alkemymovieschallenge.ui.recyclerViews

import com.example.alkemymovieschallenge.domain.model.DomainModel
import com.example.alkemymovieschallenge.domain.model.DomainTvModel

interface OnClickMoviesListener {

    fun onMoviesClicked(movie:DomainModel)
}