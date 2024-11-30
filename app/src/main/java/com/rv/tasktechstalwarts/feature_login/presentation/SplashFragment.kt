package com.rv.tasktechstalwarts.feature_login.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.rv.tasktechstalwarts.R
import com.rv.tasktechstalwarts.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(layoutInflater)
        binding.btnGetStarted.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }
        return binding.root
    }
}