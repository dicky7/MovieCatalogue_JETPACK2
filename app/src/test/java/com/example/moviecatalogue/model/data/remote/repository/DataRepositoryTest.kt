package com.example.moviecatalogue.model.data.remote.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviecatalogue.model.data.remote.source.RemoteDataSource
import com.example.moviecatalogue.utils.DataMovie
import com.example.moviecatalogue.utils.DetailDataMov
import com.example.moviecatalogue.utils.LiveDataTest
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class DataRepositoryTest{
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val dataRepository = FakeDataRepository(remote)

    private val movieResponse = DataMovie.getMovieRemote()
    private val movieId = movieResponse[0].id.toString()
    private val movieDetail = DetailDataMov.getMovieDetailRemote()

    private val tvShowResponse = DataMovie.getTvRemote()
    private val tvId = tvShowResponse[0].id.toString()
    private val tvDetail = DetailDataMov.getTvDetailRemote()

    @Test
    fun getAllMovie() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback).onMovieReceive(movieResponse)
            null
        }.`when`(remote).getMovies(any())

        val movieEntity = LiveDataTest.getValue(dataRepository.getMovie())
        verify(remote).getMovies(any())
        assertNotNull(movieEntity)
        assertEquals(movieResponse.size, movieEntity.size)
    }

    @Test
    fun getAllMovieDetail() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadDetailMovieCallback).onMovieDetailReceive(movieDetail)
            null
        }.`when`(remote).getDetailMovie(any(), eq(movieId))

        val movieDetailEntity = LiveDataTest.getValue(dataRepository.getMovieDetail(movieId))
        verify(remote).getDetailMovie(any(), eq(movieId))
        assertNotNull(movieDetailEntity)
        assertEquals(movieDetail.id, movieDetailEntity.id)
    }

    @Test
    fun getAllTv() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowsCallback).onTvReceive(tvShowResponse)
            null
        }.`when`(remote).getTvShows(any())

        val tvShowEntities = LiveDataTest.getValue(dataRepository.getTvShows())
        verify(remote).getTvShows(any())
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.size, tvShowEntities.size)
    }



    @Test
    fun getAllTvDetail() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadDetailTvShowCallback).onTvDetailReceive(tvDetail)
            null
        }.`when`(remote).getDetailTvShow(any(), eq(tvId))

        val tvDetailEntity = LiveDataTest.getValue(dataRepository.getDetailTvShow(tvId))
        verify(remote).getDetailTvShow(any(), eq(tvId))
        assertNotNull(tvDetailEntity)
        assertEquals(tvDetail.id, tvDetailEntity.id)
    }
}