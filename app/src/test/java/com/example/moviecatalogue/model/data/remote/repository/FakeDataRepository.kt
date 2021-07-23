package com.example.moviecatalogue.model.data.remote.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.model.data.entity.DetailEntity
import com.example.moviecatalogue.model.data.entity.MovieEntity
import com.example.moviecatalogue.model.data.entity.TvEntity
import com.example.moviecatalogue.model.data.remote.response.movie.MovieDetailResponse
import com.example.moviecatalogue.model.data.remote.response.movie.MovieRemote
import com.example.moviecatalogue.model.data.remote.response.tv.TvRemote
import com.example.moviecatalogue.model.data.remote.response.tv.TvShowDetailResponse
import com.example.moviecatalogue.model.data.remote.source.DataSource
import com.example.moviecatalogue.model.data.remote.source.RemoteDataSource

class FakeDataRepository (private val remoteDataSource: RemoteDataSource) : DataSource {
    override fun getMovie(): LiveData<List<MovieEntity>> {
        val getMovie = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onMovieReceive(movieResponse: List<MovieRemote>?) {
                val list = ArrayList<MovieEntity>()
                if (movieResponse != null) {
                    for (movies in movieResponse) {
                        movies.apply {
                            val movie = MovieEntity(id, title, posterPath, date)
                            list.add(movie)
                        }
                    }
                    getMovie.postValue(list)
                }
            }
        })
        return getMovie
    }

    override fun getMovieDetail(id: String): LiveData<DetailEntity> {
        val getMovieDetail = MutableLiveData<DetailEntity>()
        remoteDataSource.getDetailMovie(object : RemoteDataSource.LoadDetailMovieCallback {
            override fun onMovieDetailReceive(movieDetail: MovieDetailResponse?) {
                if (movieDetail != null) {
                    with(movieDetail) {
                        val listGenres = ArrayList<String>()

                        for (genre in genres) {
                            listGenres.add(genre.name)
                        }

                        val detailMovie = DetailEntity(
                            genres = listGenres,
                            id = this.id,
                            overview = overview,
                            date = date,
                            title = title,
                            posterPath = posterPath
                        )
                        getMovieDetail.postValue(detailMovie)
                    }
                }
            }
        }, id)
        return getMovieDetail
    }

    override fun getTvShows(): LiveData<List<TvEntity>> {
        val getTvShow = MutableLiveData<List<TvEntity>>()
        remoteDataSource.getTvShows(object : RemoteDataSource.LoadTvShowsCallback {
            override fun onTvReceive(tvResponse: List<TvRemote>?) {
                val list = ArrayList<TvEntity>()
                if (tvResponse != null) {
                    for (response in tvResponse) {
                        response.apply {
                            val tvShow = TvEntity(id, title, posterPath, date)
                            list.add(tvShow)
                        }
                    }
                    getTvShow.postValue(list)
                }
            }
        })
        return getTvShow
    }

    override fun getDetailTvShow(id: String): LiveData<DetailEntity> {
        val movieDetailResult = MutableLiveData<DetailEntity>()

        remoteDataSource.getDetailTvShow(object : RemoteDataSource.LoadDetailTvShowCallback {
            override fun onTvDetailReceive(tvShowDetail: TvShowDetailResponse?) {
                if (tvShowDetail != null) {
                    with(tvShowDetail) {
                        val listGenres = ArrayList<String>()

                        for (genre in genres) {
                            listGenres.add(genre.name)
                        }

                        val detailMovie = DetailEntity(
                            genres = listGenres,
                            id = this.id,
                            overview = overview,
                            posterPath = posterPath,
                            date = firstAirDate,
                            title = name,
                        )
                        movieDetailResult.postValue(detailMovie)
                    }
                }
            }
        }, id)
        return movieDetailResult
    }
}