package com.example.alkemymovieschallenge.ui.favorites.adapter.movies

import com.example.alkemymovieschallenge.ui.model.FavoritesUIModel

interface OnClickFavoriteMoviesListener {

    fun onMoviesClicked(favorite: FavoritesUIModel)
}