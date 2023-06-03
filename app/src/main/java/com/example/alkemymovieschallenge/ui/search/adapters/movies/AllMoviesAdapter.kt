package com.example.alkemymovieschallenge.ui.search.adapters.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.alkemymovieschallenge.R
import com.example.alkemymovieschallenge.ui.model.MoviesUIModel
import com.example.alkemymovieschallenge.ui.movies.adapters.OnClickMoviesListener

class AllMoviesAdapter(
    private var allMoviesList: List<MoviesUIModel>,
    private var onClickMoviesListener: OnClickMoviesListener
) : RecyclerView.Adapter<AllMoviesViewHolder>(), Filterable {

    var filteredAllMoviesList: List<MoviesUIModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllMoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AllMoviesViewHolder(layoutInflater.inflate(R.layout.item_grid_list, parent, false))
    }

    override fun onBindViewHolder(holder: AllMoviesViewHolder, position: Int) {
        val item = filteredAllMoviesList[position]
        holder.render(item)
        holder.itemView.setOnClickListener { onClickMoviesListener.onMoviesClicked(item) }
    }

    override fun getItemCount(): Int = filteredAllMoviesList.size

    fun setAllMoviesList(newMovieList: List<MoviesUIModel>) {
        allMoviesList = newMovieList
        filteredAllMoviesList = newMovieList
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = if (constraint.isNullOrEmpty()) {
                    allMoviesList
                } else {
                    allMoviesList.filter { it.title.contains(constraint, true) }
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                @Suppress("UNCHECKED_CAST")
                filteredAllMoviesList = results?.values as List<MoviesUIModel>?
                    ?: emptyList()
                notifyDataSetChanged()
            }
        }
    }
}