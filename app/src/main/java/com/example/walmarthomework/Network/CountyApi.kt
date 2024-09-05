package com.example.walmarthomework.Network

import com.example.walmarthomework.Models.*
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface CountyApi {
    @GET("peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json")
    fun getCountries(
    ): Observable<List<Country>>
}
