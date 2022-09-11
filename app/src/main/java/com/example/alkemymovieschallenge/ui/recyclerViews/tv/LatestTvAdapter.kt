package com.example.alkemymovieschallenge.ui.recyclerViews.tv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alkemymovieschallenge.R
import com.example.alkemymovieschallenge.domain.model.DomainTvModel
import com.example.alkemymovieschallenge.ui.recyclerViews.OnClickTvListener

class LatestTvAdapter(
    private var latestTvList: List<DomainTvModel>,
    private var onClickTvListener: OnClickTvListener
) : RecyclerView.Adapter<LatestTvViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestTvViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return LatestTvViewHolder(layoutInflater.inflate(R.layout.item_grid_list, parent, false))
    }

    override fun onBindViewHolder(holder: LatestTvViewHolder, position: Int) {
        val item = latestTvList[position]
        holder.render(item)
        holder.itemView.setOnClickListener { onClickTvListener?.onTvClicked(item) }
    }

    override fun getItemCount(): Int = latestTvList.size

    //override fun getFilter(): Filter = filter

    fun setLatestTvList(newTvList: List<DomainTvModel>) {
        latestTvList = newTvList
        notifyDataSetChanged()
    }


}