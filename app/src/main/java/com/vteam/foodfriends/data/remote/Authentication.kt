package com.vteam.foodfriends.data.remote

import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException

/**
 * Created by H2PhySicS on 12/28/2017.
 */
class Authentication{
    fun login(email : String, password : String, callback: Callback<LoginMutation.AuthResponse>){
        val client = Client()
        val apolloClient = client.getClient()
        val loginMutation = LoginMutation.builder()
                .email(email)
                .password(password)
                .build()

        val call : ApolloCall<LoginMutation.Data> = apolloClient.mutate(loginMutation)
        call.enqueue(object : ApolloCall.Callback<LoginMutation.Data>() {
            override fun onResponse(response: Response<LoginMutation.Data>) {
                val data = response.data()
                val authResponse  = data?.authResponse()
                callback.onSuccess(authResponse!!)
            }

            override fun onFailure(e: ApolloException) {
                callback.onFailed()
            }

        })
    }
}