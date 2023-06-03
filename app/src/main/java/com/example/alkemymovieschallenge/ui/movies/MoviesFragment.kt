package com.example.alkemymovieschallenge.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alkemymovieschallenge.R
import com.example.alkemymovieschallenge.databinding.FragmentMoviesBinding
import com.example.alkemymovieschallenge.ui.UIState
import com.example.alkemymovieschallenge.ui.model.MoviesUIModel
import com.example.alkemymovieschallenge.ui.movies.adapters.OnClickMoviesListener
import com.example.alkemymovieschallenge.ui.movies.adapters.nowPlaying.NowPlayingMoviesAdapter
import com.example.alkemymovieschallenge.ui.movies.adapters.popular.PopularMoviesAdapter
import com.example.alkemymovieschallenge.ui.movies.adapters.topRated.TopRatedMoviesAdapter
import com.example.alkemymovieschallenge.ui.movies.adapters.upComing.UpComingMoviesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : Fragment(), OnClickMoviesListener {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private var popularMoviesAdapter = PopularMoviesAdapter(emptyList(), this)
    private var topRatedMoviesAdapter = TopRatedMoviesAdapter(emptyList(), this)
    private var upComingMoviesAdapter = UpComingMoviesAdapter(emptyList(), this)
    private var nowPlayingMoviesAdapter = NowPlayingMoviesAdapter(emptyList(), this)

    private val moviesViewModel: MoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configRecycler()
        configObservers()

    }

    private fun configObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            moviesViewModel.getMoviesLiveData.collect { movieState ->
                when (movieState) {
                    UIState.Loading -> binding.progressBar.isVisible = true
                    is UIState.Success -> {
                        binding.progressBar.isVisible = false
                        popularMoviesAdapter.setPopularMoviesList(movieState.data.popular)
                        topRatedMoviesAdapter.setTopRatedMoviesList(movieState.data.topRated)
                        upComingMoviesAdapter.setUpComingMoviesList(movieState.data.upComing)
                        nowPlayingMoviesAdapter.setNowPlayingMoviesList(movieState.data.nowPlaying)
                    }

                    is UIState.Error -> {
                        binding.progressBar.isVisible = false
                        Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onMoviesClicked(movie: MoviesUIModel) {
        val movieBundle = Bundle()
        movieBundle.putParcelable("movie", movie)
        findNavController().navigate(R.id.movieDetailFragment, movieBundle)
    }

    private fun configRecycler() {
        binding.rvPopular.apply {
            adapter = popularMoviesAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
        binding.rvTopRated.apply {
            adapter = topRatedMoviesAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
        binding.rvUpComing.apply {
            adapter = upComingMoviesAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
        binding.rvNowPlaying.apply {
            adapter = nowPlayingMoviesAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
    }
}