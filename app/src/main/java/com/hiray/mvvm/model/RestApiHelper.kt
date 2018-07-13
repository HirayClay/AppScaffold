package com.hiray.mvvm.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.hiray.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RestApiHelper {

    @Inject
    lateinit var gson: Gson


     fun create(): RestApi {
        val okClient = OkHttpClient()
        okClient.networkInterceptors().add(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val request = chain.request().newBuilder()
                        .addHeader("deviceId", null)
                        .build()
                return chain.proceed(request)
            }

        })
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okClient)
                .baseUrl(BuildConfig.BASE_URL)
                .build().create(RestApi::class.java)

    }
}

class Response<T>(@SerializedName("news") var t: List<T>,
                  var date: String,
                  @SerializedName("is_today") var today: Boolean)