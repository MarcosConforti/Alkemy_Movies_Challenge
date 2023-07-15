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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alkemymovieschallenge.R
import com.example.alkemymovieschallenge.databinding.FragmentDetailSeriesBinding
import com.example.alkemymovieschallenge.ui.UIState
import com.example.alkemymovieschallenge.ui.favorites.FavoriteViewModel
import com.example.alkemymovieschallenge.ui.model.UIModel
import com.example.alkemymovieschallenge.utils.Constants
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailSeriesFragment : Fragment() {

    private var _binding: FragmentDetailSeriesBinding? = null
    private val binding get() = _binding!!

    private lateinit var series: UIModel

    private val favoriteViewModel: FavoriteViewModel by viewModels()
    /*private val detailViewModel: DetailViewModel by viewModels()

    private var alternativeAdapter = AlternativeSeriesTitleAdapter(emptyList())*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            series = it.getParcelable("series")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailSeriesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()
        binding.btnAddToFavorites.setOnClickListener { isChecked() }
    //    getAlternativeTitles(id = series.id.toString())
    }

    private fun getData() {
        series.let {
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
            val isFavorite = favoriteViewModel.isChecked(series.title)
            if (isFavorite) {
                removeFromFavorites(series.title)
            } else {
                addToFavorites()
            }
        }
    }

    private fun addToFavorites() {
        favoriteViewModel.addToFavorites(series)
        binding.btnAddToFavorites.setImageResource(R.drawable.ic_favorite)
        Toast.makeText(
            requireContext(), series.title + " " + Constants.TOAST_ADD_FAVORITES,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun removeFromFavorites(title: String) {
        favoriteViewModel.removeFavorites(title)
        binding.btnAddToFavorites.setImageResource(R.drawable.ic_favorite_border)
        Toast.makeText(
            requireContext(), series.title + " " + Constants.TOAST_REMOVE_FAVORITES,
            Toast.LENGTH_SHORT
        ).show()
    }

    /*private fun getAlternativeTitles(id: String) {
        configAlternativeRecycler()
        viewLifecycleOwner.lifecycleScope.launch {
            detailViewModel.getAlternativeSeriesTitles(id)
            detailViewModel.alternativeState.collect { alternativeTitle ->
                when (alternativeTitle) {
                    UIState.Loading -> {}
                    is UIState.Success -> {
                        alternativeAdapter.setAlternativeSeriesTitleList(alternativeTitle.data)
                    }
                    is UIState.Error -> {
                        Toast.makeText(
                            requireContext(), Constants.TOAST_ALTERNATIVE_TITLE_ERROR,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun configAlternativeRecycler() {
        binding.rvAlternativeSeriesTitle.apply {
            adapter = alternativeAdapter
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.HORIZONTAL, false
            )
        }
    }*/
}