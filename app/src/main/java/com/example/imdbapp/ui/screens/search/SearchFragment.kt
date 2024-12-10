package com.example.imdbapp.ui.screens.search

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.transition.Visibility
import com.example.imdbapp.R
import com.example.imdbapp.common.util.searchText
import com.example.imdbapp.ui.screens.home.HomeAdapter
import com.example.imdbapp.databinding.FragmentSearchBinding
import com.example.imdbapp.data.repository.MainRepo
import com.example.imdbapp.common.util.searchedList
import com.example.imdbapp.data.models.Movies
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {

    // dialog fragment ile dismiss() ile dialogtan çıkınca ui güncellenmiyor

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    @Inject
    lateinit var mainRepo: MainRepo
    val viewModel: SearcViewModel by viewModels()
    val listOfFilters: List<String> = listOf(
        "Year",
        "Type"
    )
    var selectedTypeItem: String? = null
    var selectedYearItem: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val filtersAdapter = ArrayAdapter(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listOfFilters)
        filtersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.filterBtn.setOnClickListener {
            lifecycleScope.launch {
                viewModel.searchMovieList.collect{
                    if(it.isNotEmpty()){
                        showDialogWithSpinners()
                    }else{
                        Snackbar.make(view,"Pleae Search Movie First",Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }

        val searchedAdapter = HomeAdapter(searchedList.toList(), mainRepo){
            val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(it)
            findNavController().navigate(action)
        }
        binding.searchedRV.adapter = searchedAdapter

        binding.movieSearchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    searchText = query
                    viewModel.isUserSearched(searchText)
                    viewModel.setFilteredFalse()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }

            }
        )
        observeData()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun observeData() {
        with(lifecycleScope) {
            launch {
                viewModel.isFiltered.collect{
                    if(it){
                        viewModel.filteredList.collect {
                            setAdapter(it)
                        }
                    }else{
                        viewModel.searchMovieList.collect{
                            setAdapter(it)
                        }
                    }
                }
            }
            launch {
                viewModel.isSearched.collect {
                    if (it) {
                        setView(View.GONE,View.GONE,View.VISIBLE)

                    } else {
                        setView(View.VISIBLE,View.VISIBLE,View.GONE)
                    }
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setAdapter(movieList: MutableList<Movies>){
        val searchedAdapter = HomeAdapter(movieList, mainRepo){
            val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(it)
            findNavController().navigate(action)
        }
        binding.searchedRV.adapter = searchedAdapter
        searchedAdapter.notifyDataSetChanged()
    }

    private fun setView(pleaseSearchView:Int,pleaseSearchTextView: Int,searchedRVView:Int){
        with(binding){
            pleaseSearch.visibility = pleaseSearchView
            pleaseSearchText.visibility = pleaseSearchTextView
            searchedRV.visibility = searchedRVView
        }
    }

    private fun applyFilter(selectedYear: String?, selectedType: String?){
        var applyYear: String? = selectedYear
        var applyType: String? = selectedType
        if(applyType == "type"){
            applyType = null
        }
        if(applyYear == "year"){
            applyYear = null
        }
        viewModel.getFilteredList(applyYear,applyType)
    }

    private fun showDialogWithSpinners() {

        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.custom_dialog,null)

        val years: MutableList<String> = mutableListOf("year")
        val year = (1980..2024).map{it.toString()}
        years.addAll(year)

        val yearsAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, years)
        yearsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val types = listOf("type","movie", "series", "game")
        val typesAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, types)
        typesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val yearSpinner = dialogView.findViewById<Spinner>(R.id.orderYearSpinner)
        val typeSpinner = dialogView.findViewById<Spinner>(R.id.orderTypeSpinner)

        yearSpinner.adapter = yearsAdapter
        typeSpinner.adapter = typesAdapter

        yearSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedYearItem = years[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

        typeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedTypeItem = types[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)
        builder.setTitle("Filter")
        builder.setPositiveButton("Apply") { dialog, _ ->
            applyFilter(selectedYearItem,selectedTypeItem)
            observeData()
            dialog.dismiss()
        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }


}



