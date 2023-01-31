package com.example.createyourfirstapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(view: View): RecyclerView.ViewHolder(view){

    val cityName: TextView
    init {
        cityName = view.findViewById(R.id.city_name)
    }
}

class CityAdapter(val cities: List<City>): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cityView = LayoutInflater.from(parent.context).inflate(R.layout.city_item_list, parent, false)
        return ViewHolder(cityView)
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cityName.text = cities[position].name
    }
}