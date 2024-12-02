package com.example.imdbapp.ui.dialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.example.imdbapp.R
import com.example.imdbapp.databinding.FragmentFilterBinding

class FilterFragment : DialogFragment() {

    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!
    val listOfFilters: List<String> = listOf(
        "Year",
        "Type"
    )

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

        binding.orderBySpinner.adapter = filtersAdapter
        binding.orderBySpinner.setSelection(0)
        binding.orderBySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when(listOfFilters[position]){
                    "Year" -> {
                        selectYear()
                    }
                    "Type" -> {
                        selectType()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

    }

    private fun selectYear(){

        val years = (1900..2024).map{it.toString()}
        val yearsAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, years)
        yearsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.whatWillBeOrderedSpinner.adapter = yearsAdapter
    }

    private fun selectType(){

        val types = listOf("Movie", "Series", "Game")
        val typesAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, types)
        typesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.whatWillBeOrderedSpinner.adapter = typesAdapter

    }


}