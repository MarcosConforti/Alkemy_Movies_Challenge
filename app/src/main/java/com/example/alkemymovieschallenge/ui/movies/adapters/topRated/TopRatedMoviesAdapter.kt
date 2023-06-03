package com.example.alkemymovieschallenge.ui.movies.adapters.topRated

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alkemymovieschallenge.R
import com.example.alkemymovieschallenge.ui.model.MoviesUIModel
import com.example.alkemymovieschallenge.ui.movies.adapters.OnClickMoviesListener

class TopRatedMoviesAdapter(
    private var topRatedMoviesList: List<MoviesUIModel>,
    private var onClickMoviesListener: OnClickMoviesListener
) : RecyclerView.Adapter<TopRatedMoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedMoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TopRatedMoviesViewHolder(layoutInflater.inflate(R.layout.item_grid_list, parent, false))
    }

    override fun onBindViewHolder(holder: TopRatedMoviesViewHolder, position: Int) {
        val item = topRatedMoviesList[position]
        holder.render(item)
        holder.itemView.setOnClickListener { onClickMoviesListener.onMoviesClicked(item) }
    }

    override fun getItemCount(): Int = topRatedMoviesList.size

    fun setTopRatedMoviesList(newMovieList: List<MoviesUIModel>) {
        topRatedMoviesList = newMovieList
        notifyDataSetChanged()
    }


}