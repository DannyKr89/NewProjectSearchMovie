package com.dk.newprojectsearchmovie.presentation.view.favoritelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.dk.newprojectsearchmovie.databinding.FragmentFavoriteBinding

class FavoriteListFragment : Fragment() {


    private var _binding: FragmentFavoriteBinding? = null
    private val binding: FragmentFavoriteBinding
        get() {
            return _binding!!
        }
    private val fovoriteListViewModel: FovoriteListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater)
        return binding.root
    }


    private fun hideProgressBar() {
        with(binding) {
            nestedSV.visibility = View.VISIBLE
            progressBarLayout.progressBar.visibility = View.GONE
        }
    }

    private fun showProgressBar() {
        with(binding) {
            nestedSV.visibility = View.GONE
            progressBarLayout.progressBar.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}


