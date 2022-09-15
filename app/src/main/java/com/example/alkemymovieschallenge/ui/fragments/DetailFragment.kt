package com.example.alkemymovieschallenge.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.alkemymovieschallenge.data.database.entities.MoviesEntities
import com.example.alkemymovieschallenge.databinding.FragmentDetailBinding
import com.example.alkemymovieschallenge.domain.model.DomainModel
import com.example.alkemymovieschallenge.ui.viewModels.favorites.FavoriteViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500"

    private lateinit var movie: DomainModel

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
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_title.text = movie.title
        tv_overview.text = movie.overview
        tv_releaseDate.text = movie.releaseDate
        tv_voteAverage.text = movie.voteAverage
        Picasso.get().load(IMAGE_BASE + movie.image).into(binding.ivImage)

        binding.btnAddToFavorites.setOnClickListener {
            favoriteViewModel.insertMovie(
                MoviesEntities(
                    movie.id, movie.title, movie.voteAverage,
                    movie.releaseDate, movie.overview, movie.image
                )
            )
            Toast.makeText(requireContext(), "Se ha añadido a favoritos", Toast.LENGTH_SHORT).show()
        }
    }

}