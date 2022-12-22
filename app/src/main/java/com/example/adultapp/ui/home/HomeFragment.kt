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
        viewModel.videoResult.observe(viewLifecycleOwner, Observer { resource ->
            when (resource) {

                is Resources.Idle -> {
                    DebugLog.d(TAG, "Idle....")
                }
                is Resources.Loading -> {
                    DebugLog.d(TAG, "Loading....")
                }
                is Resources.Success -> {

                    displayData(resource.data[0],resource.data[1],resource.data[2])
                    startEntranceTransition()


                    DebugLog.d(TAG, "Success !!!")
                    DebugLog.d(TAG, "SIZE  ---> ${resource.data.size}")
                }
                is Resources.Error -> {
                    DebugLog.d(TAG, "Error : ${resource.error} ")
                }
            }

        })
    }

    private fun displayData(
        listAll: List<DataVideos>,
        listBest: List<DataVideos>,
        listRecc: List<DataVideos>,

    ) {

        val adapter = ArrayObjectAdapter(ListRowPresenter())
        val headerRecommended = HeaderItem(RECOMMENDED_ID, requireActivity().getString(R.string.browse_header_recommended))
        val headerBest = HeaderItem(BEST_ID, requireActivity().getString(R.string.browse_header_best))
        val headerAll = HeaderItem(ALL_ID, requireActivity().getString(R.string.browse_header_all))


        val rowAdapterAll = ArrayObjectAdapter(PosterPresenter())
        val rowAdapterBest = ArrayObjectAdapter(PosterPresenter())
        val rowAdapterRecc = ArrayObjectAdapter(PosterPresenter())


        rowAdapterRecc.addAll(0,listRecc)
        rowAdapterBest.addAll(0,listBest)
        rowAdapterAll.addAll(0,listAll)


        adapter.add(ListRow(headerRecommended, rowAdapterRecc))
        adapter.add(ListRow(headerBest, rowAdapterBest))
        adapter.add(ListRow(headerAll, rowAdapterAll))

        this.adapter = adapter

    }

    override fun navigate(navigationTo: Navigation) {
        if (navigationTo  is Navigation.NavigationDetails) {

           val actionHomeToDetails = HomeFragmentDirections.actionHomeToDetails(navigationTo.videoId)
           findNavController()?.navigate(actionHomeToDetails)

        }

    }


}