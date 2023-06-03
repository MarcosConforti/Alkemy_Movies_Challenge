package com.example.alkemymovieschallenge.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alkemymovieschallenge.R
import com.example.alkemymovieschallenge.databinding.FragmentFavoritesBinding
import com.example.alkemymovieschallenge.databinding.FragmentMoviesBinding
import kotlinx.coroutines.launch

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

   // private lateinit var favoritesMoviesAdapter = favoritesMoviesAdapter()

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

       // configObservers()
        //configRecyclers()
    }

  /*  private fun configObservers(){
        viewLifecycleOwner.lifecycleScope.launch {
            favoriteViewModel.favoriteLiveData.observe(//)
        }

    }

    private fun configRecyclers() {
        binding.rvFavoriteMovies.apply {
            adapter = favoritesMoviesAdapter()
            LinearLayoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false)
        }
    }*/

}