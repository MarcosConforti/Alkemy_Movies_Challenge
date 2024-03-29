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
import com.example.alkemymovieschallenge.ui.UIState
import com.example.alkemymovieschallenge.ui.model.UIModel
import com.example.alkemymovieschallenge.ui.series.adapters.AiringTodayTvAdapter
import com.example.alkemymovieschallenge.ui.series.adapters.OnTheAirTvAdapter
import com.example.alkemymovieschallenge.ui.series.adapters.PopularTvAdapter
import com.example.alkemymovieschallenge.ui.series.adapters.TopRatedTvAdapter
import com.example.alkemymovieschallenge.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SeriesFragment : Fragment(), OnSeriesClickListener {

    private var _binding: FragmentSeriesBinding? = null
    private val binding get() = _binding!!

    private var popularTvAdapter = PopularTvAdapter(emptyList(), this)
    private var topRatedTvAdapter = TopRatedTvAdapter(emptyList(), this)
    private var airingTodayTvAdapter = AiringTodayTvAdapter(emptyList(), this)
    private var onTheAirTvAdapter = OnTheAirTvAdapter(emptyList(), this)

    private val seriesViewModel: SeriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
            seriesViewModel.getSeriesUIState.collect { seriesState ->
                when (seriesState) {
                    UIState.Loading -> {
                        binding.lottieAnimationView.isVisible = true
                        binding.scrollView.isVisible = false
                    }
                    is UIState.Success -> {
                        binding.lottieAnimationView.isVisible = false
                        binding.scrollView.isVisible = true
                        popularTvAdapter.setPopularTvList(seriesState.data.popularTv)
                        onTheAirTvAdapter.setOnTheAirTvList(seriesState.data.onTheAir)
                        airingTodayTvAdapter.setAiringTodayTvList(seriesState.data.airingToday)
                        topRatedTvAdapter.setTopRatedTvList(seriesState.data.topRated)
                    }
                    is UIState.Error -> {
                        binding.lottieAnimationView.isVisible = false
                        Toast.makeText(requireContext(), Constants.TOAST_ERROR,
                            Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
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
            adapter = onTheAirTvAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }

    }

    override fun onItemClicked(series: UIModel) {
        val bundle = Bundle().apply {
            putParcelable("series",series)
        }
        findNavController().navigate(R.id.detailSeriesFragment, bundle)
    }
}