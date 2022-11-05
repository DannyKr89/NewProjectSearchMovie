package com.dk.newprojectsearchmovie.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dk.newprojectsearchmovie.R
import com.dk.newprojectsearchmovie.databinding.FragmentMainMovieListBinding
import com.dk.newprojectsearchmovie.model.Movie
import com.dk.newprojectsearchmovie.viewmodel.MovieListViewModel
import com.dk.newprojectsearchmovie.viewmodel.StateLoadMovieList

class MainMovieListFragment : Fragment() {

    private var adapter: MovieListAdapter? = null
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
        binding.rvMoviesTop250.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvMoviesPopular.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        movieListViewModel.getMovieListTop250State().observe(viewLifecycleOwner) {
            when (it) {
                is StateLoadMovieList.ErrorLoad -> {
                    showProgressBar()
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }
                is StateLoadMovieList.Loading -> {
                    showProgressBar()
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                }
                is StateLoadMovieList.SuccessLoad -> {
                    hideProgressBar()
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                    renderMovieList(it.movieList, binding.rvMoviesTop250)
                }
            }
        }

        movieListViewModel.getMovieListPopularState().observe(viewLifecycleOwner) {
            when (it) {
                is StateLoadMovieList.ErrorLoad -> {
                    showProgressBar()
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }
                is StateLoadMovieList.Loading -> {
                    showProgressBar()
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                }
                is StateLoadMovieList.SuccessLoad -> {
                    hideProgressBar()
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                    renderMovieList(it.movieList, binding.rvMoviesPopular)
                }
            }
        }

        if (savedInstanceState == null) {
            movieListViewModel.getRequestMovieListTop250State()
            movieListViewModel.getRequestMovieListPopularState()
        }
    }

    private fun hideProgressBar() {
        with(binding) {
            nestedSV.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        }
    }

    private fun showProgressBar() {
        with(binding) {
            nestedSV.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        }
    }

    private fun renderMovieList(movieList: List<Movie>, recyclerView: RecyclerView) {
        adapter = MovieListAdapter(object : SetOnMovieClickListner {
            override fun OnMovieClick(movie: Movie) {
                findNavController().navigate(
                    R.id.action_mainMuvieListFragment_to_movieDetailFragment,
                    Bundle().apply {
                        putParcelable("movie", movie)
                        putString("name", movie.title)
                    })
            }
        }).also {
            it.setMovieList(movieList)
        }
        with(binding) {
            recyclerView.hasFixedSize()
            recyclerView.adapter = adapter
        }

    }

    interface SetOnMovieClickListner {
        fun OnMovieClick(movie: Movie)
    }

    override fun onDestroyView() {
        adapter?.removeListener()
        _binding = null
        super.onDestroyView()
    }
}


