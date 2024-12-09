package com.example.imdbapp.ui.screens.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.imdbapp.databinding.FragmentFilterBinding
import com.example.imdbapp.data.repository.MainRepo
import com.example.imdbapp.ui.screens.search.SearcViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FilterFragment : DialogFragment() {

    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!
    val listOfFilters: List<String> = listOf(
        "Year",
        "Type"
    )
    var selectedFilterItem: String? = null
    @Inject lateinit var mainRepo: MainRepo
    private val viewModel : SearcViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val filtersAdapter = ArrayAdapter(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listOfFilters)
        filtersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        var selectedYear = selectYear()
        var selectedType = selectType()

        binding.applyBtn.setOnClickListener {
            applyFilter(selectedYear,selectedType)
            dismiss()
        }
        binding.cancelBtn.setOnClickListener {
            dismiss()
        }

    }

    private fun applyFilter(selectedYear: String?, selectedType: String?){
        var applyYear: String? = selectedYear
        var applyType: String? = selectedType
        if(applyType == "Type"){
            applyType = null
        }
        if(applyYear == "Year"){
            applyYear = null
        }
        viewModel.getFilteredList(applyYear,applyType)
    }



    private fun selectYear(): String?{

        val years: MutableList<String> = mutableListOf("Year")
        val year = (1980..2024).map{it.toString()}
        years.addAll(year)
        val yearsAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, years)
        yearsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.orderYearSpinner.adapter = yearsAdapter
        binding.orderYearSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedFilterItem = years[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }
        return selectedFilterItem

    }

    private fun selectType(): String?{

        val types = listOf("Type","Movie", "Series", "Game")
        val typesAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, types)
        typesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.orderTypeSpinner.adapter = typesAdapter
        binding.orderTypeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedFilterItem = types[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }
        return selectedFilterItem

    }


}