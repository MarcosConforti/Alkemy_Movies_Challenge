package com.example.alkemymovieschallenge.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.lottie.LottieAnimationView
import com.example.alkemymovieschallenge.databinding.FragmentFavoritesBinding
import com.example.alkemymovieschallenge.ui.UIState
import com.example.alkemymovieschallenge.ui.favorites.adapter.movies.FavoriteMoviesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val favoritesMoviesAdapter = FavoriteMoviesAdapter(emptyList())

    private val favoriteViewModel: FavoriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configAnim()
        configRecyclers()
        configObservers()
    }

    private fun configAnim() {
        val animationView: LottieAnimationView = binding.lottieAnimationView
        animationView.setAnimation("progressMovie.json")
        animationView.playAnimation()

    }

    private fun configRecyclers() {
        binding.rvFavoriteMovies.adapter = favoritesMoviesAdapter
        val manager = GridLayoutManager(requireContext(), 2)
        binding.rvFavoriteMovies.layoutManager = manager
    }

    private fun configObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            favoriteViewModel.getFavorites()
            favoriteViewModel.favoriteUIState.collect { favorite ->
                when (favorite) {
                    UIState.Loading -> { binding.lottieAnimationView.isVisible = true
                    }
                    is UIState.Success -> {
                        binding.lottieAnimationView.isVisible = false
                        favoritesMoviesAdapter.setFavoriteList(favorite.data)
                    }

                    is UIState.Error -> {
                        binding.lottieAnimationView.isVisible = false
                        Toast.makeText(
                            requireContext(), "Error en Favoritos",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}