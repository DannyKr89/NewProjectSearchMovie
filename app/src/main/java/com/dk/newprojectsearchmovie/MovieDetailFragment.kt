package com.dk.newprojectsearchmovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        val movie = arguments?.getParcelable<Movie>("movie")!!

        with(binding) {
            movie.poster?.let { imageMovie.setImageResource(it) }
            movieTitle.text = movie.title
            movieRealise.text = movie.year.toString()
            movieRaiting.text = movie.rating.toString()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}