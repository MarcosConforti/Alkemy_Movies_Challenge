package com.example.alkemymovieschallenge.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alkemymovieschallenge.R
import com.example.alkemymovieschallenge.databinding.FragmentMoviesBinding
import com.example.alkemymovieschallenge.domain.model.DomainModel
import com.example.alkemymovieschallenge.ui.activities.DescriptionActivity
import com.example.alkemymovieschallenge.ui.recyclerViews.OnClickMoviesListener
import com.example.alkemymovieschallenge.ui.recyclerViews.movies.latestMovies.LatestMoviesAdapter
import com.example.alkemymovieschallenge.ui.recyclerViews.movies.nowPlaying.NowPlayingMoviesAdapter
import com.example.alkemymovieschallenge.ui.recyclerViews.movies.popular.PopularMoviesAdapter
import com.example.alkemymovieschallenge.ui.recyclerViews.movies.topRated.TopRatedMoviesAdapter
import com.example.alkemymovieschallenge.ui.recyclerViews.movies.upComing.UpComingMoviesAdapter
import com.example.alkemymovieschallenge.ui.viewModels.movies.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment(), OnClickMoviesListener {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private var popularMoviesAdapter = PopularMoviesAdapter(emptyList(), this)
    private var latestMoviesAdapter = LatestMoviesAdapter(emptyList(), this)
    private var topRatedMoviesAdapter = TopRatedMoviesAdapter(emptyList(), this)
    private var upComingMoviesAdapter = UpComingMoviesAdapter(emptyList(), this)
    private var nowPlayingMoviesAdapter = NowPlayingMoviesAdapter(emptyList(), this)

    private val popularMoviesViewModel: PopularMoviesViewModel by viewModels()
    private val latestMoviesViewModel: LatestMoviesViewModel by viewModels()
    private val topRatedMoviesViewModel: TopRatedMoviesViewModel by viewModels()
    private val upComingMoviesViewModel: UpComingMoviesViewModel by viewModels()
    private val nowPlayingMoviesViewModel: NowPlayingMoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)

        binding.btnSeries.setOnClickListener{
            findNavController().navigate(R.id.seriesFragment)
        }
        /*binding.btnFavorites.setOnClickListener{
            findNavController().navigate(R.id.favoritesFragment)
        }*/
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configRecycler()

        popularMoviesViewModel.getMoviesLiveData.observe(
            viewLifecycleOwner,
            Observer { popularMovieList ->
                popularMoviesAdapter.setPopularMoviesList(
                    popularMovieList
                )
            })
        latestMoviesViewModel.getLatestMoviesLiveData.observe(
            viewLifecycleOwner,
            Observer { latestMovieList ->
                latestMoviesAdapter.setLatestMoviesList(latestMovieList
                )
            })
        topRatedMoviesViewModel.getTopRatedMoviesLiveData.observe(
            viewLifecycleOwner,
            Observer { topRatedMovieList ->
                topRatedMoviesAdapter.setTopRatedMoviesList(
                    topRatedMovieList
                )
            })
        upComingMoviesViewModel.getUpComingMoviesLiveData.observe(
            viewLifecycleOwner,
            Observer { upComingMovieList ->
                upComingMoviesAdapter.setUpComingMoviesList(
                    upComingMovieList
                )
            })
        nowPlayingMoviesViewModel.getNowPlayingMoviesLiveData.observe(
            viewLifecycleOwner,
            Observer { nowPlayingMovieList ->
                nowPlayingMoviesAdapter.setNowPlayingMoviesList(
                    nowPlayingMovieList
                )
            })
    }

    override fun onMoviesClicked(movie: DomainModel) {
        val bundle = Bundle()
        bundle.putParcelable("movies",movie)
        findNavController().navigate(R.id.detailFragment,bundle)
        /*val intent = Intent(this, DescriptionActivity::class.java)
        intent.putExtra("title", movie.title)
        intent.putExtra("releaseDate", movie.releaseDate)
        intent.putExtra("image", movie.image)
        intent.putExtra("voteAverage", movie.voteAverage)
        intent.putExtra("overview", movie.overview)
        startActivity(intent)*/
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
        binding.rvLatest.apply {
            adapter = latestMoviesAdapter
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
            layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
    }

}