package com.example.scrollinggallery.ui.main.local

import android.view.View
import androidx.fragment.app.viewModels
import com.example.scrollinggallery.domain.Pic
import com.example.scrollinggallery.domain.Status
import com.example.scrollinggallery.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_recycler.*
import kotlinx.android.synthetic.main.view_error_empty.*

@AndroidEntryPoint
class LocalStorageFragment: BaseFragment() {

    private val localViewModel: LocalViewModel by viewModels()

    override fun savePicCallback(pic: Pic) = localViewModel.addToDB(pic)

    override fun deletePicCallback(pic: Pic) = localViewModel.removeFromDB(pic)

    override fun setupList(){
        localViewModel.pagedList.observe(viewLifecycleOwner, { pics ->
            picsumAdapter.submitList(pics)
        })
        localViewModel.listStatus.observe(viewLifecycleOwner, { status ->
            if (status != null)
                showLayer(status)
            else
                showLayer(Status.ERROR)
        })
    }

    private fun showLayer(status: Status) {
        if (status == Status.ERROR){
            fragmentRecyclerList.visibility = View.GONE
            layoutErrorEmpty.visibility = View.VISIBLE
        } else {
            fragmentRecyclerList.visibility = View.VISIBLE
            layoutErrorEmpty.visibility = View.GONE
        }
    }
}