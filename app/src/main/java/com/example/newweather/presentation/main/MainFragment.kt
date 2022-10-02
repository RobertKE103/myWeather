package com.example.newweather.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.newweather.R
import com.example.newweather.databinding.FragmentMainBinding
import com.example.newweather.domain.models.retrofit.forecastday.Weather
import retrofit2.Response


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainFragmentViewModel
    private lateinit var adapter: MainAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
        init()
    }

    private fun init() {
        try {
            viewModel = ViewModelProvider(this, MainFragmentViewModelFactory(
                requireActivity().application))[MainFragmentViewModel::class.java]
            viewModel.requestWeather()
            viewModel.weather.observe(viewLifecycleOwner) {
                bind(it!!)
                response = it
                adapter.submitList(it.body()!!.forecast.forecastday[0].hour)
            }
        } catch (_: Exception) {
        }
    }

    private fun bind(dataW: Response<Weather>) {
        val current = dataW.body()!!.current
        val forecastDay = dataW.body()!!.forecast.forecastday[0]
        val location = dataW.body()!!.location
        with(binding){
            tempC.text = current.temp_c.toInt().toString()
            wind.text = String.format(getString(R.string.windText),
                (current.wind_kph * 0.28).toInt())
            humidityWeather.text = "${current.humidity} %"
            rain.text = "${forecastDay.day.daily_chance_of_rain} %"
            cloud.text = "${current.cloud} %"
            DayWeeks.text = location.localtime
            nameCity.text = location.region
            Glide.with(requireActivity().application)
                .load("https:" + current.condition.icon)
                .centerCrop()
                .placeholder(R.drawable.ic_baseline_search_24)
                .into(binding.imgWeather)
        }
    }

    private fun setupRv(){
        val recyclerView = binding.rcMainListHour
        adapter = MainAdapter(requireActivity().application)
        recyclerView.adapter = adapter
    }

    companion object {
        var response: Response<Weather>? = null
    }

}