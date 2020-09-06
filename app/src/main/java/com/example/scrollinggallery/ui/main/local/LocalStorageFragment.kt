package com.example.scrollinggallery.ui.main.local

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.scrollinggallery.R
import com.example.scrollinggallery.domain.Pic
import com.example.scrollinggallery.ui.adapter.PicsumAdapter
import com.example.scrollinggallery.ui.adapter.list_utils.PictureCardDecorator
import com.example.scrollinggallery.ui.main.PicItemCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_recycler.*

@AndroidEntryPoint
class LocalStorageFragment: Fragment(), PicItemCallback {

    companion object{
        @JvmStatic
        fun newInstance() = LocalStorageFragment()
    }

    private val localViewModel: LocalViewModel by viewModels()

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentRecyclerList.adapter = null
    }

    override fun savePicCallback(pic: Pic) = localViewModel.addToDB(pic)

    override fun deletePicCallback(pic: Pic) {
        localViewModel.removeFromDB(pic)
    }

    private fun setupList(){
        localViewModel.pagedList.observe(viewLifecycleOwner, { pics ->
            //invalidate
            picsumAdapter.submitList(pics)
        })
    }

    private fun initRecycler(){
        fragmentRecyclerList.apply {
            addItemDecoration(PictureCardDecorator())
            picsumAdapter.setHasStableIds(true)
            adapter = picsumAdapter
        }
    }
}