package com.fendy.covid19.network.api.service

import android.os.Build
import com.fendy.covid19.BuildConfig
import com.fendy.covid19.network.api.response.ResponseCovid
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit


/**
 * Created by Fendy Saputro on 12/12/2020.
 * vidis194@gmail.com
 */
interface ApiService {

    @GET("indonesia/provinsi")
    fun getDataCovidProvince() : Call<ResponseCovid>?


    companion object {
        operator fun invoke(baseUrl: String): ApiService {
            val client = OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .build()

            return Retrofit.Builder()
                    .client(client)
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(ApiService::class.java)
        }

        private fun getVersion(): Int {
            var versionName = BuildConfig.VERSION_NAME
            var appVersion: Int = 0
            try {
                versionName = versionName.replace(".", "")
                appVersion = versionName.toInt()
            } catch (e: Exception) {
            }
            return appVersion
        }

        fun createService(baseUrl: String): ApiService {
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(Interceptor { chain: Interceptor.Chain ->
                val original = chain.request()
                // Request customization: add request headers
                val requestBuilder = original.newBuilder()
                        .header("AppVersion", getVersion().toString())
                        .header("OSVersion", Build.VERSION.RELEASE.toString())
                        .header("BuildCode", BuildConfig.VERSION_CODE.toString())
                        .header("Platform", "android")
                        .method(original.method, original.body)
                val request = requestBuilder.build()
                chain.proceed(request)
            })
            val client = httpClient
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .build()

            val gson = GsonBuilder()
                    .setLenient()
                    .create()

            return Retrofit.Builder()
                    .client(client)
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(ApiService::class.java)
        }
    }

}