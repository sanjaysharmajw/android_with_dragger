package com.rv.tasktechstalwarts.feature_dashboard.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rv.tasktechstalwarts.R
import com.rv.tasktechstalwarts.common.ZERO
import com.rv.tasktechstalwarts.feature_dashboard.domain.model.GetRecipesUIState
import com.rv.tasktechstalwarts.feature_dashboard.domain.model.Recipe
import com.rv.tasktechstalwarts.feature_dashboard.presentation.adapter.FilterHomeListAdapter
import com.rv.tasktechstalwarts.feature_dashboard.presentation.adapter.RecipesAdapter
import com.rv.tasktechstalwarts.databinding.FragmentNavHomeBinding
import com.rv.tasktechstalwarts.utils.Util
import dagger.hilt.android.qualifiers.ApplicationContext


class NavHomeFragment : Fragment() {
    private lateinit var binding: FragmentNavHomeBinding
    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var adapter: RecipesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       context?.applicationContext?.let { Util.deleteDatabase(it) }
        initView()
        observeRecipesResponse()
        setFilterListAdapter()
    }

    private fun observeRecipesResponse() {
        viewModel.observeRecipesResponse.observe(viewLifecycleOwner) {
            when (it) {
                is GetRecipesUIState.IsLoading -> {}
                is GetRecipesUIState.OnSuccess -> {
                    setRecipesAdapter(it.onSuccess.recipes)
                    if (it.onSuccess.recipes?.size == ZERO) binding.ivNoDataFound.visibility =
                        View.VISIBLE else binding.ivNoDataFound.visibility = View.GONE
                }
                is GetRecipesUIState.OnFailure -> {}
            }
        }
    }

    private fun setRecipesAdapter(recipes: List<Recipe?>?) {
        adapter = RecipesAdapter(requireContext(), recipes) {itemRes ->
            viewModel.hideBottomNav.value = R.id.itemDetailsFragment
            val action = NavHomeFragmentDirections.actionNavHomeFragmentToItemDetailsFragment(itemRes)
            findNavController().navigate(action)
        }
        binding.rvRecipes.adapter = adapter
        binding.rvRecipes.setHasFixedSize(true)

        binding.etSearchBar.doAfterTextChanged { et ->
            val filteredData = recipes?.filter { item ->
                item?.name?.contains(et.toString(), ignoreCase = true) ?: false
            }
            adapter.filterListResponse(filteredData)
            if (filteredData?.size == ZERO) binding.ivNoDataFound.visibility =
                View.VISIBLE else binding.ivNoDataFound.visibility = View.GONE
        }
    }

    private fun initView() {
        binding.ivCart.setOnClickListener {
            viewModel.hideBottomNav.value = R.id.addToCartFragment
            findNavController().navigate(R.id.addToCartFragment)
        }
    }

    private fun setFilterListAdapter() {
        val adapter = FilterHomeListAdapter(requireContext(), viewModel.getFilterList())
        binding.rvFilterItem.adapter = adapter
        binding.rvFilterItem.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()
        viewModel.hideBottomNav.value = R.id.navHomeFragment
    }
}

