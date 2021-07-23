package com.example.moviecatalogue.view.ui.tvShow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.view.activity.DetailActivity
import com.example.moviecatalogue.databinding.FragmentTvShowBinding
import com.example.moviecatalogue.view.activity.DetailViewModel
import com.example.moviecatalogue.view.activity.DetailViewModel.Companion.TV_SHOW_DETAIL
import com.example.moviecatalogue.view.ui.viewModel.ViewModelFactory


class TvShowFragment : Fragment(), TvShowAdapter.OnItemClickCallback {

    private lateinit var fragmentTvShowBinding: FragmentTvShowBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            true.progressBar()

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvViewModel::class.java]
            val tvShowAdapter = TvShowAdapter()

            viewModel.getTvModel().observe(viewLifecycleOwner, {tvShows ->
                false.progressBar()
                tvShowAdapter.apply {
                    setTvShows(tvShows)
                    setOnItemClickCallback(this@TvShowFragment)
                    notifyDataSetChanged()
                }
            })


            with(fragmentTvShowBinding.rvTvShow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = tvShowAdapter
            }
        }
    }

    override fun onItemClicked(id: String) {
        Intent(context, DetailActivity::class.java).also {
            it.putExtra(DetailActivity.EXTRA_MOVIE, id)
            it.putExtra(DetailActivity.EXTRA_DETAIL, TV_SHOW_DETAIL)

            context?.startActivity(it)
        }
    }

    private fun Boolean.progressBar() {
        fragmentTvShowBinding.progressBar.visibility = if (this) View.VISIBLE else View.GONE
    }

}