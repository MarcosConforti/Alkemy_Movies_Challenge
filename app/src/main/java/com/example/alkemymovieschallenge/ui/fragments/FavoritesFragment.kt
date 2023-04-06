package com.example.alkemymovieschallenge.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.alkemymovieschallenge.R
import com.example.alkemymovieschallenge.databinding.FragmentFavoritesBinding
import com.example.alkemymovieschallenge.databinding.FragmentMoviesBinding

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!


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

}