package com.bassiouny.woltlist.ui

import com.bassiouny.woltlist.model.RestaurantListResponse
import com.bassiouny.woltlist.network.WoltApi
import io.reactivex.Observable
import io.reactivex.Scheduler

class RestaurantsInteractor {
    fun getRestaurants(
        mainThread: Scheduler,
        io: Scheduler,
        api: WoltApi
    ): Observable<RestaurantListResponse>? {

        return api.featchNearByRestaurants(60.170187, 24.930599)
            .observeOn(mainThread)
            .subscribeOn(io)
    }
}