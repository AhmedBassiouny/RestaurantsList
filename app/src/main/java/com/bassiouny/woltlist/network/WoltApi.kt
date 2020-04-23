package com.bassiouny.woltlist.network

import com.bassiouny.woltlist.model.RestaurantListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WoltApi {
    @GET("venues")
    fun featchNearByRestaurants(
        @Query("lat") lat: String,
        @Query("lon") lon: String
    ): Observable<RestaurantListResponse>
}
