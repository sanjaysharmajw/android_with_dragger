package com.rv.tasktechstalwarts.feature_dashboard.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rv.tasktechstalwarts.common.ZERO
import com.rv.tasktechstalwarts.databinding.ItemLayoutFoodListBinding
import com.rv.tasktechstalwarts.feature_dashboard.domain.model.FavouriteListResponse

class FavouriteItemAdapter(
    private val context: Context,
    private val response: List<FavouriteListResponse>
) : RecyclerView.Adapter<FavouriteItemAdapter.ViewHolder>() {
    private var filteredRes: List<FavouriteListResponse?>? = response

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavouriteItemAdapter.ViewHolder {
        return ViewHolder(
            ItemLayoutFoodListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavouriteItemAdapter.ViewHolder, position: Int) {
        holder.binding.tvDishName.text = filteredRes?.get(position)?.name.toString()
        com.rv.tasktechstalwarts.utils.Util.setImage(
            context,
            holder.binding.ivImage,
            filteredRes?.get(position)?.image
        )
    }

    override fun getItemCount(): Int {
        return filteredRes?.size ?: ZERO
    }

    class ViewHolder(val binding: ItemLayoutFoodListBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    @SuppressLint("NotifyDataSetChanged")
    fun filterListResponse(filteredData: List<FavouriteListResponse?>?) {
            this.filteredRes = filteredData
        notifyDataSetChanged()
    }

}