package com.example.alkemymovieschallenge.ui.favorites.adapter.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alkemymovieschallenge.R
import com.example.alkemymovieschallenge.ui.model.UIModel

class FavoriteMoviesAdapter(
    private var favoriteList: List<UIModel>,
) : RecyclerView.Adapter<FavoritesMoviesViewHolder>() {

    //var filteredFavoriteMoviesList: List<FavoritesUIModel> = favoriteList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesMoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FavoritesMoviesViewHolder(
            layoutInflater.inflate(
                R.layout.item_grid_list,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoritesMoviesViewHolder, position: Int) {
        val item = favoriteList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = favoriteList.size

     fun setFavoriteList(favorite: List<UIModel>) {
        favoriteList = favorite
        notifyDataSetChanged()
    }
}