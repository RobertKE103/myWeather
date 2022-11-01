package com.example.newweather.presentation.main.viewPager.tempNow

import android.app.Application
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.newweather.R
import com.example.newweather.databinding.ItemRvFromTempNowFragmentBinding
import com.example.newweather.domain.models.retrofit.forecastday.Hour
import com.example.newweather.presentation.main.DIffCallback

class TempNowAdapter(private val application: Application) :
    ListAdapter<Hour, TempNowAdapter.MyViewHolder>(DIffCallback()) {

    class MyViewHolder(
        private val binding: ItemRvFromTempNowFragmentBinding,
        private val application: Application
    ) :
        ViewHolder(binding.root) {
        fun bind(data: Hour) {
            with(binding) {
                temp.text = data.temp_c.toInt().toString()
                time.text = data.time.takeLast(5)
                Glide.with(application)
                    .load("https:" + data.condition.icon)
                    .centerCrop()
                    .placeholder(R.drawable.ic_baseline_search_24)
                    .into(imageWeather)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRvFromTempNowFragmentBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding, application)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}