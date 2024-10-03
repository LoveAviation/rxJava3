package com.example.lesson14

import retrofit2.http.GET

interface RandomFactApi {
    @GET("random")
    suspend fun getFacts(): RandomFact
}