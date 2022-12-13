package com.example.adultapp.ui.home


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.leanback.app.BrowseSupportFragment
import androidx.lifecycle.Observer
import com.example.adultapp.base.BaseBrowseFragment
import com.example.adultapp.base.BaseFragment
import com.example.adultapp.global.helper.Resources
import com.example.adultapp.global.utils.DebugLog
import com.example.adultapp.global.utils.TAG

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseBrowseFragment() {

    private val viewModel : HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.allVideoResult.observe(viewLifecycleOwner, Observer { resource->
          when(resource){

              is Resources.Idle ->{DebugLog.d(TAG,"Idle....")}
              is Resources.Loading ->{DebugLog.d(TAG,"Loading....")}
              is Resources.Success ->{
                  DebugLog.d(TAG,"Success !!!")
                  DebugLog.d(TAG,"Item 1 ---> ${resource.data[0]}")
              }
              is Resources.Error ->{DebugLog.d(TAG,"Error : ${resource.error} ")}
          }

        })

    }




}