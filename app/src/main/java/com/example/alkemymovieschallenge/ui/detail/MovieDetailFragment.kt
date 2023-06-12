package com.example.alkemymovieschallenge.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.alkemymovieschallenge.databinding.FragmentMoviesDetailBinding
import com.example.alkemymovieschallenge.ui.favorites.FavoriteViewModel
import com.example.alkemymovieschallenge.ui.model.FavoritesUIModel
import com.example.alkemymovieschallenge.utils.Constants
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movies_detail.tv_releaseDate
import kotlinx.android.synthetic.main.fragment_movies_detail.tv_title
import kotlinx.android.synthetic.main.fragment_movies_detail.tv_voteAverage
import kotlinx.android.synthetic.main.fragment_series_detail.tv_overview
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMoviesDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var movie: FavoritesUIModel

    private val favoriteViewModel: FavoriteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            movie = it.getParcelable("movie")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()
        binding.btnAddToFavorites.setOnClickListener { isChecked() }
        binding.btnWatchTrailer.setOnClickListener { removeFromFavorites(movie.title) }
    }

    private fun getData() {
        movie.let {
            tv_title.text = it.title
            tv_overview.text = it.overview
            tv_releaseDate.text = it.releaseDate
            tv_voteAverage.text = it.voteAverage
            Picasso.get().load(Constants.IMAGE_BASE + it.image).into(binding.ivImage)
            binding.ivBigImage.scaleType = ImageView.ScaleType.FIT_XY
            Picasso.get().load(Constants.IMAGE_BASE + it.image).into(binding.ivBigImage)
        }
    }

    private fun isChecked() {
        viewLifecycleOwner.lifecycleScope.launch {
            val isFavorite = favoriteViewModel.isChecked(movie.title)
            if (isFavorite) {
                removeFromFavorites(movie.title)
            } else {
                addToFavorites()
            }
        }
    }

    private fun addToFavorites() {
        favoriteViewModel.addToFavorites(movie)
        Toast.makeText(
            requireContext(), "${movie.title} se ha agregado a Favoritos",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun removeFromFavorites(tittle: String) {
        favoriteViewModel.removeFavorites(tittle)
        Toast.makeText(
            requireContext(), "${movie.title} se ha eliminado",
            Toast.LENGTH_SHORT
        ).show()
    }
}