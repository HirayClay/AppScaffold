package com.hiray.mvvm.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.hiray.BuildConfig
import com.hiray.tsl.HttpsConfigProvider
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RestApiHelper {

    @Inject
    lateinit var gson: Gson

    @Inject
    lateinit var tslProvider: HttpsConfigProvider

     fun create(): RestApi {
        val okClient = OkHttpClient.Builder()
                .sslSocketFactory(tslProvider.sslSocketFactory,tslProvider.trustManager)
                .build()
        okClient.networkInterceptors().add(Interceptor { chain ->
            val request = chain.request().newBuilder()
                    .addHeader("deviceId", null)
                    .build()
            chain.proceed(request)
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