package com.vteam.foodfriends.data.remote

import android.content.Context
import com.apollographql.apollo.ApolloClient
import com.vteam.foodfriends.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Created by H2PhySicS on 12/28/2017.
 */
class Client{
    fun getClient() : ApolloClient{
        val okHttp : OkHttpClient.Builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG){
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttp.addInterceptor(loggingInterceptor)

        }

        val apolloClient = ApolloClient.builder().serverUrl("http://vteam.info/graphql/")
                .okHttpClient(okHttp.build())
                .build()
        return apolloClient
    }

    fun getClient(token : String) : ApolloClient{
        val okHttp : OkHttpClient.Builder = OkHttpClient.Builder()
                .addInterceptor{chain: Interceptor.Chain? ->
                    val request : Request = chain!!.request()
                    val builder : Request.Builder = request.newBuilder()
                    builder.header("Authorization", token)
                    chain.proceed(builder.build())
                }

        if (BuildConfig.DEBUG){
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttp.addInterceptor(loggingInterceptor)

        }

        val apolloClient = ApolloClient.builder().serverUrl("http://vteam.info/graphql/")
                .okHttpClient(okHttp.build())
                .build()

        return apolloClient
    }
}