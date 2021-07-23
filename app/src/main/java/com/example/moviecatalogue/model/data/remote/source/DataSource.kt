package com.example.moviecatalogue.model.data.remote.source

import androidx.lifecycle.LiveData
import com.example.moviecatalogue.model.data.entity.TvEntity
import com.example.moviecatalogue.model.data.entity.DetailEntity
import com.example.moviecatalogue.model.data.entity.MovieEntity

interface DataSource {
    fun getMovie(): LiveData<List<MovieEntity>>
    fun getMovieDetail(id: String): LiveData<DetailEntity>
    fun getTvShows(): LiveData<List<TvEntity>>
    fun getDetailTvShow(id: String): LiveData<DetailEntity>
}