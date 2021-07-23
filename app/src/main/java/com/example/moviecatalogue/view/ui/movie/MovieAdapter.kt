package com.example.moviecatalogue.view.ui.movie

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.BuildConfig.IMAGE_URL
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.ListItemBinding
import com.example.moviecatalogue.model.data.entity.MovieEntity

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    private var listMovie = ArrayList<MovieEntity>()
    companion object{
        private val TAG = MovieAdapter::class.java.simpleName
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setMovie(movie: List<MovieEntity>?){
        if (movie == null)return
        this.listMovie.clear()
        this.listMovie.addAll(movie)
    }

    inner class MovieViewHolder (private val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(movieEntity: MovieEntity){
            with(binding) {
                movieEntity.apply {
                    itemView.apply {
                        listItemTitle.text = title
                        listItemRelease.text = date
                        val idM = movieEntity.id
                        Log.d(TAG,idM.toString())

                        Glide.with(context)
                            .load(IMAGE_URL + posterPath)
                            .transform(RoundedCorners(20))
                            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                            .error(R.drawable.ic_error)
                            .into(listItemImg)

                        setOnClickListener {
                            onItemClickCallback.onItemClicked(movieEntity.id.toString())
                            val id = onItemClickCallback.onItemClicked(movieEntity.id.toString())
                            Log.d(TAG,id.toString())
                        }
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val listItemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movies = listMovie[position]
        holder.bind(movies)
    }

    override fun getItemCount(): Int = listMovie.size

    interface OnItemClickCallback {
        fun onItemClicked(movieId: String)
    }
}