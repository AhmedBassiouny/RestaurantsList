package com.bassiouny.woltlist.network

import io.reactivex.Observable
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WoltApi {
    @GET("/venues")
    fun FeatchNearByRestaurants(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Observable<Response<JSONObject>>
}
