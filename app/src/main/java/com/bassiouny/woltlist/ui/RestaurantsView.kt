package com.bassiouny.woltlist.ui

import com.bassiouny.woltlist.base.BaseView
import com.bassiouny.woltlist.model.Restaurant

interface RestaurantsView : BaseView {
    fun updateList(restaurants: List<Restaurant>)

}
