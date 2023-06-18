package com.example.alkemymovieschallenge.ui.series.adapters.topRated

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alkemymovieschallenge.R
import com.example.alkemymovieschallenge.ui.model.UIModel
import com.example.alkemymovieschallenge.ui.series.adapters.OnClickSeriesListener

class TopRatedTvAdapter(
    private var topRatedTvList: List<UIModel>,
    private var onClickSeriesListener: OnClickSeriesListener
) : RecyclerView.Adapter<TopRatedTvViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedTvViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TopRatedTvViewHolder(layoutInflater.inflate(R.layout.item_grid_list, parent, false))
    }

    override fun onBindViewHolder(holder: TopRatedTvViewHolder, position: Int) {
        val item = topRatedTvList[position]
        holder.render(item)
        holder.itemView.setOnClickListener { onClickSeriesListener?.onSeriesClicked(item) }
    }

    override fun getItemCount(): Int = topRatedTvList.size

    fun setTopRatedTvList(newTvList: List<UIModel>) {
        topRatedTvList = newTvList
        notifyDataSetChanged()
    }


}