package com.fendy.covid19.network.api.repository

import com.fendy.covid19.App
import com.fendy.covid19.network.api.response.ResponseCovid
import com.fendy.covid19.network.api.service.ApiService
import com.fendy.covid19.network.api.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Fendy Saputro on 12/12/2020.
 * vidis194@gmail.com
 */
class ResponseRepository {

    private val apiService: ApiService = ApiService.createService(App.CASE_INA_URL)

    fun getDataCovid(listener: (ResponseCovid?) -> Unit) {
        apiService.getDataCovidProvince()
                ?.enqueue(object : Callback<ResponseCovid> {
                    override fun onResponse(call: Call<ResponseCovid>, response: Response<ResponseCovid>) {
                        if (response.isSuccessful) {
                            listener(response.body())
                        } else {
                            listener(null)
                        }
                    }

                    override fun onFailure(call: Call<ResponseCovid>, t: Throwable) {
                        listener(null)
                    }

                })
    }

}