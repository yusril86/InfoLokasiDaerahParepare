package com.pareandroid.infodaerahparepare.Api


import com.pareandroid.infodaerahparepare.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Config {

    fun providesHttpAdapter(): Retrofit {
        return Retrofit.Builder().apply {
            client(providesHttpClient())
            baseUrl("https://dev.farizdotid.com/api/daerahindonesia/")
            addConverterFactory(GsonConverterFactory.create())
        }.build()
    }
   private fun providesHttpClient(): OkHttpClient{
        return OkHttpClient.Builder().apply {
            retryOnConnectionFailure(true)
            addInterceptor(providesInterceptor())
        }.build()
    }

    private fun providesInterceptor():Interceptor{
        return HttpLoggingInterceptor().apply {
            level = when (BuildConfig.DEBUG){
            true -> Level.BODY
            false -> Level.NONE
        }
        }
    }

}