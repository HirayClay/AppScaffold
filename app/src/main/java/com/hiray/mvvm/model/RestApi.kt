package com.hiray.mvvm.model

import com.hiray.mvvm.model.entity.News
import com.hiray.mvvm.model.entity.TopStory
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RestApi {

    @POST("createUser")
    fun createUser(@Query("key") key: String,
                   @Query("phone") phone: String,
                   @Query("password") password: String)


    @GET("before/{date}")
    fun fetchNewsBefore(@Path("date") date: String): Observable<Response<News>>

    @GET("latest")
    fun fetchLatestNews(): Observable<LatestResponse<News,TopStory>>

}