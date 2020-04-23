package com.bassiouny.woltlist

import com.bassiouny.woltlist.model.Restaurant
import com.bassiouny.woltlist.model.RestaurantListResponse
import com.bassiouny.woltlist.network.WoltApi
import com.bassiouny.woltlist.ui.RestaurantsPresenter
import com.bassiouny.woltlist.ui.RestaurantsView
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class MockTests {

    private val api: WoltApi = mock()
    private lateinit var testScheduler: TestScheduler
    private lateinit var presenter: RestaurantsPresenter
    private val view: RestaurantsView = mock()

    @Before
    fun setUp() {
        testScheduler = TestScheduler()
        presenter = RestaurantsPresenter(view, testScheduler, testScheduler, api)
    }

    @Test
    fun test_getRestaurants_should_callSuccess() {
        val mockedResponse: RestaurantListResponse = mock()

        doReturn(Observable.just(mockedResponse))
            .`when`(api)
            .featchNearByRestaurants("0.0", "0.0")

        presenter.getRestaurants(Pair("0.0", "0.0"))

        testScheduler.triggerActions()

        verify(view).updateList(mockedResponse.results)
    }

    @Test
    fun test_getRestaurants_shouldNot_callNoResult() {
        val mockedResponse: RestaurantListResponse = mock()
        val items = ArrayList<Restaurant>()

        items.add(mock(Restaurant::class.java))

        `when`(mockedResponse.results).thenReturn(items)

        doReturn(Observable.just(mockedResponse))
            .`when`(api)
            .featchNearByRestaurants("0.0", "0.0")

        presenter.getRestaurants(Pair("0.0", "0.0"))

        testScheduler.triggerActions()

        verify(view, times(0)).showNoResults()
    }

    @Test
    fun test_getRestaurants_should_callNoResult() {
        val mockedResponse: RestaurantListResponse = mock()
        val items = ArrayList<Restaurant>()

        `when`(mockedResponse.results).thenReturn(items)

        doReturn(Observable.just(mockedResponse))
            .`when`(api)
            .featchNearByRestaurants("0.0", "0.0")

        presenter.getRestaurants(Pair("0.0", "0.0"))

        testScheduler.triggerActions()

        verify(view).showNoResults()
    }

    @Test
    fun test_getRestaurants_should_callError() {
        val mockedResponse: Throwable = mock()

        doReturn(Observable.just(mockedResponse))
            .`when`(api)
            .featchNearByRestaurants("a", "0.0")

        presenter.getRestaurants(Pair("a", "0.0"))

        testScheduler.triggerActions()

        verify(view).showErrorMessage()
    }

}