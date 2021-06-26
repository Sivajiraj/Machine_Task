package com.appentus.machinetask.network

import com.appentus.machinetask.model.response.ImageResponseItem
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.*

interface BaseApiInterface {

    @GET("v2/list")
    fun getImageDetails() : Call<List<ImageResponseItem>>
}