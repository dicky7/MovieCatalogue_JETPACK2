package com.example.moviecatalogue.view.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.MovieModel
import com.example.moviecatalogue.model.data.entity.DetailEntity
import com.example.moviecatalogue.model.data.remote.repository.DataRepository
import com.example.moviecatalogue.utils.DataMovie



class DetailViewModel(private val mDataRepository: DataRepository): ViewModel() {
    companion object{
        const val TV_SHOW_DETAIL = "tvShowDetail"
        const val MOVIE_DETAIL = "movieDetail"
    }
    private lateinit var detailEntity: LiveData<DetailEntity>

    fun setDataDetail(movieId: String, detailId: String){
        when(detailId){
            TV_SHOW_DETAIL ->{
                detailEntity = mDataRepository.getDetailTvShow(movieId)
            }
            MOVIE_DETAIL ->{
                detailEntity = mDataRepository.getMovieDetail(movieId)
            }
        }
    }

    fun getDataDetail() = detailEntity
}