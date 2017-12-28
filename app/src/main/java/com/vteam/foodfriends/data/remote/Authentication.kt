package com.vteam.foodfriends.data.remote

import android.util.Log
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException

/**
 * Created by H2PhySicS on 12/28/2017.
 */
class Authentication {
    companion object {
        private val LOG_TAG : String = Authentication::class.java.simpleName
    }

    fun login(email: String, password: String, callback: Callback<LoginMutation.AuthResponse>) {
        val client = Client()
        val apolloClient = client.getClient()
        val loginMutation = LoginMutation.builder()
                .email(email)
                .password(password)
                .build()

        val call: ApolloCall<LoginMutation.Data> = apolloClient.mutate(loginMutation)
        call.enqueue(object : ApolloCall.Callback<LoginMutation.Data>() {
            override fun onResponse(response: Response<LoginMutation.Data>) {
                val data = response.data()
                val authResponse = data?.authResponse()
                callback.onSuccess(authResponse!!)
            }

            override fun onFailure(e: ApolloException) {
                callback.onFailed()
            }

        })
    }

    fun register(firstName: String, lastName: String, email: String, password: String) {
        val client = Client()
        val apolloClient = client.getClient()
        val registerMutation = RegisterMutation.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password).build()

        val call: ApolloCall<RegisterMutation.Data> = apolloClient.mutate(registerMutation)
        call.enqueue(object : ApolloCall.Callback<RegisterMutation.Data>() {
            override fun onResponse(response: Response<RegisterMutation.Data>) {
                Log.d(LOG_TAG,"onResponse: " + response)
            }

            override fun onFailure(e: ApolloException) {
                Log.d(LOG_TAG,"Failed " + e)
            }
        })
    }

    fun update(firstName: String, lastName: String, token: String){
        val client = Client()
        val apolloClientToken = client.getClient(token)
        val updateMutation = UpdateMutation.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build()

        val call: ApolloCall<UpdateMutation.Data> = apolloClientToken.mutate(updateMutation)
        call.enqueue(object : ApolloCall.Callback<UpdateMutation.Data>(){
            override fun onResponse(response: Response<UpdateMutation.Data>) {
                Log.d(LOG_TAG,"onResponse: " + response)
            }

            override fun onFailure(e: ApolloException) {
                Log.d(LOG_TAG,"Failed " + e)
            }
        })
    }


}