package com.example.moviecatalogue.model.data.remote.source

import android.util.Log
import com.example.moviecatalogue.BuildConfig.API_KEY
import com.example.moviecatalogue.model.data.remote.response.movie.MovieRemote
import com.example.moviecatalogue.model.data.remote.response.movie.MovieDetailResponse
import com.example.moviecatalogue.model.data.remote.response.movie.MoviesResponse
import com.example.moviecatalogue.model.data.remote.response.tv.TvRemote
import com.example.moviecatalogue.model.data.remote.response.tv.TvShowDetailResponse
import com.example.moviecatalogue.model.data.remote.response.tv.TvShowResponse
import com.example.moviecatalogue.model.api.RetrofitClient
import com.example.moviecatalogue.utils.EspressoIdlingResource

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    companion object {
        const val TAG = "Remote Data Source"

        @Volatile
        private var instance: RemoteDataSource? = null
        fun getInstance(): RemoteDataSource = instance ?: synchronized(this) {
            RemoteDataSource().apply { instance = this }
        }
    }

    fun getMovies(callback: LoadMoviesCallback) {
        EspressoIdlingResource.increment()
        RetrofitClient.getApiService().getListMovie(API_KEY)
            .enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                EspressoIdlingResource.decrement()
                callback.onMovieReceive(response.body()?.results)
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                EspressoIdlingResource.decrement()
                Log.e(TAG, "Failure ${t.message}")

            }
        })
    }

    fun getDetailMovie(callback: LoadDetailMovieCallback, id: String) {
        EspressoIdlingResource.increment()
        RetrofitClient.getApiService().getMovieDetail(id, API_KEY)
            .enqueue(object : Callback<MovieDetailResponse> {
            override fun onResponse(call: Call<MovieDetailResponse>, response: Response<MovieDetailResponse>) {
                EspressoIdlingResource.decrement()
                callback.onMovieDetailReceive(response.body())

            }
            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                EspressoIdlingResource.decrement()
                Log.e(TAG, "getDetailMovies onFailure : ${t.message}")
            }
        })
    }

    fun getTvShows(callback: LoadTvShowsCallback) {
        EspressoIdlingResource.increment()
        RetrofitClient.getApiService().getListTv(API_KEY)
            .enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(call: Call<TvShowResponse>, response: Response<TvShowResponse>) {
                EspressoIdlingResource.decrement()
                callback.onTvReceive(response.body()?.results)

            }
            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                EspressoIdlingResource.decrement()
                Log.e(TAG, "Failure ${t.message}")
            }
        })
    }

    fun getDetailTvShow(callback: LoadDetailTvShowCallback, id: String) {
        EspressoIdlingResource.increment()
        RetrofitClient.getApiService().getTvDetail(id, API_KEY)
            .enqueue(object : Callback<TvShowDetailResponse> {
            override fun onResponse(call: Call<TvShowDetailResponse>, response: Response<TvShowDetailResponse>) {
                EspressoIdlingResource.decrement()
                callback.onTvDetailReceive(response.body())
            }
            override fun onFailure(call: Call<TvShowDetailResponse>, t: Throwable) {
                EspressoIdlingResource.decrement()
                Log.e(TAG, "getDetailsTVShows onFailure : ${t.message}")
            }
        })
    }

    interface LoadMoviesCallback {
        fun onMovieReceive(movieResponse : List<MovieRemote>?)
    }

    interface LoadDetailMovieCallback {
        fun onMovieDetailReceive(movieDetail : MovieDetailResponse?)
    }

    interface LoadTvShowsCallback {
        fun onTvReceive(tvResponse : List<TvRemote>?)
    }

    interface LoadDetailTvShowCallback {
        fun onTvDetailReceive(tvShowDetail: TvShowDetailResponse?)
    }
}