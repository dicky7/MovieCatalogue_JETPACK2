package com.example.moviecatalogue.di

import android.content.Context
import com.example.moviecatalogue.model.data.remote.repository.DataRepository
import com.example.moviecatalogue.model.data.remote.source.RemoteDataSource

object Injection {
    fun provideRepository(context: Context): DataRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return DataRepository.getInstance(remoteDataSource)
    }
}