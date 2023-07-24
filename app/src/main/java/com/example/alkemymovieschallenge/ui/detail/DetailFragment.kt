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
import com.example.alkemymovieschallenge.R
import com.example.alkemymovieschallenge.databinding.FragmentDetailBinding
import com.example.alkemymovieschallenge.ui.favorites.FavoriteViewModel
import com.example.alkemymovieschallenge.ui.model.UIModel
import com.example.alkemymovieschallenge.utils.Constants
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var movie: UIModel

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
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()
        binding.btnAddToFavorites.setOnClickListener { isChecked() }
    }

    private fun getData() {
        movie.let {
            binding.tvTitle.text = it.title
            binding.tvOverview.text = it.overview
            binding.tvReleaseDate.text = it.releaseDate
            binding.tvVoteAverage.text = it.voteAverage
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
        binding.btnAddToFavorites.setImageResource(R.drawable.ic_favorite)
        Toast.makeText(
            requireContext(), movie.title + " " + Constants.TOAST_ADD_FAVORITES,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun removeFromFavorites(title: String) {
        favoriteViewModel.removeFavorites(title)
        binding.btnAddToFavorites.setImageResource(R.drawable.ic_favorite_border)
        Toast.makeText(
            requireContext(), movie.title + " " + Constants.TOAST_REMOVE_FAVORITES,
            Toast.LENGTH_SHORT
        ).show()
    }
}