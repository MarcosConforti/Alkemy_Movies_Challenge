package com.example.alkemymovieschallenge.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alkemymovieschallenge.R
import com.example.alkemymovieschallenge.databinding.FragmentSeriesBinding
import com.example.alkemymovieschallenge.domain.NetworkState
import com.example.alkemymovieschallenge.domain.model.DomainTvModel
import com.example.alkemymovieschallenge.ui.recyclerViews.OnClickTvListener
import com.example.alkemymovieschallenge.ui.recyclerViews.tv.airing.AiringTodayTvAdapter
import com.example.alkemymovieschallenge.ui.recyclerViews.tv.onTheAir.OnTheAirTvAdapter
import com.example.alkemymovieschallenge.ui.recyclerViews.tv.popular.PopularTvAdapter
import com.example.alkemymovieschallenge.ui.recyclerViews.tv.topRated.TopRatedTvAdapter
import com.example.alkemymovieschallenge.ui.viewModels.SeriesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeriesFragment : Fragment(), OnClickTvListener {

    private var _binding: FragmentSeriesBinding? = null
    private val binding get() = _binding!!

    private var popularTvAdapter = PopularTvAdapter(emptyList(), this)
    private var topRatedTvAdapter = TopRatedTvAdapter(emptyList(), this)
    private var airingTodayTvAdapter = AiringTodayTvAdapter(emptyList(), this)
    private var onTheAirTvAdapter = OnTheAirTvAdapter(emptyList(), this)

    private val seriesViewModel: SeriesViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSeriesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configRecycler()

        seriesViewModel.getSeriesLiveData.observe(viewLifecycleOwner, Observer { movieState ->
            if (movieState is NetworkState.Success) {
                popularTvAdapter.setPopularTvList(movieState.data.popularTv)
                onTheAirTvAdapter.setOnTheAirTvList(movieState.data.onTheAir)
                airingTodayTvAdapter.setAiringTodayTvList(movieState.data.airingToday)
                topRatedTvAdapter.setTopRatedTvList(movieState.data.topRated)
            }
            else{
                Toast.makeText(requireContext(),"error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onTvClicked(series: DomainTvModel) {
        val serieBundle = Bundle()
        serieBundle.putParcelable("series", series)
        findNavController().navigate(R.id.seriesDetailFragment, serieBundle)
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