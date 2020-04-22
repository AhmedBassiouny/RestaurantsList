package com.bassiouny.woltlist.network

import com.bassiouny.woltlist.model.RestaurantListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WoltApi {
    @GET("venues")
    fun featchNearByRestaurants(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Observable<RestaurantListResponse>
}
