package com.example.alkemymovieschallenge.ui.adapters.search.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.alkemymovieschallenge.R
import com.example.alkemymovieschallenge.domain.model.DomainModel
import com.example.alkemymovieschallenge.ui.adapters.OnClickMoviesListener

class AllMoviesAdapter(
    private var allMoviesList: List<DomainModel>,
    private var onClickMoviesListener: OnClickMoviesListener
) : RecyclerView.Adapter<AllMoviesViewHolder>(), Filterable {

    private val filter = MoviesListFilter(this)
    var filteredAllMoviesList: List<DomainModel> = emptyList()

    init {
        filteredAllMoviesList = allMoviesList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllMoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AllMoviesViewHolder(layoutInflater.inflate(R.layout.item_grid_list, parent, false))
    }

    override fun onBindViewHolder(holder: AllMoviesViewHolder, position: Int) {
        val item = filteredAllMoviesList[position]
        holder.render(item)
        holder.itemView.setOnClickListener { onClickMoviesListener?.onMoviesClicked(item) }
    }

    override fun getItemCount(): Int = filteredAllMoviesList.size

    fun setAllMoviesList(newMovieList: List<DomainModel>) {
        allMoviesList = newMovieList
        filteredAllMoviesList = newMovieList
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter = filter


}