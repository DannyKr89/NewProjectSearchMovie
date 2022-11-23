package com.dk.newprojectsearchmovie.presentation.view.search

import android.annotation.SuppressLint
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
import com.dk.newprojectsearchmovie.R
import com.dk.newprojectsearchmovie.data.model.search.RequestSearchMovie
import com.dk.newprojectsearchmovie.databinding.FragmentSearchBinding
import com.dk.newprojectsearchmovie.presentation.viewmodel.MovieSearchListViewModel
import com.dk.newprojectsearchmovie.presentation.viewmodel.StateLoadMovieList
import kotlin.properties.Delegates

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MovieSearchListViewModel by activityViewModels()
    private var adapter: SearchAdapter? = null
    private lateinit var sharedPref: SharedPreferences
    private var seekBarRating by Delegates.notNull<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()


    }

    private fun init() {
        sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)!!
        seekBarRating = sharedPref.getInt("seekbar", 0)

        adapter = SearchAdapter()
        viewModel.getMovieSearchList().observe(viewLifecycleOwner) {
            when (it) {
                is StateLoadMovieList.ErrorLoad -> {
                    hideProgressBar()
                    Toast.makeText(requireContext(), it.throwable.message, Toast.LENGTH_SHORT)
                        .show()

                }
                is StateLoadMovieList.Loading -> {
                    showProgressBar()
                }
                is StateLoadMovieList.SuccessLoad -> {
                    hideProgressBar()
                    renderMovieList(it.movieList as RequestSearchMovie)
                }
            }
        }

        with(binding) {
            seekBar.progress = seekBarRating
            tvMinRating.text = getDoubleFromString(seekBar.progress).toString()
            viewModel.setMinRating(getDoubleFromString(seekBar.progress))


            btnSearch.setOnClickListener {
                val textQuary = binding.etSearch.text.toString()
                viewModel.getMovieSearchRequest(textQuary)
            }


            seekBar.setOnSeekBarChangeListener(@SuppressLint("AppCompatCustomView")
            object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    tvMinRating.text = getDoubleFromString(p1).toString()
                }
                override fun onStartTrackingTouch(p0: SeekBar?) {
                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                    if (p0 != null) {
                        viewModel.setMinRating(getDoubleFromString(p0.progress))
                    }
                    sharedPref.edit().putInt("seekbar", seekBar.progress).apply()
                }
            })

        }
    }

    private fun getDoubleFromString(seekBarRating: Int): Double {
        return seekBarRating.toDouble()/10
    }

    private fun renderMovieList(requestSearchMovie: RequestSearchMovie) {

        val searchList = requestSearchMovie.searchMovie.sortedBy {
            it.imDbRating
        }
        with(binding) {
            adapter?.setList(searchList.reversed())
            rvSearch.adapter = adapter
        }
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
        adapter = null
        _binding = null
        super.onDestroyView()
    }
}