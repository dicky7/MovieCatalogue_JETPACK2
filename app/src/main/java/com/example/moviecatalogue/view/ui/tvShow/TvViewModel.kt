package com.example.moviecatalogue.view.ui.tvShow

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.model.data.remote.repository.DataRepository


class TvViewModel(private val mDataDataRepository: DataRepository): ViewModel() {
    fun getTvModel() = mDataDataRepository.getTvShows()
}