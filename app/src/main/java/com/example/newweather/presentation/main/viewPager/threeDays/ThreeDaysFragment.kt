package com.example.newweather.presentation.main.viewPager.threeDays

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newweather.KEY_DATA
import com.example.newweather.R
import com.example.newweather.databinding.FragmentThreeDaysBinding
import com.example.newweather.domain.models.retrofit.forecastday.Weather

class ThreeDaysFragment : Fragment() {

    private lateinit var adapter: ThreeDaysAdapter
    private var _binding: FragmentThreeDaysBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThreeDaysBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parseArgument()
    }



    private fun parseArgument(){
        setupRv()
        requireArguments().getParcelable<Weather>(KEY_DATA)?.let {
            binding.cityname.text = String.format(getString(R.string.name_city_from_ten_days_fragment), it.location.region)
            adapter.submitList(it.forecast.forecastday)
        }
    }

    private fun setupRv() {
        val rv = binding.rcTen
        adapter = ThreeDaysAdapter(requireActivity().application)
        rv.adapter = adapter
    }

}