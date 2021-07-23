package com.example.moviecatalogue

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.moviecatalogue.utils.DataMovie
import com.example.moviecatalogue.utils.DetailDataMov
import com.example.moviecatalogue.utils.EspressoIdlingResource
import com.example.moviecatalogue.view.activity.MainActivity
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private val dataMovie = DataMovie.generateDataMovie()
    private val dataMovieDetail = DetailDataMov.getMovieDetail()
    private val dataTv = DataMovie.generateDataTv()
    private val dataTvDetail = DetailDataMov.getTvDetail()

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoIdlingResource)
    }

    @Test
    fun loadAllMovie() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dataMovie.size))
    }

    @Test
    fun loadAllMovieDetail() {
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.img_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.title_detail)).check(matches(withText(dataMovieDetail.title)))
        onView(withId(R.id.release_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.release_detail)).check(matches(withText(dataMovieDetail.date)))
        onView(withId(R.id.genre_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.overview_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.overview_detail)).check(matches(withText(dataMovieDetail.overview)))
    }

    @Test
    fun loadAllTv() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvShow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dataTv.size))
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tvShow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.img_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.title_detail)).check(matches(withText(dataTvDetail.title)))
        onView(withId(R.id.release_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.release_detail)).check(matches(withText(dataTvDetail.date)))
        onView(withId(R.id.genre_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.overview_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.overview_detail)).check(matches(withText(dataTvDetail.overview)))
    }

}