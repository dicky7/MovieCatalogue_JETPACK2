package com.example.moviecatalogue.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.BuildConfig.IMAGE_URL
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.ActivityDetailBinding
import com.example.moviecatalogue.model.data.entity.DetailEntity
import com.example.moviecatalogue.view.ui.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_DETAIL = "extra_detail"
        private val TAG = DetailActivity::class.java.simpleName
    }
    private lateinit var detailBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        supportActionBar?.hide()
        detailBinding.tolbar.setNavigationOnClickListener { onBackPressed() }

        true.progressBar()

        val factor = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factor)[DetailViewModel::class.java]
        val extras = intent.extras
        if (extras != null){
            val movieId = extras.getString(EXTRA_MOVIE)
            val detailId = extras.getString(EXTRA_DETAIL)
            Log.d(TAG, movieId.toString())
            Log.d(TAG, detailId.toString())

            if (movieId != null && detailId != null){
                viewModel.setDataDetail(movieId,detailId)
                viewModel.getDataDetail().observe(this,{ details->
                    bind(details)
                    false.progressBar()
                })
            }
        }
    }

    private fun bind(detailEntity: DetailEntity){
        if (supportActionBar!= null){
            title = detailEntity.title + "Detail"
        }

        val genre = detailEntity.genres.toString()
            .replace("[", "")
            .replace("]", "")

        with(detailBinding){

            titleDetail.text = detailEntity.title
            genreDetail.text = genre
            releaseDetail.text = detailEntity.date
            overviewDetail.text = detailEntity.overview
        }

        Glide.with(this)
            .load(IMAGE_URL + detailEntity.posterPath)
            .transform(RoundedCorners(20))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_error)
            .into(img_detail)
    }

    private fun Boolean.progressBar() {
        detailBinding.progressBar.visibility = if (this) View.VISIBLE else View.GONE
    }
}