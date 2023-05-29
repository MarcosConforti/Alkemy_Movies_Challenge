package com.example.alkemymovieschallenge.ui.adapters.movies.nowPlaying

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alkemymovieschallenge.R
import com.example.alkemymovieschallenge.domain.model.DomainModel
import com.example.alkemymovieschallenge.ui.adapters.OnClickMoviesListener

class NowPlayingMoviesAdapter(
    private var nowPlayingMoviesList: List<DomainModel>,
    private var onClickMoviesListener: OnClickMoviesListener
) : RecyclerView.Adapter<NowPlayingMoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingMoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return NowPlayingMoviesViewHolder(layoutInflater.inflate(R.layout.item_grid_list, parent, false))
    }

    override fun onBindViewHolder(holder: NowPlayingMoviesViewHolder, position: Int) {
        val item = nowPlayingMoviesList[position]
        holder.render(item)
        holder.itemView.setOnClickListener { onClickMoviesListener?.onMoviesClicked(item) }
    }

    override fun getItemCount(): Int = nowPlayingMoviesList.size

    fun setNowPlayingMoviesList(newMovieList: List<DomainModel>) {
        nowPlayingMoviesList = newMovieList
        notifyDataSetChanged()
    }


}