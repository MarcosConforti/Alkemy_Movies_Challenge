package com.example.alkemymovieschallenge.ui.favorites.adapter.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.alkemymovieschallenge.R
import com.example.alkemymovieschallenge.ui.model.UIModel

class FavoriteMoviesAdapter(
    private var favoriteList: List<UIModel>,
) : RecyclerView.Adapter<FavoritesMoviesViewHolder>(), Filterable {

    var filteredFavoriteMoviesList: List<UIModel> = favoriteList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesMoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FavoritesMoviesViewHolder(
            layoutInflater.inflate(
                R.layout.item_grid_list,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoritesMoviesViewHolder, position: Int) {
        val item = filteredFavoriteMoviesList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = filteredFavoriteMoviesList.size

    fun setFavoriteList(favorite: List<UIModel>) {
        favoriteList = favorite
        filteredFavoriteMoviesList = favorite
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = if (constraint.isNullOrEmpty()) {
                    favoriteList
                } else {
                    favoriteList.filter { it.title.contains(constraint, true) }
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                @Suppress("UNCHECKED_CAST")
                filteredFavoriteMoviesList = results?.values as List<UIModel>?
                    ?: emptyList()
                notifyDataSetChanged()
            }
        }
    }
}