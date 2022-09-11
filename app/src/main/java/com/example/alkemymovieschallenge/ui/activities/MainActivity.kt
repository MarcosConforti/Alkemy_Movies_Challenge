package com.example.alkemymovieschallenge.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.alkemymovieschallenge.databinding.ActivityMainBinding
import com.example.alkemymovieschallenge.domain.model.DomainModel
import com.example.alkemymovieschallenge.ui.recyclerViews.OnClickMoviesListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnClickMoviesListener {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*binding.btnMovies.setOnClickListener {
            val intent = Intent(this, SeriesFragment::class.java)
            startActivity(intent)
        }*/

    }


    override fun onMoviesClicked(movie: DomainModel) {
        val intent = Intent(this, DescriptionActivity::class.java)
        intent.putExtra("title", movie.title)
        intent.putExtra("releaseDate", movie.releaseDate)
        intent.putExtra("image", movie.image)
        intent.putExtra("voteAverage", movie.voteAverage)
        intent.putExtra("overview", movie.overview)
        startActivity(intent)
    }
    /* override fun onCreateOptionsMenu(menu: Menu): Boolean {
         menuInflater.inflate(R.menu.pokeapp_options_menu, menu)
         val item = menu.findItem(R.id.searchView)
         val searchView = item.actionView as SearchView
         searchView.queryHint = "Search by name or id"
         searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
             override fun onQueryTextSubmit(query: String): Boolean {
                 return false
             }
             override fun onQueryTextChange(newText: String): Boolean {
                 adapter.filter.filter(newText)
                 return false
             }
         })
         return true
     }*/
}