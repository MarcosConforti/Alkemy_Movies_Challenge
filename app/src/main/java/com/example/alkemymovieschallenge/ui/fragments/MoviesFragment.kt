package com.example.alkemymovieschallenge.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alkemymovieschallenge.R
import com.example.alkemymovieschallenge.databinding.FragmentMoviesBinding
import com.example.alkemymovieschallenge.domain.NetworkState
import com.example.alkemymovieschallenge.domain.model.DomainModel
import com.example.alkemymovieschallenge.ui.recyclerViews.OnClickMoviesListener
import com.example.alkemymovieschallenge.ui.recyclerViews.movies.nowPlaying.NowPlayingMoviesAdapter
import com.example.alkemymovieschallenge.ui.recyclerViews.movies.popular.PopularMoviesAdapter
import com.example.alkemymovieschallenge.ui.recyclerViews.movies.topRated.TopRatedMoviesAdapter
import com.example.alkemymovieschallenge.ui.recyclerViews.movies.upComing.UpComingMoviesAdapter
import com.example.alkemymovieschallenge.ui.recyclerViews.search.movies.AllMoviesAdapter
import com.example.alkemymovieschallenge.ui.viewModels.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment(), OnClickMoviesListener {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private var popularMoviesAdapter = PopularMoviesAdapter(emptyList(), this)
    private var topRatedMoviesAdapter = TopRatedMoviesAdapter(emptyList(), this)
    private var upComingMoviesAdapter = UpComingMoviesAdapter(emptyList(), this)
    private var nowPlayingMoviesAdapter = NowPlayingMoviesAdapter(emptyList(), this)

    private val moviesViewModel: MoviesViewModel by viewModels()

    private lateinit var dialog: AlertDialog


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configRecycler()
        configObservers()

    }

    private fun configObservers() {
        moviesViewModel.getMoviesLiveData.observe(
            viewLifecycleOwner,
            Observer { movieState ->
                if (movieState is NetworkState.Success) {
                    dialog.dismiss()
                    popularMoviesAdapter.setPopularMoviesList(movieState.data.popular)
                    topRatedMoviesAdapter.setTopRatedMoviesList(movieState.data.topRated)
                    upComingMoviesAdapter.setUpComingMoviesList(movieState.data.upComing)
                    nowPlayingMoviesAdapter.setNowPlayingMoviesList(movieState.data.nowPlaying)
                } else {

                    Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
                }
            })

    }

    override fun onMoviesClicked(movie: DomainModel) {
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