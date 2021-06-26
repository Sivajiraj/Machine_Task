package com.appentus.machinetask.repository

import com.appentus.machinetask.model.response.ImageResponseItem
import com.appentus.machinetask.network.BaseApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImageRepository (private val baseApiInterface: BaseApiInterface) : IImageRepository {

    override fun getImages(apiResponse: IApiResponse) {

        CoroutineScope(Dispatchers.IO).launch {
            val call = baseApiInterface.getImageDetails()
            call.enqueue(object : Callback<List<ImageResponseItem>?> {
                override fun onResponse(call: Call<List<ImageResponseItem>?>, response: Response<List<ImageResponseItem>?>) {
                    if (response.body() != null) {
                        apiResponse.onSuccess(response.body() as List<ImageResponseItem>)
                    } else {
                        apiResponse.onFailure()
                    }
                }
                override fun onFailure(call: Call<List<ImageResponseItem>?>, t: Throwable) {
                    apiResponse.onFailure()
                }
            })
        }
    }
}