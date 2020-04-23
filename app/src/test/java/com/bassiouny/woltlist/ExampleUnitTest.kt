package com.bassiouny.woltlist

import android.content.Context
import com.bassiouny.woltlist.model.Restaurant
import com.bassiouny.woltlist.model.RestaurantListResponse
import com.bassiouny.woltlist.network.Client
import com.bassiouny.woltlist.ui.RestaurantsPresenter
import com.bassiouny.woltlist.ui.RestaurantsView
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ExampleUnitTest {
    private val view = TestListView()
    private val client = Client()
    private val presenter: RestaurantsPresenter =
        RestaurantsPresenter(
            view,
            Schedulers.trampoline(),
            Schedulers.trampoline(),
            client.getService()
        )

    @Before
    fun setUp() {
        view.reset()
    }

    @Test
    fun getRestaurants_success() {
        presenter.getRestaurants(Pair("0.0", "0.0"))
        Assert.assertEquals("Checking updateReposCounter", 1, view.updateRestaurantsCounter)
        Assert.assertEquals("Checking showErrorCounter", 0, view.logErrorCounter)
    }

    @Test
    fun restaurantsInfo_exist() {
        presenter.getRestaurants(Pair("0.0", "0.0"))
        Assert.assertEquals("Checking updateReposCounter", 1, view.updateRestaurantsCounter)
        Assert.assertEquals("Checking showErrorCounter", 0, view.logErrorCounter)
        Assert.assertEquals("Checking updateReposCounter", true, view.idIsNotNull)
    }

    @Test
    fun getRestaurants_fail() {
        presenter.getRestaurants(Pair("abc", "0.0"))
        Assert.assertEquals("Checking updateReposCounter", 0, view.updateRestaurantsCounter)
        Assert.assertEquals("Checking showErrorCounter", 1, view.logErrorCounter)
    }


    class TestListView : RestaurantsView {
        var updateRestaurantsCounter = 0
        var logErrorCounter = 0
        var idIsNotNull = false
        lateinit var restaurantsResponse: RestaurantListResponse

        override fun updateList(restaurants: List<Restaurant>) {
            updateRestaurantsCounter++
            this.restaurantsResponse = RestaurantListResponse(restaurants)
            idNotNull(restaurants.first())
        }

        override fun logError(error: String) {
            logErrorCounter++
        }

        override fun showNoResults() {
            TODO("Not yet implemented")
        }

        override fun getContext(): Context? {
            TODO("Not yet implemented")
        }

        fun idNotNull(restaurant: Restaurant) {
            idIsNotNull = restaurant.id["\$oid"] != null
        }

        fun reset() {
            updateRestaurantsCounter = 0
            logErrorCounter = 0
            idIsNotNull = false
        }
    }
}

