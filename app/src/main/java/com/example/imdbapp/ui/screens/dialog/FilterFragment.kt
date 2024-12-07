package com.example.imdbapp.ui.screens.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.example.imdbapp.databinding.FragmentFilterBinding
import com.example.imdbapp.data.repository.MainRepo
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


        selectYear()
        selectType()

//        binding.applyBtn.setOnClickListener {
//            it as AppCompatButton
//            it.clicked(it.id)
//        }
        binding.cancelBtn.setOnClickListener {
            dismiss()
        }

    }



    private fun selectYear(){

        val years = (1980..2024).map{it.toString()}
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

    }

    private fun selectType(){

        val types = listOf("Movie", "Series", "Game")
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

    }


}