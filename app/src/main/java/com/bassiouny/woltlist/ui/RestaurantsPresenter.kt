package com.bassiouny.woltlist.ui

import android.annotation.SuppressLint
import android.util.Log
import com.bassiouny.woltlist.base.BasePresenter
import com.bassiouny.woltlist.network.WoltApi
import io.reactivex.Scheduler

class RestaurantsPresenter(
    view: RestaurantsView,
    private val mainThread: Scheduler,
    private val io: Scheduler,
    private val service: WoltApi
) : BasePresenter<RestaurantsView>(view) {

    private val restaurantsInteractor = RestaurantsInteractor()

    @SuppressLint("CheckResult")
    override fun onViewCreated() {
        restaurantsInteractor.getRestaurants(mainThread, io, service)
            ?.subscribe(
                {
                    Log.d("response", "success")
                },
                {
                    Log.d("response", "fail")
                }
            )
    }
}
