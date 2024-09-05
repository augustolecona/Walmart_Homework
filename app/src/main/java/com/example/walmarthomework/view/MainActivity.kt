package com.example.walmarthomework.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.walmarthomework.Models.*
import com.example.walmarthomework.Network.NetworkClient.showError
import com.example.walmarthomework.R
import com.example.walmarthomework.viewmodel.CountriesViewModel
import io.reactivex.rxjava3.plugins.RxJavaPlugins

private lateinit var countriesRV: RecyclerView
val listOfMovies: ArrayList<Country> = arrayListOf()

private lateinit var adapter: CountryAdapter


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countriesRV = findViewById(R.id.countriesRV)
        countriesRV.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = CountryAdapter(listOfMovies)
        countriesRV.adapter = adapter

        RxJavaPlugins.setErrorHandler { throwable ->
            throwable.printStackTrace()
            Toast.makeText(this, showError(throwable), Toast.LENGTH_LONG).show()

        }

        val viewModel = ViewModelProvider(this).get(CountriesViewModel::class.java)

        viewModel.startRequest()

        val observer = Observer<List<Country>> {
            listOfMovies.clear()
            listOfMovies.addAll(it as ArrayList<Country>)
            adapter.notifyDataSetChanged()
        }
        viewModel.returnlistOfPopularMovies().observe(this, observer)
    }


}
