package com.fendy.covid19.network.api.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Fendy Saputro on 12/12/2020.
 * vidis194@gmail.com
 */
class RetrofitClient {

    private val BASE_URL = "https://api.kawalcorona.com/"
    var retrofit: Retrofit? = null

    fun getRetrofitInstance(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return retrofit
    }
}