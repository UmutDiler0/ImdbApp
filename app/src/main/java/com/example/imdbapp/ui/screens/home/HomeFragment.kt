package com.example.imdbapp.ui.screens.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.imdbapp.R
import com.example.imdbapp.adapters.HomeAdapter
import com.example.imdbapp.databinding.FragmentHomeBinding
import com.example.imdbapp.main.MainRepo
import com.example.imdbapp.util.moviesList
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
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

        val adapter = HomeAdapter(moviesList,mainRepo)
        binding.homeRC.adapter = adapter
        mainRepo.fetchData(adapter)
        viewModel.updateResponseState(mainRepo.isResponseSuccess)
        GlobalScope.launch{
            delay(2000L)
            observeData()
        }
    }

    private fun observeData(){
        lifecycleScope.launch {
            viewModel.responseState.collect(){
                if(it){
                    binding.homePB.visibility = View.VISIBLE
                }else{
                    binding.homePB.visibility = View.GONE
                }
            }
        }
    }

}