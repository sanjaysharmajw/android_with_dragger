package com.rv.tasktechstalwarts.feature_dashboard.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rv.tasktechstalwarts.common.ZERO
import com.rv.tasktechstalwarts.databinding.FragmentNavFavouritesBinding
import com.rv.tasktechstalwarts.feature_dashboard.domain.model.FavouriteListResponse
import com.rv.tasktechstalwarts.feature_dashboard.domain.model.GetFavouriteListUIState
import com.rv.tasktechstalwarts.feature_dashboard.presentation.adapter.FavouriteItemAdapter

class NavFavouritesFragment : Fragment() {
    private lateinit var binding: FragmentNavFavouritesBinding
    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var adapter: FavouriteItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavFavouritesBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        viewModel.getFavouriteListResponse()
        observeFavouriteListResponse()
    }

    private fun initView() {
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun observeFavouriteListResponse() {
        viewModel.getFavouriteItem.observe(viewLifecycleOwner) {
            when (it) {
                is GetFavouriteListUIState.IsLoading -> {}
                is GetFavouriteListUIState.OnSuccess -> {
                    if (it.onSuccess?.size == ZERO) binding.ivNoDataFound.visibility =
                        View.VISIBLE else binding.ivNoDataFound.visibility = View.GONE
                    it?.onSuccess?.let { res ->
                        setAdapter(res)
                    }
                }

                is GetFavouriteListUIState.OnFailure -> {}
            }
        }
    }

    private fun setAdapter(res: List<FavouriteListResponse>) {
        adapter = FavouriteItemAdapter(requireContext(), res)
        binding.rvFavourite.adapter = adapter
        binding.rvFavourite.setHasFixedSize(true)

        binding.etSearchBarFavourite.doAfterTextChanged { et ->
            val filteredData = res?.filter { item ->
                item?.name?.contains(et.toString(), ignoreCase = true) ?: false
            }
            adapter.filterListResponse(filteredData)
            if (filteredData?.size == ZERO) binding.ivNoDataFound.visibility =
                View.VISIBLE else binding.ivNoDataFound.visibility = View.GONE
        }
    }
}