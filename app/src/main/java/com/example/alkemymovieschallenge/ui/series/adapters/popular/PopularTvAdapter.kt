package com.example.alkemymovieschallenge.ui.series.adapters.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alkemymovieschallenge.R
import com.example.alkemymovieschallenge.ui.model.UIModel
import com.example.alkemymovieschallenge.ui.series.adapters.OnClickSeriesListener

class PopularTvAdapter(
    private var popularTvList: List<UIModel>,
    private var onClickSeriesListener: OnClickSeriesListener
) : RecyclerView.Adapter<PopularTvViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularTvViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PopularTvViewHolder(layoutInflater.inflate(R.layout.item_grid_list, parent, false))
    }

    override fun onBindViewHolder(holder: PopularTvViewHolder, position: Int) {
        val item = popularTvList[position]
        holder.render(item)
        holder.itemView.setOnClickListener { onClickSeriesListener?.onSeriesClicked(item) }
    }

    override fun getItemCount(): Int = popularTvList.size

    fun setPopularTvList(newTvList: List<UIModel>) {
        popularTvList = newTvList
        notifyDataSetChanged()
    }


}