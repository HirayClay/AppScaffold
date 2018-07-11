package com.hiray.data

import retrofit2.http.POST
import retrofit2.http.Query

interface RestApi {

    /*https://www.apiopen.top/createUser?key=00d91e8e0cca2b76f515926a36db68f5&phone=13594347817&passwd=123654*/
    @POST("createUser")
    fun createUser(@Query("key") key: String,
                   @Query("phone") phone: String,
                   @Query("password") password: String)

}