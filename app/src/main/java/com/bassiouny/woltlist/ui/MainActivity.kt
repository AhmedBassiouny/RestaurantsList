package com.bassiouny.woltlist.ui

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bassiouny.woltlist.R
import com.bassiouny.woltlist.base.BaseActivity
import com.bassiouny.woltlist.model.Restaurant
import com.bassiouny.woltlist.network.Client
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<RestaurantsPresenter>(), RestaurantsView {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RestaurantAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var fetchedRestaurants = ArrayList<Restaurant>()

    private var client = Client()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.onViewCreated()
        setupList()
    }

    private fun setupList() {
        viewManager = LinearLayoutManager(this)
        viewAdapter = RestaurantAdapter(fetchedRestaurants, this)

        recyclerView = restaurantList.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun instantiatePresenter(): RestaurantsPresenter {
        return RestaurantsPresenter(
            this,
            AndroidSchedulers.mainThread(),
            Schedulers.io(),
            client.getService()
        )
    }

    override fun updateList(restaurants: List<Restaurant>) {
        viewAdapter.updateData(restaurants as ArrayList<Restaurant>)
    }
}
