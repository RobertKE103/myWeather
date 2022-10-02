package com.example.newweather.presentation.tenDays

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newweather.databinding.FragmentTenDaysBinding
import com.example.newweather.domain.models.retrofit.forecastday.Weather
import retrofit2.Response

class TenDaysFragment(private val data: Response<Weather>) : Fragment() {



    private lateinit var viewModel: TenDaysViewModel
    private var _binding: FragmentTenDaysBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTenDaysBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    companion object {

        fun newInstance(data: Response<Weather>): TenDaysFragment{
            return TenDaysFragment(data)
        }
    }
}