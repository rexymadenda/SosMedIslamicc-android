package com.hariobudiharjo.sosmedislamic.network

import com.hariobudiharjo.sosmedislamic.model.api.*
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
    ): Observable<LoginResponse>


    @FormUrlEncoded
    @POST("api.php?action=register")
    fun registrasi(
        @Field("nama") nama: String,
        @Field("email") email: String,
        @Field("pass") pass: String
    ): Observable<RegistrasiResponse>

    @GET("api.php?action=chat")
    fun listchat(
        @Query("id") id: String
    ): Observable<ListChatResponse>

    @GET("api.php?action=listgrup")
    fun listgrup(): Observable<ListGrupResponse>


    @FormUrlEncoded
    @POST("api.php?action=post")
    fun sendMessage(
        @Field("message") message: String,
        @Field("id") id: String,
        @Field("gid") gid: String
    ): Observable<KirimMessageResponse>


    @FormUrlEncoded
    @POST("api.php?action=postaudio")
    fun sendAudio(
        @Field("id") id: String,
        @Field("gid") gid: String,
//        @Field("audio") audio: String
        @Part body: MultipartBody.Part
    ): Observable<KirimAudioResponse>
}