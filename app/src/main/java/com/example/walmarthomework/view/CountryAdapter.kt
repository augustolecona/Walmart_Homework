package com.example.walmarthomework.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.walmarthomework.Models.*
import com.example.walmarthomework.R

class CountryAdapter(
    private var countries: List<Country>
) : RecyclerView.Adapter<CountryAdapter.CountriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_country, parent, false)
        return CountriesViewHolder(view)
    }

    override fun getItemCount(): Int = countries.size

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    fun updateCountries(countries: List<Country>) {
        this.countries = countries
        this.notifyDataSetChanged()
    }

    inner class CountriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(country: Country) {

            val countryWithRegion: TextView = itemView.findViewById(R.id.country_region_tv)
            val code: TextView = itemView.findViewById(R.id.code)
            val capital: TextView = itemView.findViewById(R.id.capital)
            if(country.capital.isNullOrBlank())country.capital="Unknown"
            if(country.code.isNullOrBlank())country.code="Unknown"
            if(country.name.isNullOrBlank())country.name="Unknown"
            if(country.region.isNullOrBlank())country.region="Unknown"
            countryWithRegion.text= "${country.name}, ${country.region}"
            code.text= country.code
            capital.text=country.capital
        }

    }
}