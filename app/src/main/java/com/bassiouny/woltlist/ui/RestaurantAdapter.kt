package com.bassiouny.woltlist.ui

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bassiouny.woltlist.R
import com.bassiouny.woltlist.model.Restaurant
import com.like.LikeButton
import com.like.OnLikeListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.restaurant_item.view.*
import kotlin.math.min

class RestaurantAdapter(
    private var fetchedRestaurants: ArrayList<Restaurant>,
    private val context: Context
) : RecyclerView.Adapter<RestaurantViewHolder>() {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "MySharedPref",
        AppCompatActivity.MODE_PRIVATE
    )
    private var editor: SharedPreferences.Editor

    init {
        editor = sharedPreferences.edit()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        return RestaurantViewHolder(
            LayoutInflater.from(context).inflate(R.layout.restaurant_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return min(fetchedRestaurants.size, 15)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.name.text = fetchedRestaurants[position].name.first().value
        holder.description.text = fetchedRestaurants[position].short_description.first().value
        Picasso.get().load(fetchedRestaurants[position].mainimage)
            .placeholder(context.resources.getDrawable(R.drawable.response)).into(holder.image)

        holder.favIcon.tag = fetchedRestaurants[position].id["\$oid"]
        holder.favIcon.isLiked =
            sharedPreferences.contains(fetchedRestaurants[position].id["\$oid"])
        holder.favIcon.setOnLikeListener(object : OnLikeListener {
            override fun liked(likeButton: LikeButton) {
                editor.putBoolean(likeButton.tag.toString(), true)
                editor.apply()
            }

            override fun unLiked(likeButton: LikeButton) {
                editor.remove(likeButton.tag.toString())
                editor.apply()
            }
        })
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
    var favIcon: LikeButton = item.favIc
}