package com.rv.tasktechstalwarts.feature_dashboard.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.rv.tasktechstalwarts.R
import com.rv.tasktechstalwarts.feature_dashboard.domain.model.FilterListResponse
import com.rv.tasktechstalwarts.databinding.ItemFilterListBinding

class FilterHomeListAdapter(
    private val context: Context,
    private val response: List<FilterListResponse>
) : RecyclerView.Adapter<FilterHomeListAdapter.ViewHolder>() {
    private var selectedItemPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemFilterListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val view = holder.binding
        view.filterTypeName.text = response[position].dishName.toString()

        if (response[position].isSelected) {
            view.filterTypeName.setTextColor(ContextCompat.getColor(context, R.color.orange))
            view.viewLine.visibility = View.VISIBLE
        } else {
            view.filterTypeName.setTextColor(ContextCompat.getColor(context, R.color.grey))
            view.viewLine.visibility = View.INVISIBLE
        }

        // Handle item clicks
        view.itemLayout.setOnClickListener {
            if (selectedItemPosition != holder.adapterPosition) {
                val previousSelectedPosition = selectedItemPosition
                if (previousSelectedPosition>=0){
                    response[previousSelectedPosition].isSelected = false
                }
                selectedItemPosition = holder.adapterPosition
                response[selectedItemPosition].isSelected = true
                notifyItemChanged(previousSelectedPosition)
                notifyItemChanged(selectedItemPosition)
            }
        }
    }

    override fun getItemCount(): Int {
        return response.size
    }

    class ViewHolder(val binding: ItemFilterListBinding) :
        RecyclerView.ViewHolder(binding.root) {}
}