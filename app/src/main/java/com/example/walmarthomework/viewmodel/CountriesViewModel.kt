package com.example.walmarthomework.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.walmarthomework.Models.*

import com.example.walmarthomework.Network.NetworkClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketException
import java.net.UnknownHostException

class CountriesViewModel : ViewModel() {
    val listOfCountries = MutableLiveData<List<Country>>()
    fun startRequest() {
        NetworkClient.getMovieApi()?.getCountries()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError {
                Log.d(this.javaClass.name, it.message.toString())
            }
            ?.doOnNext {
                listOfCountries.postValue(it)
            }?.subscribe()
    }

    fun returnlistOfPopularMovies(): MutableLiveData<List<Country>> {
        return listOfCountries
    }







}