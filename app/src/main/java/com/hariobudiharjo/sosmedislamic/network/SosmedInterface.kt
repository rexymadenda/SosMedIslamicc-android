package id.bigio.jakarta.ppid.api

import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*


interface SosmedInterface {

    @POST("/apps/dip/format/json")
    @FormUrlEncoded
    fun login(
        @Field("kategori") kategori: String,
        @Field("jangka_waktu") jangka_waktu: String?
    ): Observable<ResponseBody>


    @FormUrlEncoded
    @POST("apps/signup/format/json")
    fun registrasi(
        @Field("nama_lengkap") nama: String,
        @Field("nik_no_ktp") nik: String,
        @Field("kategori_nik") kategori_nik: String
    ): Observable<ResponseBody>

    @FormUrlEncoded
    @POST("apps/signup/format/json")
    fun listchat(
        @Field("nama_lengkap") nama: String,
        @Field("nik_no_ktp") nik: String,
        @Field("kategori_nik") kategori_nik: String
    ): Observable<ResponseBody>



    @FormUrlEncoded
    @POST("apps/signup/format/json")
    fun listgrup(
        @Field("nama_lengkap") nama: String,
        @Field("nik_no_ktp") nik: String,
        @Field("kategori_nik") kategori_nik: String
    ): Observable<ResponseBody>



    @FormUrlEncoded
    @POST("apps/signup/format/json")
    fun sendMessage(
        @Field("nama_lengkap") nama: String,
        @Field("nik_no_ktp") nik: String,
        @Field("kategori_nik") kategori_nik: String
    ): Observable<ResponseBody>



    @FormUrlEncoded
    @POST("apps/signup/format/json")
    fun sendAudio(
        @Field("nama_lengkap") nama: String,
        @Field("nik_no_ktp") nik: String,
        @Field("kategori_nik") kategori_nik: String
    ): Observable<ResponseBody>
}