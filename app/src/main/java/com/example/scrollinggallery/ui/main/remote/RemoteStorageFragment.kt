package com.example.scrollinggallery.ui.main.remote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.scrollinggallery.R
import com.example.scrollinggallery.domain.Pic
import com.example.scrollinggallery.domain.Status
import com.example.scrollinggallery.ui.adapter.PicsumAdapter
import com.example.scrollinggallery.ui.adapter.list_utils.PictureCardDecorator
import com.example.scrollinggallery.ui.main.PicItemCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_recycler.*
import kotlinx.android.synthetic.main.view_error_recycler.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class RemoteStorageFragment: Fragment(), PicItemCallback {

    companion object{
        @JvmStatic
        fun newInstance() = RemoteStorageFragment()
    }

    private val picsumViewModel: RemoteViewModel by viewModels()

    private val picsumAdapter = PicsumAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_recycler, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        setupList()
        layoutErrorButtonRetry.setOnClickListener {
            //setupList()
            picsumViewModel.retryConnection()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentRecyclerList.adapter = null
        layoutErrorButtonRetry.setOnClickListener(null)
    }

    override fun savePicCallback(pic: Pic) = picsumViewModel.addToDB(pic)

    override fun deletePicCallback(pic: Pic) = picsumViewModel.removeFromDB(pic)

    private fun setupList(){
        viewModelStore.clear()
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

    private fun showLayer(status: Status) {
        if (status == Status.ERROR){
            fragmentRecyclerList.visibility = View.GONE
            layoutError.visibility = View.VISIBLE
        } else {
            fragmentRecyclerList.visibility = View.VISIBLE
            layoutError.visibility = View.GONE
        }
    }

    private fun initRecycler(){
        fragmentRecyclerList.apply {
            addItemDecoration(PictureCardDecorator())
            picsumAdapter.setHasStableIds(true)
            adapter = picsumAdapter
        }
    }
}