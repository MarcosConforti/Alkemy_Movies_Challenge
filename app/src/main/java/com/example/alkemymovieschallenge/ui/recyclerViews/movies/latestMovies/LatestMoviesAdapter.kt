package com.example.alkemymovieschallenge.ui.recyclerViews.movies.latestMovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alkemymovieschallenge.R
import com.example.alkemymovieschallenge.domain.model.DomainModel
import com.example.alkemymovieschallenge.ui.recyclerViews.OnClickMoviesListener

class LatestMoviesAdapter(
    private var latestMoviesList: List<DomainModel>,
    private var onClickMoviesListener: OnClickMoviesListener
) : RecyclerView.Adapter<LatestMoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestMoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return LatestMoviesViewHolder(layoutInflater.inflate(R.layout.item_grid_list, parent, false))
    }

    override fun onBindViewHolder(holder: LatestMoviesViewHolder, position: Int) {
        val item = latestMoviesList[position]
        holder.render(item)
        holder.itemView.setOnClickListener { onClickMoviesListener?.onMoviesClicked(item) }
    }

    override fun getItemCount(): Int = latestMoviesList.size

    //override fun getFilter(): Filter = filter

    fun setLatestMoviesList(newMovieList: List<DomainModel>) {
        latestMoviesList = newMovieList
        notifyDataSetChanged()
    }


}