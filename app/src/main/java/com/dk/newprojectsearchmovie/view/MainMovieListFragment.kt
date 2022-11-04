package com.dk.newprojectsearchmovie.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dk.newprojectsearchmovie.databinding.FragmentMainMovieListBinding
import com.dk.newprojectsearchmovie.model.Movie
import com.dk.newprojectsearchmovie.model.getMovieTop250ListFromLocalStorage
import com.dk.newprojectsearchmovie.viewmodel.MovieListAdapter
import com.dk.newprojectsearchmovie.viewmodel.MovieListViewModel
import com.dk.newprojectsearchmovie.viewmodel.StateLoadMovieList

class MainMovieListFragment : Fragment() {

    lateinit var adapter: MovieListAdapter
    private var _binding: FragmentMainMovieListBinding? = null
    private val binding: FragmentMainMovieListBinding
        get() {
            return _binding!!
        }
    private val movieListViewModel: MovieListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainMovieListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieListViewModel.getMovieListState().observe(viewLifecycleOwner) {
            when (it) {
                is StateLoadMovieList.ErrorLoad -> {

                }
                StateLoadMovieList.Loading -> {

                }
                is StateLoadMovieList.SuccesLoad -> {
                    renderMovieList(it.movieList)
                }
            }
        }
        movieListViewModel.getRequestMovieListState()

        renderMovieList(getMovieTop250ListFromLocalStorage())
    }

    private fun renderMovieList(movieList: List<Movie>) {
        adapter = MovieListAdapter().also {
            it.setMovieList(movieList)
        }
        with(binding) {
            rvMovies.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rvMovies.hasFixedSize()
            rvMovies.adapter = adapter
        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}


