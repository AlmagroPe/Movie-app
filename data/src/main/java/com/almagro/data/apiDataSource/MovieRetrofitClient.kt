package com.almagro.data.apiDataSource

import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import okio.Buffer
import okio.BufferedSource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MovieRetrofitClient
@Inject constructor() {

    companion object {
        const val API_KEY = "a3f8a7c85f022b9a8858e535d07a0672"
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient()
            .newBuilder()
            .addInterceptor(HttpLoggingInterceptor())
            .addInterceptor Interceptor@{ chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("api_key", API_KEY)
                    .build()

                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor try {chain.proceed(request)} catch (e: IOException) {
                    Response.Builder()
                        .code(418)
                        .request(chain.request())
                        .body(object: ResponseBody() {
                            override fun contentLength() = 0L
                            override fun contentType(): MediaType? = null
                            override fun source(): BufferedSource = Buffer()
                        })
                        .message(e.message ?: e.toString())
                        .protocol(Protocol.HTTP_1_1)
                        .build()

                }
            }
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    val api: MovieApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(MovieApi::class.java)
    }
}