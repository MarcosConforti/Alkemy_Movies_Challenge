package com.example.alkemymovieschallenge.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alkemymovieschallenge.databinding.FragmentFavoritesBinding
import com.example.alkemymovieschallenge.ui.UIState
import com.example.alkemymovieschallenge.ui.favorites.adapter.movies.FavoriteMoviesAdapter
import com.example.alkemymovieschallenge.ui.favorites.adapter.movies.OnClickFavoriteMoviesListener
import com.example.alkemymovieschallenge.ui.model.FavoritesUIModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class FavoritesFragment : Fragment(), OnClickFavoriteMoviesListener {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private var favoritesMoviesAdapter = FavoriteMoviesAdapter(emptyList(),this)

    private val favoriteViewModel:FavoriteViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        /*binding.btnMovies.setOnClickListener{
            findNavController().navigate(R.id.moviesFragment)
        }
        binding.btnSeries.setOnClickListener{
            findNavController().navigate(R.id.seriesFragment)
        }*/
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configRecyclers()
        configObservers()
    }

  private fun configObservers(){
        viewLifecycleOwner.lifecycleScope.launch {
            favoriteViewModel.favoriteLiveData.collect{favorite->
                when(favorite){
                    UIState.Loading -> {}
                    is UIState.Success -> {
                        favoritesMoviesAdapter.setFavoriteMovieList(favorite.data)
                    }
                    is UIState.Error -> {
                        Toast.makeText(requireContext(),"Error en Favoritos",
                            Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun configRecyclers() {
        binding.rvFavoriteMovies.apply {
            adapter = favoritesMoviesAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,

                false)
        }
    }

    override fun onMoviesClicked(favorite: FavoritesUIModel) {
        TODO("Not yet implemented")
    }
}