package com.example.smartapp.Request

import com.example.smartapp.Response.AllDataResponse
import retrofit2.Call
import retrofit2.http.GET

interface Catsdata
{
   @GET("facts")
   public fun getCatFacts():Call<AllDataResponse>
}