package com.example.newweather.presentation.startWork

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.newweather.R
import com.example.newweather.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchFragmentViewModel
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[SearchFragmentViewModel::class.java]
//        viewModel.weatherList.observe(viewLifecycleOwner) {
//            adapter.setList = it
//        }

        setOnEditorActionListener()
        onFocusChangeListener()
        setOnClickListener()
    }



    private fun setOnClickListener() {
        binding.inputNameCity.setOnClickListener {
            binding.inputNameCity.isCursorVisible = true
        }
    }

    private fun onFocusChangeListener() {
        binding.inputNameCity.onFocusChangeListener =
            View.OnFocusChangeListener { _, p1 ->
                binding.inputNameCity.isCursorVisible = p1
            }
    }

    private fun setOnEditorActionListener() {
        binding.inputNameCity.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                addCityFromVd(v)
                cursorFalse()
                binding.inputNameCity.text = null
                removeKeyboard()
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun addCityFromVd(v: TextView) {
        val text = v.text.toString()
        viewModel.addCity(text)
    }

    private fun removeKeyboard() {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.inputNameCity.windowToken, 0)
    }

    private fun cursorFalse() {
        binding.inputNameCity.isCursorVisible = false
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, SearchFragment())
            .addToBackStack(null)
            .commit()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}