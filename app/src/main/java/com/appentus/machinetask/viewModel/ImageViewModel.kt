package com.appentus.machinetask.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appentus.machinetask.model.response.ImageResponseItem
import com.appentus.machinetask.network.ApiRetrofitClient
import com.appentus.machinetask.repository.IApiResponse
import com.appentus.machinetask.repository.ImageRepository

class ImageViewModel : ViewModel() {

    //Calling the repository
    private val repository = ImageRepository(ApiRetrofitClient.baseApiInterface)

    //success response
    private val _handleSuccess = MutableLiveData<List<ImageResponseItem>>()
    val handleSuccess: LiveData<List<ImageResponseItem>> = _handleSuccess

    private val _handleFailure = MutableLiveData<Boolean>()
    val handleFailure: LiveData<Boolean> = _handleFailure

    fun setHandleSuccess(value: List<ImageResponseItem>) {
        _handleSuccess.value = value
    }

    fun setHandleFailure(value: Boolean) {
        _handleFailure.value = value
    }

    //@Get  Api response
    fun getAllImages(){
        repository.getImages(object : IApiResponse{
            override fun onSuccess(response: Any) {
                if (response != null) {
                    setHandleSuccess(response as List<ImageResponseItem>)
                } else {
                    setHandleFailure(true)
                }
            }
                override fun onFailure() {
                    setHandleFailure(true)
                }
            })
    }
}