package com.rv.tasktechstalwarts.feature_dashboard.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationBarView
import com.rv.tasktechstalwarts.R
import com.rv.tasktechstalwarts.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {
    private lateinit var binding: FragmentDashboardBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBottomNav()
        viewModel.getRecipesResponse()
        viewModel.hideBottomNav.observe(viewLifecycleOwner) {
            when (it) {
                R.id.itemDetailsFragment -> hideBottomNav()
                R.id.addToCartFragment -> hideBottomNav()
                R.id.navFavouritesFragment -> hideBottomNav()
                R.id.navHomeFragment -> showBottomNav()
                else -> showBottomNav()
            }
        }
    }

    private fun initBottomNav() {
        binding.bottomNav.itemIconTintList = null

        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.bottom_nav_host) as NavHostFragment
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navHomeFragment,
                R.id.navFavouritesFragment,
                R.id.bottomNavProfile,
                R.id.bottomNavHistory,
            )
        )
        navController = navHostFragment.navController
        binding.bottomNav.labelVisibilityMode = NavigationBarView.LABEL_VISIBILITY_LABELED

        binding.bottomNav.setupWithNavController(navController)


        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navHomeFragment -> {
                    navController.navigate(R.id.navHomeFragment)
                    true
                }

                R.id.navFavouritesFragment -> {
                    navController.navigate(R.id.navFavouritesFragment)
                    viewModel.hideBottomNav.value = R.id.navFavouritesFragment
                    true
                }

                R.id.bottomNavProfile -> {
                    Toast.makeText(requireContext(), "InProgress", Toast.LENGTH_LONG).show()
                    false
                }

                else -> {
                    Toast.makeText(requireContext(), "InProgress", Toast.LENGTH_LONG).show()
                    false
                }
            }
        }
        binding.bottomNav.setOnItemReselectedListener { }
    }

    private fun hideBottomNav() {
        binding.bottomNav.apply {
            isFocusable = false
            isClickable = false
            binding.bottomNav.visibility = View.GONE
        }
    }

    private fun showBottomNav() {
        binding.bottomNav.apply {
            isFocusable = true
            isClickable = true
            visibility = View.VISIBLE
        }
    }
}