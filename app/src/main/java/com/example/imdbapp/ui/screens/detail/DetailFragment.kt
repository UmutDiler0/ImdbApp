package com.example.imdbapp.ui.screens.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.imdbapp.R
import com.example.imdbapp.common.util.token
import com.example.imdbapp.data.repository.MainRepo
import com.example.imdbapp.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    val binding get() = _binding!!
    @Inject lateinit var mainRepo: MainRepo
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val imdbId = DetailFragmentArgs.fromBundle(it).imdbID
            with(lifecycleScope){
                launch{
                    delay(1000L)
                    binding.detailCL.visibility = View.VISIBLE
                    binding.detailPB.visibility = View.GONE
                }
            }
            lifecycleScope.launch{
                val movie= mainRepo.getMoviesWithID(imdbId!!)!!
                binding.titleTV.text = movie.title
                binding.yearFromApi.text = movie.year
                binding.genreFromApi.text = movie.genre
                binding.directorFromApi.text = movie.director
                binding.actorsTV.text = movie.actors
                binding.plotTV.text = movie.plot
                Glide.with(requireContext())
                    .load(movie.poster)
                    .error(R.drawable.ic_error)
                    .into(binding.detaitPosterIV)
                binding.awardsFromApi.text = movie.awards
                binding.ratedFromApi.text = movie.imdbRating
                binding.writerFromApi.text = movie.writer
                binding.languageFromApi.text = movie.language
                binding.countryFromApi.text = movie.country
                binding.runtimeFromApi.text = movie.runtime
                binding.firstSource.text = movie.ratings[0].source
                binding.secondSource.text = movie.ratings[1].source
                binding.thirdSource.text = movie.ratings[2].source
                binding.firstSourceRating.text = movie.ratings[0].value
                binding.secondSourceRating.text = movie.ratings[1].value
                binding.thirdSourceRating.text = movie.ratings[2].value
                binding.metacriticRating.text = movie.metascore
                binding.boxOfficeFromApi.text = movie.boxOffice
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}