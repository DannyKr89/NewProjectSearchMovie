package com.dk.newprojectsearchmovie.presentation.view.collections

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.dk.newprojectsearchmovie.R
import com.dk.newprojectsearchmovie.data.common.COLLECTIONS
import com.dk.newprojectsearchmovie.data.common.MOVIE
import com.dk.newprojectsearchmovie.data.common.NAME
import com.dk.newprojectsearchmovie.data.common.SEEKBAR
import com.dk.newprojectsearchmovie.databinding.FragmentCollectionsListBinding
import com.dk.newprojectsearchmovie.model.imdb.CollectionsMovie
import com.dk.newprojectsearchmovie.presentation.view.states.StateLoadMovieList

class CollectionsListFragment : Fragment() {

    private var _binding: FragmentCollectionsListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CollectionsViewModel by activityViewModels()
    private var adapter = CollectionsAdapter()
    private var movieList = listOf<CollectionsMovie>()
    private lateinit var sharedPref: SharedPreferences
    private var seekBarRatingList = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val list = arguments?.getString(COLLECTIONS)
        viewModel.getRequestMovieListState(list.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCollectionsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

    }

    private fun init() {
        sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)!!
        seekBarRatingList = sharedPref.getInt(SEEKBAR, 0)

        with(binding) {
            seekBar.progress = seekBarRatingList
            tvMinRating.text = getDoubleFromString(seekBar.progress).toString()

            seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    tvMinRating.text = getDoubleFromString(p1).toString()
                    if (p0 != null) {
                        val newList = movieList.filter {
                            it.imDbRating >= getDoubleFromString(p0.progress).toString()
                        }
                        renderMovieList(newList)
                    }
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                    seekBarRatingList = p0?.progress ?: 0
                    sharedPref.edit().putInt(SEEKBAR, seekBarRatingList).apply()
                }
            })
        }


        viewModel.getMovieListState().observe(viewLifecycleOwner) { state ->
            when (state) {
                is StateLoadMovieList.ErrorLoad -> {
                    hideProgressBar()
                    Toast.makeText(
                        requireContext(), state.throwable.message, Toast.LENGTH_SHORT
                    ).show()

                }
                is StateLoadMovieList.Loading -> {
                    showProgressBar()
                }
                is StateLoadMovieList.SuccessLoad -> {
                    hideProgressBar()
                    movieList = (state.movieList).filterIsInstance<CollectionsMovie>()

                    renderMovieList(movieList)
                }
            }
        }
    }

    private fun renderMovieList(movieList: List<CollectionsMovie>) {

        adapter.submitList(movieList)
        adapter.listener = {
            findNavController().navigate(R.id.Collections_to_Detail, Bundle().apply {
                putString(MOVIE, it.id)
                putString(NAME, it.title)
            })
        }
        with(binding) {
            rvSearch.adapter = adapter
        }
    }

    private fun getDoubleFromString(seekBarRating: Int): Double {
        return seekBarRating.toDouble() / 10
    }

    private fun hideProgressBar() {
        with(binding) {
            rvSearch.visibility = View.VISIBLE
            progressBarLayout.progressBar.visibility = View.GONE
        }
    }

    private fun showProgressBar() {
        with(binding) {
            rvSearch.visibility = View.GONE
            progressBarLayout.progressBar.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}