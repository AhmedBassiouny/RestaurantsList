package com.bassiouny.woltlist.ui

import android.os.Bundle
import com.bassiouny.woltlist.R
import com.bassiouny.woltlist.base.BaseActivity
import com.bassiouny.woltlist.network.Client
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : BaseActivity<RestaurantsPresenter>(), RestaurantsView {

    private var clinet = Client()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.onViewCreated()
    }

    override fun instantiatePresenter(): RestaurantsPresenter {
        return RestaurantsPresenter(
            this,
            AndroidSchedulers.mainThread(),
            Schedulers.io(),
            clinet.getService()
        )
    }
}
