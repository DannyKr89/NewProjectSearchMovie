package com.dk.newprojectsearchmovie.presentation.view.search

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
import com.dk.newprojectsearchmovie.data.common.MOVIE
import com.dk.newprojectsearchmovie.data.common.NAME
import com.dk.newprojectsearchmovie.data.common.SEEKBAR
import com.dk.newprojectsearchmovie.data.common.TEXT_QUERY
import com.dk.newprojectsearchmovie.data.model.search.SearchMovie
import com.dk.newprojectsearchmovie.databinding.FragmentSearchListBinding
import com.dk.newprojectsearchmovie.presentation.view.states.StateLoadMovieList
import kotlin.properties.Delegates

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchListViewModel by activityViewModels()
    private var adapter = SearchAdapter()
    private var movieList = listOf<SearchMovie>()
    private lateinit var sharedPref: SharedPreferences
    private var seekBarRating by Delegates.notNull<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textQuery = arguments?.getString(TEXT_QUERY)
        viewModel.getMovieSearchRequest(textQuery.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

    }

    private fun init() {
        sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)!!
        seekBarRating = sharedPref.getInt(SEEKBAR, 0)



        with(binding) {
            seekBar.progress = seekBarRating
            tvMinRating.text = getDoubleFromString(seekBar.progress).toString()

            seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    tvMinRating.text = getDoubleFromString(p1).toString()
                    if (p0 != null) {
                        val newList = movieList.filter {
                            it.imDbRating >= getDoubleFromString(p0.progress).toString()
                        }.sortedBy {
                            it.imDbRating
                        }.reversed()
                        renderSearchList(newList)
                    }
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                    seekBarRating = p0?.progress ?: 0
                    sharedPref.edit().putInt(SEEKBAR, seekBarRating).apply()
                }
            })

            viewModel.getMovieSearchList().observe(viewLifecycleOwner) { state ->
                when (state) {
                    is StateLoadMovieList.ErrorLoad -> {
                        hideProgressBar()
                        Toast.makeText(
                            requireContext(),
                            state.throwable.message,
                            Toast.LENGTH_SHORT
                        )
                            .show()

                    }
                    is StateLoadMovieList.Loading -> {
                        showProgressBar()
                    }
                    is StateLoadMovieList.SuccessLoad -> {
                        hideProgressBar()
                        movieList =
                            state.movieList.filterIsInstance<SearchMovie>().filter { list ->
                                list.imDbRating >= getDoubleFromString(seekBarRating).toString()
                            }.sortedBy {
                                it.imDbRating
                            }.reversed()

                        renderSearchList(movieList)
                    }
                }
            }
        }
    }


    private fun renderSearchList(movieList: List<SearchMovie>) {

        adapter.submitList(movieList)
        adapter.listener = {
            findNavController().navigate(
                R.id.SearchList_to_Detail,
                Bundle().apply {
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
            progressBarSearch.progressBar.visibility = View.GONE
        }
    }

    private fun showProgressBar() {
        with(binding) {
            rvSearch.visibility = View.GONE
            progressBarSearch.progressBar.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}