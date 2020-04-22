package com.bassiouny.woltlist.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bassiouny.woltlist.R
import com.bassiouny.woltlist.model.Restaurant
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.restaurant_item.view.*
import kotlin.math.min

class RestaurantAdapter(
    private var fetchedRestaurants: ArrayList<Restaurant>,
    private val context: Context
) : RecyclerView.Adapter<RestaurantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        return RestaurantViewHolder(
            LayoutInflater.from(context).inflate(R.layout.restaurant_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return min(fetchedRestaurants.size, 15)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.name.text = fetchedRestaurants[position].name[0].value
        holder.description.text = fetchedRestaurants[position].short_description[0].value
        Picasso.get().load(fetchedRestaurants[position].mainimage).into(holder.image)
    }

    fun updateData(updatedRestaurants: ArrayList<Restaurant>) {
        fetchedRestaurants.apply {
            clear()
            addAll(updatedRestaurants)
        }
        notifyDataSetChanged()
    }
}

class RestaurantViewHolder(item: View) : RecyclerView.ViewHolder(item) {
    var name: TextView = item.restaurantName
    var description: TextView = item.restaurantShortDescription
    var image: ImageView = item.restaurantImageView
}