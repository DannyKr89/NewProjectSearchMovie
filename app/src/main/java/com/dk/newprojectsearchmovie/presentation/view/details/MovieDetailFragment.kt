package com.dk.newprojectsearchmovie.presentation.view.details

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
import com.dk.newprojectsearchmovie.R
import com.dk.newprojectsearchmovie.data.common.MOVIE
import com.dk.newprojectsearchmovie.data.model.detailMovie.ImdbMovieDetail
import com.dk.newprojectsearchmovie.databinding.FragmentMovieDetailBinding
import com.dk.newprojectsearchmovie.presentation.view.favoritelist.FovoriteListViewModel
import com.dk.newprojectsearchmovie.presentation.view.states.StateLoadMovie
import jp.wasabeef.glide.transformations.BlurTransformation
import java.text.NumberFormat

class MovieDetailFragment : Fragment() {

    private val movieDetailViewModel: MovieDetailViewModel by activityViewModels()
    private val fovoriteListViewModel: FovoriteListViewModel by activityViewModels()
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
        val movie = arguments?.getString(MOVIE)!!

        movieDetailViewModel.getRequestMovieDetailState(movie)

        movieDetailViewModel.getMovieDetailState().observe(viewLifecycleOwner) {
            when (it) {
                is StateLoadMovie.ErrorLoad -> {
                    hideProgressBar()
                    Toast.makeText(requireContext(), it.throwable.message, Toast.LENGTH_SHORT)
                        .show()
                }
                is StateLoadMovie.Loading -> {
                    showProgressBar()
                }
                is StateLoadMovie.SuccessLoad -> {
                    hideProgressBar()
                    renderDetail(it.imdbMovieDetail)
                }
            }

        }


    }

    private fun hideProgressBar() {
        with(binding) {
            progressBarLayout.progressBar.visibility = View.GONE
            nestedSVDetail.visibility = View.VISIBLE
        }
    }

    private fun showProgressBar() {
        with(binding) {
            progressBarLayout.progressBar.visibility = View.VISIBLE
            nestedSVDetail.visibility = View.GONE
        }
    }

    private fun renderDetail(movieDetail: ImdbMovieDetail) {

        with(binding) {
            Glide.with(binding.root.context).load(movieDetail.image)
                .into(imageMovie)

            Glide.with(binding.root.context)
                .load(movieDetail.image)
                .apply(bitmapTransform(BlurTransformation(25, 3)))
                .into(background)

            movieTitle.text = movieDetail.title
            movieRealise.text = movieDetail.year

            movieRating.apply {
                text = if (movieDetail.imDbRating != null) {
                    movieDetail.imDbRating
                } else {
                    ""
                }
                movieDetail.imDbRating?.let {
                    when (it.toDouble()) {
                        in 0.0..4.9 -> setTextColor(Color.RED)
                        in 5.0..6.9 -> setTextColor(Color.YELLOW)
                        in 7.0..10.0 -> setTextColor(Color.GREEN)
                    }
                }
            }

            movieRatingVote.text = if(movieDetail.imDbRating != null && movieDetail.imDbRating.equals(""))
                String.format(
                    getString(R.string.votes),
                    NumberFormat.getInstance().format(movieDetail.imDbRatingVotes.toLong()))
             else String.format(
                getString(R.string.votes), "0")

            movieContentRating.text = movieDetail.contentRating
            movieCountries.text = movieDetail.countries
            movieGenres.text = movieDetail.genres
            movieDirector.text = movieDetail.directors
            movieRunTimeStr.text = movieDetail.runtimeStr
            movieLocalPlot.text = movieDetail.plotLocal
            movieStars.text = movieDetail.stars
            movieWriter.text = movieDetail.writers

            rvActorsList.adapter = DetailAdapter(movieDetail.actorList)
            rvSimilarsList.adapter = DetailAdapter(movieDetail.similars)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}