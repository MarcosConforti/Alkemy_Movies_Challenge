package com.example.alkemymovieschallenge.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.alkemymovieschallenge.databinding.FragmentMoviesDetailBinding
import com.example.alkemymovieschallenge.domain.model.DomainModel
import com.example.alkemymovieschallenge.ui.viewModels.FavoriteViewModel
import com.example.alkemymovieschallenge.utils.Constants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movies_detail.*
import kotlinx.android.synthetic.main.item_grid_list.tv_title
import kotlinx.android.synthetic.main.item_grid_list.tv_voteAverage

class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMoviesDetailBinding? = null
    private val binding get() = _binding!!

    private  var movie: DomainModel? = null

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
        _binding = FragmentMoviesDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movie.let {
            tv_title.text = it?.title
            tv_overview.text = it?.overview
            tv_releaseDate.text = it?.releaseDate
            tv_voteAverage.text = it?.voteAverage
            Picasso.get().load(Constants.IMAGE_BASE + it?.image).into(binding.ivImage)
        }



        /*binding.btnAddToFavorites.setOnClickListener {
            favoriteViewModel.insertMovie(
                MoviesEntities(
                    movie.id, movie.title, movie.voteAverage,
                    movie.releaseDate, movie.overview, movie.image
                )
            )
            Toast.makeText(requireContext(), "Se ha añadido a favoritos", Toast.LENGTH_SHORT).show()
        }*/
    }

}