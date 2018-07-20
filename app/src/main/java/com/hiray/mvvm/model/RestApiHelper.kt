package com.hiray.mvvm.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.hiray.BuildConfig
import com.hiray.tsl.TslProvider
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RestApiHelper @Inject constructor(var gson: Gson, var tslProvider: TslProvider) {


    fun create(): RestApi {
        val okClient = OkHttpClient.Builder()
                .connectTimeout(2000L,TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .sslSocketFactory(tslProvider.sslSocketFactory, tslProvider.trustManager)
                .build()
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okClient)
                .baseUrl(BuildConfig.BASE_URL)
                .build().create(RestApi::class.java)

    }
}

class LatestResponse<T, R>(@SerializedName("news") var news: List<T>,
                           var date: String,
                           @SerializedName("top_stories") var topStories: List<R>,
                           @SerializedName("is_today") var today: Boolean)

class Response<T>(var date: String,
                  @SerializedName("news") var news: List<T>
)


