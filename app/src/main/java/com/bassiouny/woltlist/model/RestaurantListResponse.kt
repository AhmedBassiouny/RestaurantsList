package com.bassiouny.woltlist.model

data class RestaurantListResponse(
    val results: List<Restaurant>
)

data class Restaurant(
    val id: Map<String, String>,
    val short_description: List<Description>,
    val name: List<Name>,
    val mainimage: String
)

data class Description(
    val value: String
)

data class Name(
    val value: String
)