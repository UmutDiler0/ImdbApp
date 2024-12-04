package com.example.imdbapp.ui.screens.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.imdbapp.R
import com.example.imdbapp.adapters.HomeAdapter
import com.example.imdbapp.adapters.SearchingAdapter
import com.example.imdbapp.databinding.FragmentSearchBinding
import com.example.imdbapp.main.MainRepo
import com.example.imdbapp.util.searchedList
import com.example.imdbapp.util.searchingList
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var mainRepo: MainRepo
    val viewModel: SearcViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.filterBtn.setOnClickListener {
            if(searchedList.isNotEmpty()){
                findNavController().navigate(R.id.action_searchFragment_to_filterFragment)
            }else{
                Snackbar.make(view,"Please Search Movie First",Snackbar.LENGTH_LONG).show()
            }
        }
        val searchedAdapter = HomeAdapter(searchedList.toList(),mainRepo)
        binding.searchedRV.adapter = searchedAdapter
        val searchingAdapter = SearchingAdapter(searchingList)
        binding.searchingRV.adapter = searchingAdapter
    }



}