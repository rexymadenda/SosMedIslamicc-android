package id.bigio.jakarta.ppid.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
    companion object {
        fun getRetrofitClient(): Retrofit {

            val headerInterceptor = Interceptor { chain ->
                val request = chain.request().newBuilder()
//                    .addHeader("apps-api-key", "m0b1l34ppspp1db3t4123456")
//                    .addHeader("Authorization", "Basic bTBiMWwzcHAxZDpwcDFkbTBiMWwzQGQxNWswbTFuZjB0MWs=")
                    .build()
                chain.proceed(request)
            }

            val clientBuilder = OkHttpClient().newBuilder().addInterceptor(headerInterceptor)

//            if (BuildConfig.DEBUG) {
//                val interceptor = HttpLoggingInterceptor()
//                interceptor.level = HttpLoggingInterceptor.Level.BODY
//                clientBuilder.addInterceptor(interceptor).build()
//            }

            val client = clientBuilder
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://rexymadenda.pe.hu/")
                .build()
        }
    }

}