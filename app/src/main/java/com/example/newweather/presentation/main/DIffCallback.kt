package com.example.newweather.presentation.main

import androidx.recyclerview.widget.DiffUtil
import com.example.newweather.domain.models.retrofit.forecastday.Hour

class DIffCallback: DiffUtil.ItemCallback<Hour>() {
    override fun areItemsTheSame(oldItem: Hour, newItem: Hour): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Hour, newItem: Hour): Boolean {
        return oldItem == newItem
    }
}