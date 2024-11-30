package com.rv.tasktechstalwarts.feature_dashboard.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rv.tasktechstalwarts.common.ZERO
import com.rv.tasktechstalwarts.databinding.ItemCartListBinding
import com.rv.tasktechstalwarts.feature_dashboard.domain.model.Recipe
import com.rv.tasktechstalwarts.utils.Util

class AddToCartAdapter(
    private val context: Context,
    private val response: List<Recipe>
) : RecyclerView.Adapter<AddToCartAdapter.ViewHolder>() {
    private var count = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCartListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val view = holder.binding
       view.tvDish.text = response[position].name.toString()
        view.tvCount.text = count.toString()
        Util.setImage(context,view.ivDish,response[position].image)
        view.tvMinus.setOnClickListener {
            if (count > 0) {
                count -= 1 // Decrement the count by 1
                view.tvCount.text = count.toString() // Update the text to show the new count
            }
        }

        view.tvPlus.setOnClickListener {
            count += 1 // Increment the count by 1
            view.tvCount.text = count.toString() // Update the text to show the new count
        }
    }

    override fun getItemCount(): Int {
        return response.size ?: ZERO
    }

    class ViewHolder(val binding: ItemCartListBinding) :
        RecyclerView.ViewHolder(binding.root) {}
}