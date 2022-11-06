package com.dk.newprojectsearchmovie.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.dk.newprojectsearchmovie.R
import com.dk.newprojectsearchmovie.databinding.FragmentMovieDetailBinding
import com.dk.newprojectsearchmovie.model.Movie

class MovieDetailFragment : Fragment() {
    private var _binding: FragmentMovieDetailBinding? = null
    private val binding: FragmentMovieDetailBinding
        get() {
            return _binding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie = arguments?.getParcelable<Movie>(MOVIE)

        with(binding) {
            Glide.with(binding.root.context).load(movie?.poster)
                .error(movie?.posterLocal)
                .placeholder(R.drawable.poster)
                .override(674,1000)
                .into(imageMovie)
            movieTitle.text = movie?.title
            movieRealise.text = movie?.year.toString()
            movieRaiting.text = movie?.rating.toString()
        }

    }

    companion object {
        const val MOVIE = "movie"
        const val NAME = "name"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}