package com.example.moviecatalogue.view.ui.tvShow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.BuildConfig
import com.example.moviecatalogue.R
import com.example.moviecatalogue.model.data.entity.TvEntity
import com.example.moviecatalogue.databinding.ListItemBinding


class TvShowAdapter: RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    private var shows = ArrayList<TvEntity>()

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setTvShows(list: List<TvEntity>?) {
        if (list == null) return
        this.shows.clear()
        this.shows.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val listItemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tv = shows[position]
        holder.bind(tv)
    }

    override fun getItemCount(): Int = shows.size

    inner class TvShowViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvEntity: TvEntity) {
            with(binding) {
                tvEntity.apply {
                    itemView.apply {
                        listItemTitle.text = title
                        listItemRelease.text = date

                        Glide.with(context)
                            .load(BuildConfig.IMAGE_URL + posterPath)
                            .transform(RoundedCorners(20))
                            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                            .error(R.drawable.ic_error)
                            .into(listItemImg)
                        itemView.setOnClickListener { onItemClickCallback.onItemClicked(tvEntity.id.toString()) }
                    }
                }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(id: String)
    }
}