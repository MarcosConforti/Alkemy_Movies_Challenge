package com.example.alkemymovieschallenge.ui.adapters.tv.topRated

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alkemymovieschallenge.R
import com.example.alkemymovieschallenge.domain.model.DomainTvModel
import com.example.alkemymovieschallenge.ui.adapters.OnClickTvListener

class TopRatedTvAdapter(
    private var topRatedTvList: List<DomainTvModel>,
    private var onClickTvListener: OnClickTvListener
) : RecyclerView.Adapter<TopRatedTvViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedTvViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TopRatedTvViewHolder(layoutInflater.inflate(R.layout.item_grid_list, parent, false))
    }

    override fun onBindViewHolder(holder: TopRatedTvViewHolder, position: Int) {
        val item = topRatedTvList[position]
        holder.render(item)
        holder.itemView.setOnClickListener { onClickTvListener?.onTvClicked(item) }
    }

    override fun getItemCount(): Int = topRatedTvList.size

    fun setTopRatedTvList(newTvList: List<DomainTvModel>) {
        topRatedTvList = newTvList
        notifyDataSetChanged()
    }


}