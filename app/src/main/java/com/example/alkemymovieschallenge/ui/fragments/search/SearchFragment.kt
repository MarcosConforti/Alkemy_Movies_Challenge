package com.example.alkemymovieschallenge.ui.fragments.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.alkemymovieschallenge.databinding.FragmentSearchBinding
import com.example.alkemymovieschallenge.domain.NetworkState
import com.example.alkemymovieschallenge.domain.model.DomainModel
import com.example.alkemymovieschallenge.ui.adapters.OnClickMoviesListener
import com.example.alkemymovieschallenge.ui.adapters.search.movies.AllMoviesAdapter
import com.example.alkemymovieschallenge.ui.viewModels.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment(), OnClickMoviesListener, SearchView.OnQueryTextListener {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val searchViewModel: SearchViewModel by viewModels()
    private val allMoviesAdapter: AllMoviesAdapter = AllMoviesAdapter(emptyList(), this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.searchView.setOnQueryTextListener(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configRecycler()
        configObservers()
    }

    private fun configRecycler() {
        binding.rvAllMovies.adapter = allMoviesAdapter
        val manager = GridLayoutManager(this.requireContext(), 2)
        binding.rvAllMovies.layoutManager = manager
    }

    private fun configObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            searchViewModel.getMoviesLiveData.collect { movieState ->
                when (movieState) {
                    NetworkState.Loading -> {}
                    is NetworkState.Success -> {
                        movieState.data?.let { allMoviesAdapter.setAllMoviesList(it) }
                    }
                    is NetworkState.Error -> {
                        Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        allMoviesAdapter.filter.filter(newText)
        return false
    }

    override fun onMoviesClicked(movie: DomainModel) {
        TODO("Not yet implemented")
    }
}