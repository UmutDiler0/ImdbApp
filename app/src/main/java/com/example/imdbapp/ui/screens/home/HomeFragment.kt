package com.example.imdbapp.ui.screens.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.imdbapp.R
import com.example.imdbapp.databinding.FragmentHomeBinding
import com.example.imdbapp.data.repository.MainRepo
import com.example.imdbapp.common.util.moviesList
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    @Inject lateinit var mainRepo: MainRepo
    private val viewModel: HomeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadMovies()
        observeData()
    }

    private fun observeData() {
        with(lifecycleScope){
            launch{
                viewModel.movieListVM.collect{
                    moviesList.addAll(it)
                    val adapter = HomeAdapter(moviesList, mainRepo){string->
                        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(string)
                        findNavController().navigate(action)
                    }
                    binding.homeRC.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }
            launch{
                viewModel.responseState.collect{
                    if(it){
                        binding.homePB.visibility = View.GONE
                    }else{
                        binding.homePB.visibility = View.VISIBLE
                    }
                }
            }
        }

    }
}