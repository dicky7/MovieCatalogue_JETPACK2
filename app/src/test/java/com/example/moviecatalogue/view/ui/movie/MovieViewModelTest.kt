package com.example.moviecatalogue.view.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.model.data.entity.MovieEntity
import com.example.moviecatalogue.model.data.remote.repository.DataRepository
import com.example.moviecatalogue.utils.DataMovie
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
class MovieViewModelTest{
    private lateinit var movieViewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Before
    fun setUp() {
        movieViewModel = MovieViewModel(dataRepository)
    }

    @Test
    fun getMovieModel(){
        val dataMove = DataMovie.generateDataMovie()
        val listMovie = MutableLiveData<List<MovieEntity>>()
        listMovie.value = dataMove

        Mockito.`when`(dataRepository.getMovie()).thenReturn(listMovie)
        val movie = movieViewModel.getMovieModel().value
        verify(dataRepository).getMovie()
        assertNotNull(movie)
        assertEquals(5, movie?.size)

        movieViewModel.getMovieModel().observeForever(observer)
        verify(observer).onChanged(dataMove)
    }

}