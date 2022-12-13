package com.example.adultapp.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.example.adultapp.R
import com.example.adultapp.base.BaseFragment
import com.example.adultapp.databinding.FragmentSplashBinding
import com.example.adultapp.global.helper.Navigation
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * Use the [SplashFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class SplashFragment : BaseFragment() {

    lateinit var binding : FragmentSplashBinding

    private val viewModel : SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSplashBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerBaseObserver(viewModel)
    }

    override fun navigate(navigationTo: Navigation) {


        if (navigationTo  is Navigation.NavigationHome) {
            val actionSplashToHomeFragment =
                SplashFragmentDirections.actionSplashToHome()

            findNavController()?.navigate(actionSplashToHomeFragment)
        }

    }

}