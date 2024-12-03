package com.example.imdbapp.ui.screens.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.imdbapp.adapters.HomeAdapter
import com.example.imdbapp.databinding.FragmentHomeBinding
import com.example.imdbapp.main.MainRepo
import com.example.imdbapp.util.moviesList
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    // filter işlemini applya basılınca search ekranında olacak şekilde yap ama önce kullanıcıdan bir arama iste gelen sonuçları rc de göster sonra filtreleme yap
    // applya tıklanınca home fragmenta gitme nv graphtan actionu kaldır


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
                    val adapter = HomeAdapter(moviesList, mainRepo)
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