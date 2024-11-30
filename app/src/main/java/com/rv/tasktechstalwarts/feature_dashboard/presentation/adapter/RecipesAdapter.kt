package com.rv.tasktechstalwarts.feature_dashboard.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rv.tasktechstalwarts.common.ZERO
import com.rv.tasktechstalwarts.feature_dashboard.domain.model.Recipe
import com.rv.tasktechstalwarts.databinding.ItemLayoutFoodListBinding
import com.rv.tasktechstalwarts.utils.Util

class RecipesAdapter(private val requireContext: Context, private val recipes: List<Recipe?>?,private val onItemClick: (Recipe) -> Unit) :
    RecyclerView.Adapter<RecipesAdapter.ViewHolder>() {
        private var filteredRes: List<Recipe?>? = recipes

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesAdapter.ViewHolder {
        return ViewHolder(
            ItemLayoutFoodListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecipesAdapter.ViewHolder, position: Int) {
        val view = holder.binding
        view.tvDishName.text = filteredRes?.get(position)?.name.toString()
        Util.setImage(requireContext,view.ivImage,filteredRes?.get(position)?.image)
        view.clParentLayout.setOnClickListener {
            filteredRes?.get(position)?.let { data -> onItemClick(data) }
        }
    }

    override fun getItemCount(): Int {
        return filteredRes?.size ?: ZERO
    }

    class ViewHolder(val binding: ItemLayoutFoodListBinding) :
        RecyclerView.ViewHolder(binding.root) {}


    @SuppressLint("NotifyDataSetChanged")
    fun filterListResponse(filteredData: List<Recipe?>?) {
        this.filteredRes = filteredData
        notifyDataSetChanged()
    }
}