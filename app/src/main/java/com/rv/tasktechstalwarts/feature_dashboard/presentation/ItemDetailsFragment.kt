package com.rv.tasktechstalwarts.feature_dashboard.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rv.tasktechstalwarts.R
import com.rv.tasktechstalwarts.databinding.FragmentItemDetailsBinding
import com.rv.tasktechstalwarts.feature_dashboard.domain.model.FavouriteListResponse
import com.rv.tasktechstalwarts.feature_dashboard.domain.model.InsertAddToCartUIState
import com.rv.tasktechstalwarts.feature_dashboard.domain.model.InsertFavouriteUIState
import com.rv.tasktechstalwarts.utils.Util
import com.rv.tasktechstalwarts.utils.Util.showToast

class ItemDetailsFragment : Fragment() {
    private lateinit var binding: FragmentItemDetailsBinding
    private val args: ItemDetailsFragmentArgs by navArgs()
    private val viewModel: HomeViewModel by activityViewModels()
    private var isFavouriteSelected = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentItemDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemModel = args.itemModel
        showFavouriteSelected(isFavouriteSelected)

        binding.apply {
            tvDishName.text = itemModel.name.toString()
            Util.setCircularImage(requireContext(), binding.image, itemModel.image)
            tvDishNum.text = itemModel.userId.toString()
        }

        binding.btnAddToCart.setOnClickListener {
            itemModel?.let {
                viewModel.insertDataAddToCart(it)
            }
            observeInsertResponse()
        }

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.icFavourite.setOnClickListener {
            isFavouriteSelected = !isFavouriteSelected
            if (isFavouriteSelected) {
                itemModel?.let {
                    viewModel.addFavouriteData(
                        FavouriteListResponse(
                            caloriesPerServing = itemModel.caloriesPerServing,
                            cookTimeMinutes = itemModel.cookTimeMinutes,
                            cuisine = itemModel.cuisine,
                            difficulty = itemModel.difficulty,
                            id = itemModel.id,
                            image = itemModel.image,
                            name = itemModel.name,
                            prepTimeMinutes = itemModel.prepTimeMinutes,
                            rating = itemModel.rating,
                            reviewCount = itemModel.reviewCount,
                            servings = itemModel.servings,
                            userId = itemModel.userId
                        )
                    )
                }
                observeAddFavouriteResponse()
            } else {

            }
            showFavouriteSelected(isFavouriteSelected)
        }
    }

    private fun observeAddFavouriteResponse() {
        viewModel.observeInsertFavourite.observe(viewLifecycleOwner) {
            when (it) {
                is InsertFavouriteUIState.IsLoading -> {}
                is InsertFavouriteUIState.OnSuccess -> {
                    requireContext()?.showToast("Favourite Item Added Successfully!")
                    viewModel.clearFavouriteData()
                }

                is InsertFavouriteUIState.OnFailure -> {
                    viewModel.clearFavouriteData()
                }

                null -> {}
            }
        }
    }

    private fun observeInsertResponse() {
        viewModel.observeInsertAddToCartData.observe(viewLifecycleOwner) {
            when (it) {
                is InsertAddToCartUIState.IsLoading -> {}
                is InsertAddToCartUIState.OnSuccess -> {
                    requireContext()?.showToast("Added Successfully!")
                    viewModel.clearInsertAddToCart()
                }

                is InsertAddToCartUIState.OnFailure -> {
                    requireContext()?.showToast(it.onFailure.toString())
                    viewModel.clearInsertAddToCart()
                }

                null -> {}
            }
        }
    }

    fun showFavouriteSelected(isFavouriteSelected: Boolean) = if (isFavouriteSelected) {
        binding.icFavourite.setImageResource(R.drawable.ic_favourite_selected_filled)
    } else {
        binding.icFavourite.setImageResource(
            R.drawable.ic_favourite_unselect
        )
    }
}