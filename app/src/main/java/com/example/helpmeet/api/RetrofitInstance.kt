package com.example.helpmeet.api

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {

    companion object {

        private const val BASE_URL = "https://help-meet.herokuapp.com/"



        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES) // connect timeout
                .writeTimeout(5, TimeUnit.MINUTES) // write timeout
                .readTimeout(5, TimeUnit.MINUTES) // read timeout
                .addInterceptor(logging)
                .retryOnConnectionFailure(true)
                .build()

            /*val client2 = *//*client.addInterceptor { chain ->
                val request: Request = chain.request().newBuilder()
                    .addHeader("Connection", "close")
                    .addHeader("Transfer-Encoding", "chunked")
                    .build()
                chain.proceed(request)
            }*/

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }

        val api: HelpMeetApi by lazy {
            retrofit.create(HelpMeetApi::class.java)
        }

    }
}



/*
object RetrofitInstance {

    private const val BASE_URL = "https://help-meet.herokuapp.com"

    val api: HelpMeetApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HelpMeetApi::class.java)
    }
}*/
