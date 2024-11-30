package com.rv.tasktechstalwarts.feature_dashboard.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rv.tasktechstalwarts.feature_dashboard.domain.model.FilterListResponse
import com.rv.tasktechstalwarts.feature_dashboard.presentation.adapter.AddToCartAdapter
import com.rv.tasktechstalwarts.databinding.FragmentAddToCartBinding
import com.rv.tasktechstalwarts.feature_dashboard.domain.model.GetAddToCartUIState


class AddToCartFragment : Fragment() {
    private lateinit var binding: FragmentAddToCartBinding
    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddToCartBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        viewModel.getAddToCartResponse()
        observeCartListResponse()
        setCartListAdapter()
    }

    private fun initView() {
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
    private fun observeCartListResponse() {
        viewModel.getAddToCartList.observe(viewLifecycleOwner) {
            when (it) {
                is GetAddToCartUIState.IsLoading -> {}
                is GetAddToCartUIState.OnSuccess -> {

                    val adapter = it?.onSuccess?.let { res ->
                        AddToCartAdapter(
                            requireContext(),
                            res
                        )
                    }
                    binding.rvAddToCart.adapter = adapter
                    binding.rvAddToCart.setHasFixedSize(true)
                }

                is GetAddToCartUIState.OnFailure -> {}
            }
        }
    }

    private fun setCartListAdapter() {


        // Use RecyclerViewSwipeHelper to set up swipe actions
        /*  val swipeHelper = RecyclerViewSwipeHelper()
          swipeHelper.attachSwipeHelper(binding.rvAddToCart) { viewHolder, direction ->
              val position = viewHolder.adapterPosition
              if (direction == ItemTouchHelper.LEFT) {
                  // Handle left swipe (e.g., Delete)
                  Toast.makeText(requireContext(), "Left Swipe (Delete) at position $position", Toast.LENGTH_SHORT).show()
              } else if (direction == ItemTouchHelper.RIGHT) {
                  // Handle right swipe (e.g., Edit)
                  Toast.makeText(requireContext(), "Right Swipe (Edit) at position $position", Toast.LENGTH_SHORT).show()
              }
          }
  */
    }
}