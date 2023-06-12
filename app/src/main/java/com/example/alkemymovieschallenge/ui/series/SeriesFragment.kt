package com.example.alkemymovieschallenge.ui.series

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.LottieAnimationView
import com.example.alkemymovieschallenge.R
import com.example.alkemymovieschallenge.databinding.FragmentSeriesBinding
import com.example.alkemymovieschallenge.domain.NetworkState
import com.example.alkemymovieschallenge.domain.model.DomainTvModel
import com.example.alkemymovieschallenge.ui.series.adapters.OnClickTvListener
import com.example.alkemymovieschallenge.ui.series.adapters.airing.AiringTodayTvAdapter
import com.example.alkemymovieschallenge.ui.series.adapters.onTheAir.OnTheAirTvAdapter
import com.example.alkemymovieschallenge.ui.series.adapters.popular.PopularTvAdapter
import com.example.alkemymovieschallenge.ui.series.adapters.topRated.TopRatedTvAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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

        configAnim()
        configRecycler()
        configObservers()
    }

    private fun configAnim(){
        val animationView: LottieAnimationView = binding.lottieAnimationView
        animationView.setAnimation("progressMovie.json")
        animationView.playAnimation()

    }

    private fun configObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            seriesViewModel.getSeriesLiveData.collect { seriesState ->
                when (seriesState) {
                    NetworkState.Loading -> {
                        binding.lottieAnimationView.isVisible = true
                        binding.scrollView.isVisible = false
                    }
                    is NetworkState.Success -> {
                        binding.lottieAnimationView.isVisible = false
                        binding.scrollView.isVisible = true
                        popularTvAdapter.setPopularTvList(seriesState.data.popularTv)
                        onTheAirTvAdapter.setOnTheAirTvList(seriesState.data.onTheAir)
                        airingTodayTvAdapter.setAiringTodayTvList(seriesState.data.airingToday)
                        topRatedTvAdapter.setTopRatedTvList(seriesState.data.topRated)
                    }
                    is NetworkState.Error -> {
                        binding.lottieAnimationView.isVisible = false
                        Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
    override fun onTvClicked(series: DomainTvModel) {
        val seriesBundle = Bundle()
        seriesBundle.putParcelable("series", series)
        findNavController().navigate(R.id.seriesDetailFragment, seriesBundle)
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