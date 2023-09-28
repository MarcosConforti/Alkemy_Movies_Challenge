package com.example.alkemymovieschallenge.movies.ui


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
import com.airbnb.lottie.LottieAnimationView
import com.example.alkemymovieschallenge.core.ui.UIModel
import com.example.alkemymovieschallenge.core.ui.UIState
import com.example.alkemymovieschallenge.databinding.FragmentMoviesBinding
import com.example.alkemymovieschallenge.movies.ui.adapters.NowPlayingMoviesAdapter
import com.example.alkemymovieschallenge.movies.ui.adapters.OnMoviesClickListener
import com.example.alkemymovieschallenge.movies.ui.adapters.PopularMoviesAdapter
import com.example.alkemymovieschallenge.movies.ui.adapters.TopRatedMoviesAdapter
import com.example.alkemymovieschallenge.movies.ui.adapters.UpComingMoviesAdapter
import com.example.alkemymovieschallenge.movies.ui.model.MovieUIList
import com.example.alkemymovieschallenge.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : Fragment(), OnMoviesClickListener {

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

        initUI()
    }

    private fun initUI() {
        configAnim()
        configRecycler()
        configObservers()
    }


    private fun configAnim() {
        val animationView: LottieAnimationView = binding.lottieAnimationView
        animationView.setAnimation("progressMovie.json")
        animationView.playAnimation()

    }

    private fun configObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            moviesViewModel.getMoviesStateFlow.collect { movieState ->
                when (movieState) {
                    UIState.Loading -> {
                        loadingState()
                    }

                    is UIState.Success -> {
                        successState(movieState)
                    }

                    is UIState.Error -> {
                        errorState()
                    }
                }
            }
        }
    }

    private fun loadingState() {
        binding.lottieAnimationView.isVisible = true
        binding.scrollView.isVisible = false
    }

    private fun successState(movieState: UIState.Success<MovieUIList>) {
        binding.scrollView.isVisible = true
        binding.lottieAnimationView.isVisible = false
        popularMoviesAdapter.setPopularMoviesList(movieState.data.popular)
        topRatedMoviesAdapter.setTopRatedMoviesList(movieState.data.topRated)
        upComingMoviesAdapter.setUpComingMoviesList(movieState.data.upComing)
        nowPlayingMoviesAdapter.setNowPlayingMoviesList(movieState.data.nowPlaying)
    }

    private fun errorState() {
        binding.lottieAnimationView.isVisible = false
        Toast.makeText(requireContext(), Constants.TOAST_ERROR, Toast.LENGTH_SHORT).show()
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

    override fun onItemClicked(movie: UIModel) {
        findNavController().navigate(
            MoviesFragmentDirections.actionMoviesFragmentToMovieDetailFragment(
                movie.id
            )
        )
    }
}