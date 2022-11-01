package com.example.newweather.presentation.singleActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.newweather.R
import com.example.newweather.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        viewModel.getCity()
        viewModel.nameCity.observe(this) {
            if (it == null) {
                findNavController(R.id.nav_host_fragment)
                    .navigate(R.id.action_global_search_fragment)
            } else {
                viewModel.getDataInRetrofit(it.name)
            }
        }
    }


}