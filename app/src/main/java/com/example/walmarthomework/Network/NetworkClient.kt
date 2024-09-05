package com.example.walmarthomework.Network

import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClient {
    private val BASEURL = "https:/gist.githubusercontent.com/"
    private val retrogitBuilder:
            Retrofit.Builder = Retrofit.Builder()
        .baseUrl(BASEURL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
    private val retrofitClient = retrogitBuilder.build()
    private val movieApi = retrofitClient.create(CountyApi::class.java)

    fun getMovieApi(): CountyApi? {
        return movieApi
    }

    fun showError(throwable: Throwable): String {
        if (throwable is HttpException) {

            val code = (throwable as HttpException).code().toString()
            when (code) {
                "403" -> {

                    return "You have reached the limit of intents.. please try again in a couple of minutes "

                }
                "404" -> {

                    return "We can't find that page "

                }
                "409" -> {
                    return "Error 409"
                }
                "400, 422" -> {

                    return "There's something wrong  with your search"

                }

                else -> {
                    return "Something went wrong =( "
                }
            }
        } else {

            return "There's a problem with the network. Please verify your network "

        }
    }
}