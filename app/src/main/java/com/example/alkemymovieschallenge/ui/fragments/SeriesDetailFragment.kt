package com.example.alkemymovieschallenge.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.alkemymovieschallenge.databinding.FragmentSeriesDetailBinding
import com.example.alkemymovieschallenge.domain.model.DomainTvModel
import com.example.alkemymovieschallenge.utils.Constants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_series_detail.*


class SeriesDetailFragment : Fragment() {

    private var _binding: FragmentSeriesDetailBinding? = null
    private val binding get() = _binding!!

    private  var series: DomainTvModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {

            series = it.getParcelable("series")!!

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSeriesDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        series.let {
            tv_title.text = it?.title
            tv_overview.text = it?.overview
            tv_releaseDate.text = it?.releaseDate
            tv_voteAverage.text = it?.voteAverage
            Picasso.get().load(Constants.IMAGE_BASE + it?.image).into(binding.ivImage)
        }
    }

}