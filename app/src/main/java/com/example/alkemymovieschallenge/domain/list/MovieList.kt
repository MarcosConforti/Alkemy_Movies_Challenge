package com.example.alkemymovieschallenge.domain.list

import com.example.alkemymovieschallenge.domain.model.DomainMoviesModel

data class MovieList(
     val popular:List<DomainMoviesModel>,
     val upComing:List<DomainMoviesModel>,
     val nowPlaying:List<DomainMoviesModel>,
     val topRated:List<DomainMoviesModel>,
)
