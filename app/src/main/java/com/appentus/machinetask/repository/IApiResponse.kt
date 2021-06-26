package com.appentus.machinetask.repository

interface IApiResponse {
    fun onSuccess(response: Any)
    fun onFailure()
}