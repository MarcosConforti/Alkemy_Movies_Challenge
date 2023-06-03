package com.example.alkemymovieschallenge.ui.movies.adapters.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alkemymovieschallenge.R
import com.example.alkemymovieschallenge.ui.model.MoviesUIModel
import com.example.alkemymovieschallenge.ui.movies.adapters.OnClickMoviesListener

class PopularMoviesAdapter(
    private var popularMoviesList: List<MoviesUIModel>,
    private var onClickMoviesListener: OnClickMoviesListener
) : RecyclerView.Adapter<PopularMoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PopularMoviesViewHolder(layoutInflater.inflate(R.layout.item_grid_list, parent, false))
    }

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {
        val item = popularMoviesList[position]
        holder.render(item)
        holder.itemView.setOnClickListener { onClickMoviesListener?.onMoviesClicked(item) }
    }

    override fun getItemCount(): Int = popularMoviesList.size

    fun setPopularMoviesList(newMovieList: List<MoviesUIModel>) {
        popularMoviesList = newMovieList
        notifyDataSetChanged()
    }


}