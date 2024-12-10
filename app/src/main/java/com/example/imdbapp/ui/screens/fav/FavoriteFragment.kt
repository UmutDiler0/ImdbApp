package com.example.imdbapp.ui.screens.fav

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.imdbapp.R
import com.example.imdbapp.common.util.favoritedMovies
import com.example.imdbapp.data.repository.MainRepo
import com.example.imdbapp.databinding.FragmentFavoriteBinding
import com.example.imdbapp.ui.screens.home.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoriteViewModel by viewModels()
    @Inject lateinit var mainRepo: MainRepo

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeData(){
        lifecycleScope.launch {
            viewModel.getFavoritedMovies()
            viewModel.favoritedMovieList.collect{
                it.clear()
                val adapter = HomeAdapter(it,mainRepo){
                    findNavController().navigate(R.id.action_favoriteFragment_to_detailFragment)
                }
                binding.favoritedRC.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        }
    }


}