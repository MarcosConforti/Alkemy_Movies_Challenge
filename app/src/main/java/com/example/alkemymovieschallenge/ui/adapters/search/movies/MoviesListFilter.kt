package com.example.alkemymovieschallenge.ui.adapters.search.movies

import android.widget.Filter
import androidx.core.text.isDigitsOnly
import com.example.alkemymovieschallenge.domain.model.DomainModel

class MoviesListFilter(
    private val adapter: AllMoviesAdapter
): Filter() {

    override fun performFiltering(constraint: CharSequence?): FilterResults {
        val filterString = constraint.toString()
        val results = FilterResults()
        val list = adapter.filteredAllMoviesList

        val filteredList: List<DomainModel> = if (filterString.isEmpty()) {
            list
        } else {
            list.filter {
                it.title.contains(filterString, true) or (filterString.isDigitsOnly() && it.id.equals( filterString.toInt()))
            }
        }

        results.values = filteredList
        results.count = filteredList.size

        return results
    }

    @Suppress("UNCHECKED_CAST")
    override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
        adapter.filteredAllMoviesList = results?.values as List<DomainModel>
        adapter.notifyDataSetChanged()
    }

}
