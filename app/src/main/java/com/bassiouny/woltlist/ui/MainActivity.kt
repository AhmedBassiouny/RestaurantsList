package com.bassiouny.woltlist.ui

import android.os.Bundle
import com.bassiouny.woltlist.R
import com.bassiouny.woltlist.base.BaseActivity

class MainActivity : BaseActivity<RestaurantsPresenter>(), RestaurantsView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun instantiatePresenter(): RestaurantsPresenter {
        return RestaurantsPresenter(this)
    }
}
