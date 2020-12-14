package com.fendy.covid19.fragment

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.fendy.covid19.network.api.repository.ResponseRepository
import com.fendy.covid19.network.api.response.ResponseCovid


/**
 * Created by Fendy Saputro on 12/12/2020.
 * vidis194@gmail.com
 */
class CovidViewModel (private val responseRepository: ResponseRepository, context: Context) {

    var onGetDataCovidSuccess = MutableLiveData<ResponseCovid>()
    var onGetDataCovidFailed = MutableLiveData<Map<Int, String>>()

    fun getDataCovid() {
        responseRepository.getDataCovid { responseCovid: ResponseCovid? ->
            if (responseCovid != null) {
                onGetDataCovidSuccess.value = responseCovid
            } else {
                onGetDataCovidFailed.value = mapOf(400 to "null data")
            }
        }
    }
}