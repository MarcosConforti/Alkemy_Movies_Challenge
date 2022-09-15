package com.example.alkemymovieschallenge.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alkemymovieschallenge.R
import com.example.alkemymovieschallenge.databinding.FragmentSeriesBinding
import com.example.alkemymovieschallenge.domain.model.DomainTvModel
import com.example.alkemymovieschallenge.ui.recyclerViews.OnClickTvListener
import com.example.alkemymovieschallenge.ui.recyclerViews.tv.*
import com.example.alkemymovieschallenge.ui.viewModels.tv.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeriesFragment : Fragment(), OnClickTvListener {

    private var _binding: FragmentSeriesBinding? = null
    private val binding get() = _binding!!

    private var popularTvAdapter = PopularTvAdapter(emptyList(), this)
    private var latestTvAdapter = LatestTvAdapter(emptyList(), this)
    private var topRatedTvAdapter = TopRatedTvAdapter(emptyList(), this)
    private var airingTodayTvAdapter = AiringTodayTvAdapter(emptyList(), this)
    private var onTheAirTvAdapter = OnTheAirTvAdapter(emptyList(), this)

    private val popularTvViewModel: PopularTvViewModel by viewModels()
    private val latestTvViewModel: LatestTvViewModel by viewModels()
    private val topRatedTvViewModel: TopRatedTvViewModel by viewModels()
    private val airingTodayTvViewModel: AiringTodayTvViewModel by viewModels()
    private val onTheAirTvViewModel: OnTheAirTvViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSeriesBinding.inflate(inflater, container, false)

        binding.btnMovies.setOnClickListener {
            findNavController().navigate(R.id.moviesFragment)
        }
        /*binding.btnFavorites.setOnClickListener {
            findNavController().navigate(R.id.favoritesFragment)
        }*/
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configRecycler()

        popularTvViewModel.getPopularTvLiveData.observe(
            viewLifecycleOwner,
            Observer { popularTvList ->
                popularTvAdapter.setPopularTvList(
                    popularTvList
                )
            })
        latestTvViewModel.getLatestTvLiveData.observe(
            viewLifecycleOwner,
            Observer { latestTvList ->
                latestTvAdapter.setLatestTvList(
                    latestTvList
                )
            })
        topRatedTvViewModel.getTopRatedTvLiveData.observe(
            viewLifecycleOwner,
            Observer { topRatedTvList ->
                topRatedTvAdapter.setTopRatedTvList(
                    topRatedTvList
                )
            })
        airingTodayTvViewModel.getAiringTodayTvLiveData.observe(
            viewLifecycleOwner,
            Observer { airingTvList ->
                airingTodayTvAdapter.setAiringTodayTvList(
                    airingTvList
                )
            })
        onTheAirTvViewModel.getOnTheAirTvLiveData.observe(
            viewLifecycleOwner,
            Observer { onTheAirTvList ->
                onTheAirTvAdapter.setOnTheAirTvList(
                    onTheAirTvList
                )
            })
    }

    override fun onTvClicked(tv: DomainTvModel) {

    }

    private fun configRecycler() {
        binding.rvPopularTv.apply {
            adapter = popularTvAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
        binding.rvLatestTv.apply {
            adapter = latestTvAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
        binding.rvTopRatedTv.apply {
            adapter = topRatedTvAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
        binding.rvAiringTodayTv.apply {
            adapter = airingTodayTvAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
        binding.rvOnTheAirTv.apply {
            adapter = airingTodayTvAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }

    }
}