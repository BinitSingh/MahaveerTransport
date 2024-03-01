package com.mobile.mahaveertransport.presentation.view.userinterface

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobile.mahaveertransport.databinding.FragmentMovieDetailBinding
import com.mobile.mahaveertransport.domain.datamodel.Movie
import com.mobile.mahaveertransport.utility.Constants.MOVIE
import com.mobile.mahaveertransport.extension.loadImageOrDefault

class MovieDetailFragment : BaseFragment() {
    private val TAG = MovieDetailFragment::class.java.canonicalName
    private lateinit var binding: FragmentMovieDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Movie>(MOVIE)?.let {
            renderUi(it)
        }?: run {
            Log.e(TAG,"Failed to load movie")
        }
    }

    private fun renderUi(movie: Movie) {
        with(binding) {
            movie.image?.let {
                imgBanner.loadImageOrDefault(it)
            }
            titleVal.text = movie.title ?: ""
            crewVal.text = movie.crew ?: ""
            ratingVal.text = movie.imDbRating ?: ""
            ratingCountVal.text = movie.imDbRatingCount ?: ""
            yearLabelVal.text = movie.year ?: ""
        }
    }
}