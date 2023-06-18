package com.example.alkemymovieschallenge.ui.series.adapters.airing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alkemymovieschallenge.R
import com.example.alkemymovieschallenge.ui.model.UIModel
import com.example.alkemymovieschallenge.ui.series.adapters.OnClickSeriesListener

class AiringTodayTvAdapter(
    private var airingTodayTvList: List<UIModel>,
    private var onClickSeriesListener: OnClickSeriesListener
) : RecyclerView.Adapter<AiringTodayTvViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AiringTodayTvViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AiringTodayTvViewHolder(layoutInflater.inflate(R.layout.item_grid_list, parent, false))
    }

    override fun onBindViewHolder(holder: AiringTodayTvViewHolder, position: Int) {
        val item = airingTodayTvList[position]
        holder.render(item)
        holder.itemView.setOnClickListener { onClickSeriesListener?.onSeriesClicked(item) }
    }

    override fun getItemCount(): Int = airingTodayTvList.size

    fun setAiringTodayTvList(newTvList: List<UIModel>) {
        airingTodayTvList = newTvList
        notifyDataSetChanged()
    }

}