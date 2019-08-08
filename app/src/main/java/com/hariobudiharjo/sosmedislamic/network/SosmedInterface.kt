package com.hariobudiharjo.sosmedislamic.network

import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*


interface SosmedInterface {

    @POST("api.php?action=login")
    @FormUrlEncoded
    fun login(
        @Field("email") email: String,
        @Field("pass") pass: String?
    ): Observable<ResponseBody>


    @FormUrlEncoded
    @POST("api.php?action=register")
    fun registrasi(
        @Field("nama") nama: String,
        @Field("email") email: String,
        @Field("pass") pass: String
    ): Observable<ResponseBody>

    @GET("api.php?action=chat")
    fun listchat(
        @Query("id") id: String
    ): Observable<ResponseBody>

    @GET("api.php?action=listgrup")
    fun listgrup(): Observable<ResponseBody>


    @FormUrlEncoded
    @POST("api.php?action=post")
    fun sendMessage(
        @Field("message") message: String,
        @Field("id") id: String,
        @Field("gid") gid: String
    ): Observable<ResponseBody>


    @FormUrlEncoded
    @POST("api.php?action=postaudio")
    fun sendAudio(
        @Field("id") id: String,
        @Field("gid") gid: String,
//        @Field("audio") audio: String
        @Part body: MultipartBody.Part
    ): Observable<ResponseBody>
}