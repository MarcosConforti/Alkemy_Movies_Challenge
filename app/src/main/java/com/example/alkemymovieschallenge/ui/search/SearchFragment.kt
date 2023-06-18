package com.example.alkemymovieschallenge.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.lottie.LottieAnimationView
import com.example.alkemymovieschallenge.R
import com.example.alkemymovieschallenge.databinding.FragmentSearchBinding
import com.example.alkemymovieschallenge.ui.UIState
import com.example.alkemymovieschallenge.ui.model.UIModel
import com.example.alkemymovieschallenge.ui.movies.adapters.OnClickMoviesListener
import com.example.alkemymovieschallenge.ui.search.adapters.movies.AllMoviesAdapter
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
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.searchView.setOnQueryTextListener(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configAnim()
        configRecycler()
        configObservers()
    }

    private fun configAnim() {
        val animationView: LottieAnimationView = binding.lottieAnimationView
        animationView.setAnimation("progressMovie.json")
        animationView.playAnimation()

    }

    private fun configRecycler() {
        binding.rvAllMovies.adapter = allMoviesAdapter
        val manager = GridLayoutManager(this.requireContext(), 2)
        binding.rvAllMovies.layoutManager = manager
    }

    private fun configObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            searchViewModel.getAllMoviesState.collect { movieState ->
                when (movieState) {
                    UIState.Loading -> {
                        binding.lottieAnimationView.isVisible = true
                        binding.scrollView.isVisible = false
                    }
                    is UIState.Success -> {
                        binding.lottieAnimationView.isVisible = false
                        binding.scrollView.isVisible = true
                        movieState.data.let { allMoviesAdapter.setAllMoviesList(it) }
                    }
                    is UIState.Error -> {
                        binding.lottieAnimationView.isVisible = false
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

    override fun onMoviesClicked(data: UIModel) {
        val bundle = Bundle().apply {
            putParcelable(
                "data",data)
        }
        findNavController().navigate(R.id.detailFragment, bundle)
    }
}