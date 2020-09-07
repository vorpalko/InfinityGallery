package com.example.scrollinggallery.ui.main.remote

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.scrollinggallery.domain.Pic
import com.example.scrollinggallery.domain.Status
import com.example.scrollinggallery.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_recycler.*
import kotlinx.android.synthetic.main.view_error_network.*

@AndroidEntryPoint
class RemoteStorageFragment: BaseFragment() {

    private val picsumViewModel: RemoteViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutErrorButtonRetry.setOnClickListener { picsumViewModel.retryConnection()}
    }

    override fun setupList(){
        picsumViewModel.listStatus.observe(viewLifecycleOwner, { status ->
            if (status != null)
                showLayer(status)
            else
                showLayer(Status.ERROR)
        })
        picsumViewModel.pagedList.observe(viewLifecycleOwner, { pics ->
            picsumAdapter.submitList(pics)
        })
    }

    override fun savePicCallback(pic: Pic) = picsumViewModel.addToDB(pic)

    override fun deletePicCallback(pic: Pic) = picsumViewModel.removeFromDB(pic)

    private fun showLayer(status: Status) {
        if (status == Status.ERROR){
            fragmentRecyclerList.visibility = View.GONE
            layoutErrorNetwork.visibility = View.VISIBLE
        } else {
            fragmentRecyclerList.visibility = View.VISIBLE
            layoutErrorNetwork.visibility = View.GONE
        }
    }
}