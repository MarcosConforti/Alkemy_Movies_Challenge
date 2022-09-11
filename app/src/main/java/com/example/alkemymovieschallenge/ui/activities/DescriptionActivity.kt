package com.example.alkemymovieschallenge.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.alkemymovieschallenge.databinding.ActivityDescriptionBinding
import com.example.alkemymovieschallenge.ui.viewModels.movies.PopularMoviesViewModel
import com.squareup.picasso.Picasso

class DescriptionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDescriptionBinding

    private val IMAGE_BASE =  "https://image.tmdb.org/t/p/w500"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()


    }
    private fun getData(){
        val title:String = intent.getStringExtra("title").toString()
        val releaseDate:String = intent.getStringExtra("releaseDate").toString()
        val image:String = intent.getStringExtra("image").toString()
        val voteAverage:String = intent.getStringExtra("voteAverage").toString()
        val overview:String = intent.getStringExtra("overview").toString()

        binding.tvTitle.text = title
        binding.tvReleaseDate.text = releaseDate
        binding.tvVoteAverage.text = voteAverage
        binding.tvOverview.text = overview

        Picasso.get().load(IMAGE_BASE + image).into(binding.ivImage)
    }
}