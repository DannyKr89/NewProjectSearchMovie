package com.dk.newprojectsearchmovie.presentation.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dk.newprojectsearchmovie.R
import com.dk.newprojectsearchmovie.data.common.*
import com.dk.newprojectsearchmovie.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        with(binding) {

            etSearch.setOnEditorActionListener { _, p1, _ ->
                if (p1 == EditorInfo.IME_ACTION_SEARCH) {
                    startSearch()
                }
                false
            }

            btnSearch.setOnClickListener {
                startSearch()
            }
            imbtnTop250.setOnClickListener {
                findNavController().navigate(
                    R.id.Main_to_Collections,
                    Bundle().apply {
                        putString(COLLECTIONS, TOP250)
                        putString(NAME, getString(R.string.top_250))

                    })
            }
            imbtnPopular.setOnClickListener {
                findNavController().navigate(
                    R.id.Main_to_Collections,
                    Bundle().apply {
                        putString(COLLECTIONS, POPULAR)
                        putString(NAME, getString(R.string.popular))
                    })
            }

        }
    }

    private fun startSearch() {
        val textQuery = binding.etSearch.text.toString()
        if (textQuery.isNotEmpty())
            findNavController().navigate(
                R.id.Main_to_SearchList,
                Bundle().apply {
                    putString(TEXT_QUERY, textQuery)
                })
        else
            Toast.makeText(context, getString(R.string.request), Toast.LENGTH_SHORT).show()
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}