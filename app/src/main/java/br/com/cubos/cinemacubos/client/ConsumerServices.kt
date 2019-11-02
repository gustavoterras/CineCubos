package br.com.cubos.cinemacubos.client

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ConsumerServices {

    fun getClient(): IConsumerServices {

        val httpClient = OkHttpClient.Builder()

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        val loggingInterceptor =
            httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .client(httpClient.addInterceptor(loggingInterceptor).build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(IConsumerServices::class.java)
    }
}
