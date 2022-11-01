package com.example.newweather.presentation.main.viewPager.tempNow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.newweather.KEY_DATA
import com.example.newweather.R
import com.example.newweather.databinding.FragmentTempNowBinding
import com.example.newweather.domain.models.retrofit.forecastday.Weather


class TempNowFragment : Fragment() {

    private var _binding: FragmentTempNowBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { TempNowAdapter(requireActivity().application) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTempNowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        binding.addOrSearchCity.setOnClickListener {
            requireActivity().findNavController(R.id.nav_host_fragment)
                .navigate(R.id.action_global_search_fragment)
        }
    }


    private fun init() {
        setupRv()
        parseArguments()
    }

    private fun parseArguments() {
        requireArguments().getParcelable<Weather>(KEY_DATA).let {
            it?.let { it1 -> bind(it1) }
        }
    }

    private fun setupRv(){
        val rv = binding.recyclerViewHour
        rv.adapter = adapter
    }

    private fun bind(dataW: Weather) {
        val current = dataW.current
        val forecastDay = dataW.forecast.forecastday[0]
        val location = dataW.location
        with(binding) {
            tempC.text = current.temp_c.toInt().toString()
            wind.text = String.format(
                getString(R.string.windText),
                (current.wind_kph * 0.28).toInt()
            )
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

            adapter.submitList(dataW.forecast.forecastday[0].hour)
        }
    }

}