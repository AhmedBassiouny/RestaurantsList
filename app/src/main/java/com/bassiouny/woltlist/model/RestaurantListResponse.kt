package com.bassiouny.woltlist.model

data class RestaurantListResponse(
    val results: List<Restaurant>
)

data class Restaurant(
    val short_description: List<Description>
)

data class Description(
    val value: String
)
