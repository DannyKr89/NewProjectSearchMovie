package com.dk.newprojectsearchmovie.view.movielist

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dk.newprojectsearchmovie.R
import com.dk.newprojectsearchmovie.databinding.FragmentMainMovieListBinding
import com.dk.newprojectsearchmovie.domain.MovieListType
import com.dk.newprojectsearchmovie.model.Movie
import com.dk.newprojectsearchmovie.view.MovieDetailFragment.Companion.MOVIE
import com.dk.newprojectsearchmovie.view.MovieDetailFragment.Companion.NAME
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

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            fabToggle.setOnClickListener {
                movieListViewModel.toggleStorage()
            }
        }

        initListViewModel(MovieListType.TOP250, binding.rvMoviesTop250)
        initListViewModel(MovieListType.POPULAR, binding.rvMoviesPopular)

        if (savedInstanceState == null){
            initStorageViewModel()
        }

    }

    private fun initStorageViewModel() {
        movieListViewModel.getLocalStorage().observe(viewLifecycleOwner) {

            movieListViewModel.getRequestMovieListState(MovieListType.TOP250)
            movieListViewModel.getRequestMovieListState(MovieListType.POPULAR)

            if (it) {
                binding.fabToggle.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
            } else {
                binding.fabToggle.backgroundTintList = ColorStateList.valueOf(Color.RED)
            }
        }
    }

    private fun initListViewModel(movieListType: MovieListType, recyclerView: RecyclerView) {
        movieListViewModel.getMovieListState(movieListType).observe(viewLifecycleOwner) {
            when (it) {
                is StateLoadMovieList.ErrorLoad -> {
                    hideProgressBar()
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                    renderMovieList(it.movieList, recyclerView)
                }
                is StateLoadMovieList.Loading -> {
                    showProgressBar()
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                }
                is StateLoadMovieList.SuccessLoad -> {
                    hideProgressBar()
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                    renderMovieList(it.movieList, recyclerView)
                }
            }
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
        adapter = MovieListAdapter(object : SetOnMovieClickListener {
            override fun onMovieClick(movie: Movie) {
                findNavController().navigate(R.id.action_mainMuvieListFragment_to_movieDetailFragment,
                    Bundle().apply {
                        putParcelable(MOVIE, movie)
                        putString(NAME, movie.title)
                    })
            }
        }).apply {
            setMovieList(movieList)
        }
        recyclerView.adapter = adapter
    }

    interface SetOnMovieClickListener {
        fun onMovieClick(movie: Movie)
    }

    override fun onDestroyView() {
        adapter?.removeListener()
        _binding = null
        super.onDestroyView()
    }
}


