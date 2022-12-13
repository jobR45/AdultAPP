package com.example.adultapp.base

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.adultapp.R
import com.example.adultapp.global.helper.Navigation

abstract class BaseFragment : Fragment(){


    protected fun findNavController(): NavController? {
        activity?.let {
            return androidx.navigation.Navigation.findNavController(
                it as Activity,
                R.id.nav_host_fragment
            )
        } ?: return null
    }

    /**
     * observe navigation, Snack bar, dialogs ect..
     *
     * @param viewModel BaseAndroidViewModel
     */
    protected fun registerBaseObserver(viewModel: ViewModel) {
        if (viewModel is BaseAndroidViewModel) {
            registerNavigation(viewModel)
        }
    }


    private fun registerNavigation(viewModel: BaseAndroidViewModel) {

        viewModel.navigation.observe(viewLifecycleOwner) { navigate(it) }
    }



    /**
     * handling navigation actions
     * @param navigationTo activity to navigate to
     */
    open fun navigate(navigationTo: Navigation) {

    }

}