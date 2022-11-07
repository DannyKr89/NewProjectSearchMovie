package com.dk.newprojectsearchmovie.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.dk.newprojectsearchmovie.databinding.FragmentMovieDetailBinding
import com.dk.newprojectsearchmovie.model.Movie
import com.dk.newprojectsearchmovie.model.imdb.imdbMovie.ImdbMovieDetail
import com.dk.newprojectsearchmovie.viewmodel.MovieListViewModel
import com.dk.newprojectsearchmovie.viewmodel.StateLoadMovie
import jp.wasabeef.glide.transformations.BlurTransformation

class MovieDetailFragment : Fragment() {

    private val movieViewModel: MovieListViewModel by activityViewModels()
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
        val movie = arguments?.getParcelable<Movie>(MOVIE)!!

        movieViewModel.getRequestMovieDetailState(movie)

        movieViewModel.getMovieDetailState().observe(viewLifecycleOwner) {
            when (it) {
                is StateLoadMovie.ErrorLoad -> {
                    Toast.makeText(requireContext(),"error",Toast.LENGTH_SHORT).show()
                }
                is StateLoadMovie.Loading -> {
                    Toast.makeText(requireContext(),"loading",Toast.LENGTH_SHORT).show()
                }
                is StateLoadMovie.SuccessLoad -> {
                    Toast.makeText(requireContext(),"success",Toast.LENGTH_SHORT).show()
                    renderDetail(it.imdbMovieDetail)
                }
            }

        }


    }

    private fun renderDetail(movieDetail: ImdbMovieDetail) {

        with(binding) {
            Glide.with(binding.root.context).load(movieDetail.image)
                .error(movieDetail.posterLocal)
                .into(imageMovie)

            Glide.with(binding.root.context)
                .load(movieDetail.image)
                .error(movieDetail.posterLocal)
                .apply(bitmapTransform(BlurTransformation(25, 3)))
                .into(background)

            movieTitle.text = movieDetail.title
            movieRealise.text = movieDetail.year

            movieRating.apply {
                text = movieDetail.imDbRating
                movieDetail.imDbRating?.let {
                    when (it.toDouble()) {
                        in 0.0..4.9 ->  setTextColor(Color.RED)
                        in 5.0..6.9 -> setTextColor(Color.YELLOW)
                        in 7.0..10.0 -> setTextColor(Color.GREEN)
                    }
                }
            }

            movieRatingVote.text = movieDetail.imDbRatingVotes

            movieContentRating.text = movieDetail.contentRating
            movieCountries.text = movieDetail.countries
            movieGenres.text = movieDetail.genres
            movieDirector.text = movieDetail.directors
            movieRunTimeStr.text = movieDetail.runtimeStr
            movieLocalPlot.text = movieDetail.plotLocal
            movieStars.text = movieDetail.stars
            movieWriter.text = movieDetail.writers
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