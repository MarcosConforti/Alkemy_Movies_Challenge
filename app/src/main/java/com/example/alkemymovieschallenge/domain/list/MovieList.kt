package com.example.alkemymovieschallenge.domain.list

import com.example.alkemymovieschallenge.domain.model.DomainModel

data class MovieList(
     val popular:List<DomainModel>,
     val upComing:List<DomainModel>,
     val nowPlaying:List<DomainModel>,
     val topRated:List<DomainModel>,

)
