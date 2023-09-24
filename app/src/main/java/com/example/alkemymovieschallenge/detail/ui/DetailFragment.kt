package com.example.alkemymovieschallenge.detail.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.alkemymovieschallenge.R
import com.example.alkemymovieschallenge.databinding.FragmentDetailBinding
import com.example.alkemymovieschallenge.favorites.ui.FavoriteViewModel
import com.example.alkemymovieschallenge.core.ui.UIModel
import com.example.alkemymovieschallenge.core.ui.UIState
import com.example.alkemymovieschallenge.utils.Constants
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private var movie: UIModel? = null

    private val favoriteViewModel: FavoriteViewModel by viewModels()
    private val detailViewModel: DetailViewModel by viewModels()

    private val args: DetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()

    }

    private fun initUI() {
        detailViewModel.getMovies(args.movieId)
        observeMovieDetail()
    }

    private fun observeMovieDetail() {
        viewLifecycleOwner.lifecycleScope.launch {
            detailViewModel.detailState.collect { state ->
                when (state) {
                    UIState.Loading -> {}
                    is UIState.Success -> {
                        successState(state)
                    }
                    is UIState.Error -> {
                        Log.e("Detail", "Error al cargar datos")
                    }


                }
            }
        }
    }

    private fun successState(state: UIState.Success<UIModel>) {

        movie = state.data

        binding.tvTitle.text = state.data.title
        binding.tvOverview.text = state.data.overview
        binding.tvReleaseDate.text = state.data.releaseDate
        binding.tvVoteAverage.text = state.data.voteAverage
        binding.ivBigImage.scaleType = ImageView.ScaleType.FIT_XY
        Picasso.get().load(Constants.IMAGE_BASE + state.data.image).into(binding.ivBigImage)

        binding.btnAddToFavorites.setOnClickListener { isChecked() }
    }

    private fun isChecked() {
        viewLifecycleOwner.lifecycleScope.launch {
            val isFavorite = favoriteViewModel.isChecked(movie!!.title)
            if (isFavorite) {
                removeFromFavorites(movie!!.title)
            } else {
                addToFavorites(movie!!)
            }
        }
    }

    private fun addToFavorites(movieData:UIModel) {
        favoriteViewModel.addToFavorites(movieData)
        binding.btnAddToFavorites.setImageResource(R.drawable.ic_favorite)
        Toast.makeText(
            requireContext(), movie!!.title + " " + Constants.TOAST_ADD_FAVORITES,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun removeFromFavorites(title: String) {
        favoriteViewModel.removeFavorites(title)
        binding.btnAddToFavorites.setImageResource(R.drawable.ic_favorite_border)
        Toast.makeText(
            requireContext(), movie!!.title + " " + Constants.TOAST_REMOVE_FAVORITES,
            Toast.LENGTH_SHORT
        ).show()
    }
}