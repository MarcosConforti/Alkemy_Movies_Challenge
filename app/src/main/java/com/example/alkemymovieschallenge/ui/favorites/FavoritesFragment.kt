package com.example.alkemymovieschallenge.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.lottie.LottieAnimationView
import com.example.alkemymovieschallenge.R
import com.example.alkemymovieschallenge.databinding.FragmentFavoritesBinding
import com.example.alkemymovieschallenge.ui.UIState
import com.example.alkemymovieschallenge.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : Fragment(), SearchView.OnQueryTextListener {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val favoritesMoviesAdapter = FavoriteMoviesAdapter(emptyList())

    private val favoriteViewModel: FavoriteViewModel by viewModels()

    private lateinit var circularButton: View
    private lateinit var searchView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        binding.searchViewLayout.searchView.setOnQueryTextListener(this)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        circularButton = binding.searchViewLayout.circularButton
        searchView = binding.searchViewLayout.searchView
        configAnim()
        configSearchView()
        configRecyclers()
        configObservers()
    }

    private fun configSearchView() {
        circularButton.setOnClickListener {
            // Expandir o contraer el SearchView
            if (searchView.visibility == View.VISIBLE) {
                val shrinkAnimation =
                    AnimationUtils.loadAnimation(this.requireContext(), R.anim.shrink_out_animation)
                searchView.startAnimation(shrinkAnimation)
                searchView.visibility = View.GONE
            } else {
                val expandAnimation =
                    AnimationUtils.loadAnimation(this.requireContext(), R.anim.expand_in_animation)
                searchView.startAnimation(expandAnimation)
                searchView.visibility = View.VISIBLE
            }
        }
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
                    UIState.Loading -> {
                        binding.lottieAnimationView.isVisible = true
                    }

                    is UIState.Success -> {
                        binding.lottieAnimationView.isVisible = false
                        favoritesMoviesAdapter.setFavoriteList(favorite.data)
                    }

                    is UIState.Error -> {
                        binding.lottieAnimationView.isVisible = false
                        Toast.makeText(
                            requireContext(), Constants.TOAST_FAVORITE_ERROR,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    override fun onQueryTextSubmit(string: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(string: String?): Boolean {
        favoritesMoviesAdapter.filter.filter(string)
        return false
    }
}