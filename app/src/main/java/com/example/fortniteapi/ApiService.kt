package com.example.fortniteapi


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("v2/cosmetics/br/search/all?matchMethod=contains")
    suspend fun getCosmetics(@Query("name") name: String): Response<CosmeticsResponse>

    @GET("v2/cosmetics/br/search/ids")
    suspend fun getCosmeticDetail(@Query("id") id: String): Response<CosmeticsResponse>
}