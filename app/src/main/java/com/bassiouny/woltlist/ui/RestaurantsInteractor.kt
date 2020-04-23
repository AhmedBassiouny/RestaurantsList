package com.bassiouny.woltlist.ui

import com.bassiouny.woltlist.model.RestaurantListResponse
import com.bassiouny.woltlist.network.WoltApi
import io.reactivex.Observable
import io.reactivex.Scheduler

class RestaurantsInteractor {
    fun getRestaurants(
        mainThread: Scheduler,
        io: Scheduler,
        api: WoltApi,
        latLon: Pair<String, String>
    ): Observable<RestaurantListResponse> {

        return api.featchNearByRestaurants(latLon.first, latLon.second)
            .observeOn(mainThread)
            .subscribeOn(io)
    }
}