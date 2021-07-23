package com.example.moviecatalogue.view.ui.movie

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.model.data.remote.repository.DataRepository
import com.example.moviecatalogue.utils.DataMovie

class MovieViewModel(private val mDataDataRepository: DataRepository): ViewModel() {
    fun getMovieModel() = mDataDataRepository.getMovie()
}