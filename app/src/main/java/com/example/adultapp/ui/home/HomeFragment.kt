package com.example.adultapp.ui.home


import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import androidx.lifecycle.Observer
import com.example.adultapp.R
import com.example.adultapp.base.BaseBrowseFragment
import com.example.adultapp.base.BaseFragment
import com.example.adultapp.data.model.videos.DataVideos
import com.example.adultapp.global.helper.Navigation
import com.example.adultapp.global.helper.Resources
import com.example.adultapp.global.utils.*
import com.example.adultapp.ui.splash.SplashFragmentDirections

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseBrowseFragment() {

    private val viewModel : HomeViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState===null){

            prepareEntranceTransition()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerBaseObserver(viewModel)
        observeData()

        //navigate to details on video click
        setOnItemViewClickedListener{ _,item,_,_->
            /**
             * @param1 Presenter.ViewHolder
             * @param2 Object item
             * @param3 RowPresenter.ViewHolder rowViewHolder
             * @param4 T row
             *
             */
            item as DataVideos
            viewModel.navigateDetails(item.id)
        }

    }

    private fun observeData() {
        viewModel.allVideoResult.observe(viewLifecycleOwner, Observer { resource ->
            when (resource) {

                is Resources.Idle -> {
                    DebugLog.d(TAG, "Idle....")
                }
                is Resources.Loading -> {
                    DebugLog.d(TAG, "Loading....")
                }
                is Resources.Success -> {

                    displayData(resource.data)
                    startEntranceTransition()
                    DebugLog.d(TAG, "Success !!!")
                    DebugLog.d(TAG, "Item 1 ---> ${resource.data[0]}")
                }
                is Resources.Error -> {
                    DebugLog.d(TAG, "Error : ${resource.error} ")
                }
            }

        })
    }

    private fun displayData(listVideos: List<DataVideos>) {
        val adapter = ArrayObjectAdapter(ListRowPresenter())
        val headerRecommended = HeaderItem(RECOMMENDED_ID, requireActivity().getString(R.string.browse_header_recommended))
        val headerBest = HeaderItem(BEST_ID, requireActivity().getString(R.string.browse_header_best))
        val headerAll = HeaderItem(ALL_ID, requireActivity().getString(R.string.browse_header_all))
       /* for (data in listVideos) {
            val rowAdapter = ArrayObjectAdapter(PosterPresenter())
            rowAdapter.add(data)

            adapter.add(ListRow(headerItem, rowAdapter))
        }*/

        val rowAdapter = ArrayObjectAdapter(PosterPresenter())
        rowAdapter.addAll(0,listVideos)
        rowAdapter.addAll(1,listVideos)
        rowAdapter.addAll(2,listVideos)


        adapter.add(ListRow(headerRecommended, rowAdapter))
        adapter.add(ListRow(headerBest, rowAdapter))
        adapter.add(ListRow(headerAll, rowAdapter))

        this.adapter = adapter

    }

    override fun navigate(navigationTo: Navigation) {
        if (navigationTo  is Navigation.NavigationDetails) {

           val actionHomeToDetails = HomeFragmentDirections.actionHomeToDetails(navigationTo.videoId)
           findNavController()?.navigate(actionHomeToDetails)

        }

    }


}