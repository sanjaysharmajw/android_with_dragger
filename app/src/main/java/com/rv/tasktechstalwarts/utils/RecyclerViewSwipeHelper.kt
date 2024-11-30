package com.rv.tasktechstalwarts.utils

import android.animation.ObjectAnimator
import android.graphics.Canvas
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.LinearLayout
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.rv.tasktechstalwarts.R


class RecyclerViewSwipeHelper {

    fun attachSwipeHelper(
        recyclerView: RecyclerView,
        onSwiped: (viewHolder: RecyclerView.ViewHolder, direction: Int) -> Unit
    ) {
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // Handle the action when the user fully swipes the item
                onSwiped(viewHolder, direction)

            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val itemView = viewHolder.itemView
               // val swipeLayout = itemView.findViewById<LinearLayout>(R.id.swipeLayout)

                // Get the width of the item
                val itemWidth = itemView.width
                val swipeLimit = itemWidth * 10 // Set swipe limit to 30% of item width

                // Calculate restricted translationX (only allow swiping up to the limit)
                val translationX = when {
                    dX < 0 -> dX.coerceAtLeast(-swipeLimit.toFloat())
                    else -> dX.coerceAtMost(swipeLimit.toFloat())
                }

                // Set itemView's translationX with a smoother swipe behavior
                itemView.translationX = translationX

                // Handle visibility of the swipe layout
               // swipeLayout.visibility = if (translationX != 0f) View.VISIBLE else View.GONE

                super.onChildDraw(c, recyclerView, viewHolder, translationX, dY, actionState, isCurrentlyActive)

                // Handle the case where the swipe is undone (not fully swiped)
                if (!isCurrentlyActive && translationX == 0f) {
                  //  resetSwipePosition(itemView)
                }
            }

            override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
                return 10f // Only allow swiping up to 30% of the item width
            }

            override fun getSwipeEscapeVelocity(defaultValue: Float): Float {
                return defaultValue * 10// Adjust escape velocity for smoother swipe experience
            }

            override fun getSwipeVelocityThreshold(defaultValue: Float): Float {
                return defaultValue * 10// Adjust swipe velocity to control smoothness
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    // Method to quickly reset the item's position (undo swipe) with a smooth animation
    private fun resetSwipePosition(itemView: View) {
        itemView.animate()
            .translationX(0f) // Move back to original position
        // Fast animation duration (150ms)
            .withEndAction {
                itemView.findViewById<LinearLayout>(R.id.swipeLayout).visibility = View.GONE
            }
            .start()
    }
}





