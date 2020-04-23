package com.bassiouny.woltlist.ui

import com.bassiouny.woltlist.base.BasePresenter
import com.bassiouny.woltlist.network.WoltApi
import com.bassiouny.woltlist.util.REFRESH_RATE
import com.bassiouny.woltlist.util.locations
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class RestaurantsPresenter(
    view: RestaurantsView,
    private val mainThread: Scheduler,
    private val io: Scheduler,
    private val service: WoltApi
) : BasePresenter<RestaurantsView>(view) {
    private val restaurantsInteractor = RestaurantsInteractor()
    private val compositeDisposable = CompositeDisposable()

    override fun onViewCreated() {
        periodicLocationChange()
    }

    fun periodicLocationChange() {
        compositeDisposable.add(Observable
            .interval(0, REFRESH_RATE, TimeUnit.SECONDS)
            .map { item ->
                val latLon = locations[item.toInt()]
                getRestaurants(latLon)
            }
            .take(locations.size.toLong())
            .repeatWhen { completed -> completed.delay(REFRESH_RATE, TimeUnit.SECONDS) }
            .subscribe()
        )
    }

    private fun getRestaurants(latLon: Pair<String, String>) {
        compositeDisposable.add(
            restaurantsInteractor.getRestaurants(mainThread, io, service, latLon)
                .subscribe(
                    {
                        view.updateList(it.results)
                    },
                    {
                    }
                )
        )
    }

    override fun onViewDestroyed() {
        compositeDisposable.dispose()
    }

    fun clearDisposeBag() {
        compositeDisposable.clear()
    }
}
