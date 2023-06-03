package com.example.alkemymovieschallenge.ui.movies.adapters

import com.example.alkemymovieschallenge.domain.model.DomainModel

interface OnClickMoviesListener {

    fun onMoviesClicked(movie:DomainModel)
}