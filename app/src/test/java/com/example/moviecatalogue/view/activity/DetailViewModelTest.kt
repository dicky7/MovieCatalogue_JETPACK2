package com.example.moviecatalogue.view.activity

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.model.data.entity.DetailEntity
import com.example.moviecatalogue.model.data.remote.repository.DataRepository
import com.example.moviecatalogue.utils.DetailDataMov
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest{
    private lateinit var detailViewModel: DetailViewModel

    private val dataMovie = DetailDataMov.getMovieDetail()
    private val dataMovieId = dataMovie.id.toString()

    private val dataTv = DetailDataMov.getTvDetail()
    private val dataTvId = dataTv.id.toString()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<DetailEntity>

    @Before
    fun setUp() {
        detailViewModel = DetailViewModel(dataRepository)
    }

    @Test
    fun getMovieDetails(){
        val movDetailEntity = MutableLiveData<DetailEntity>()
        movDetailEntity.value = dataMovie

        Mockito.`when`(dataRepository.getMovieDetail(dataMovieId)).thenReturn(movDetailEntity)
        detailViewModel.setDataDetail(dataMovieId, DetailViewModel.MOVIE_DETAIL)
        val movieDetail = detailViewModel.getDataDetail().value
        verify(dataRepository).getMovieDetail(dataMovieId)
        assertNotNull(movieDetail)

        assertEquals(dataMovie.id, movieDetail?.id)
        assertEquals(dataMovie.title, movieDetail?.title)
        assertEquals(dataMovie.date, movieDetail?.date)
        assertEquals(dataMovie.genres, movieDetail?.genres)
        assertEquals(dataMovie.posterPath, movieDetail?.posterPath)
        assertEquals(dataMovie.overview, movieDetail?.overview)


        detailViewModel.getDataDetail().observeForever(observer)
        verify(observer).onChanged(dataMovie)
    }

    @Test
    fun getTvDetails(){
        val tvDetailEntity = MutableLiveData<DetailEntity>()
        tvDetailEntity.value = dataTv

        Mockito.`when`(dataRepository.getDetailTvShow(dataTvId)).thenReturn(tvDetailEntity)
        detailViewModel.setDataDetail(dataTvId, DetailViewModel.TV_SHOW_DETAIL)
        val tvDetail = detailViewModel.getDataDetail().value
        verify(dataRepository).getDetailTvShow(dataTvId)
        assertNotNull(tvDetail)

        assertEquals(dataTv.id, tvDetail?.id)
        assertEquals(dataTv.title, tvDetail?.title)
        assertEquals(dataTv.date, tvDetail?.date)
        assertEquals(dataTv.genres, tvDetail?.genres)
        assertEquals(dataTv.posterPath, tvDetail?.posterPath)
        assertEquals(dataTv.overview, tvDetail?.overview)


        detailViewModel.getDataDetail().observeForever(observer)
        verify(observer).onChanged(dataTv)
    }

}