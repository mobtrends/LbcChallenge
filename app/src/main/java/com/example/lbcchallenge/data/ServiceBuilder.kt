package com.example.lbcchallenge.data

import android.content.Context
import com.example.data.RetrofitUtil
import retrofit2.Retrofit

object ServiceBuilder {

    fun getRetrofit(context: Context): Retrofit {
        return RetrofitUtil.buildRestAdapter(
            context,
            BASE_URL,
            CONNECTION_TIMEOUT,
            SOCKET_TIMEOUT,
            true
        )
    }

    private const val CONNECTION_TIMEOUT = 20000
    private const val SOCKET_TIMEOUT = 10000
    private const val BASE_URL = "https://static.leboncoin.fr"
}