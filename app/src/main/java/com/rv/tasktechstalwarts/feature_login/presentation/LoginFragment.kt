package com.rv.tasktechstalwarts.feature_login.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.rv.tasktechstalwarts.R
import com.rv.tasktechstalwarts.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_nested_dash_navigation)
        }

       /* val adapter = ViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = animalsArray[position]
        }.attach()*/
    }

    private fun initView() {
        binding.tvLogin.setOnClickListener {
            binding.apply {
                viewLogin.visibility = View.VISIBLE
                viewSignUp.visibility = View.INVISIBLE
            }
        }

        binding.tvSignUp.setOnClickListener {
            binding.apply {
                viewLogin.visibility = View.INVISIBLE
                viewSignUp.visibility = View.VISIBLE
            }
        }
    }
}

//val animalsArray = arrayOf(
//    "Login",
//    "Sign-up",
//)
