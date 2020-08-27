package com.example.scrollinggallery

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scrollinggallery.network.ApiUtils
import com.example.scrollinggallery.network.data.PicsumDTO
import com.example.scrollinggallery.ui.adapters.recycler.PictureCardDecoration
import com.example.scrollinggallery.ui.adapters.recycler.PictureAdapter
import kotlinx.android.synthetic.main.fragment_recycler.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class RecyclerFragment : Fragment(){

    private val testList = ArrayList<PicsumDTO>()

    private val pictureAdapter = PictureAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_recycler, container, false)

    fun newInstance() = RecyclerFragment()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecycler()
    }

    private fun getTestList(page: Int, limit: Int){
        ApiUtils.getPicsumApi()?.getPicturesByPage(page, limit)?.enqueue(
            object : Callback<List<PicsumDTO>> {
                override fun onFailure(call: Call<List<PicsumDTO>>, t: Throwable) {
                    Log.e("ERROR", "Failure")
                }

                override fun onResponse(
                    call: Call<List<PicsumDTO>>,
                    response: Response<List<PicsumDTO>>
                ) {
                    if (response.isSuccessful) {
                        Handler(context?.mainLooper).post {
                            testList.addAll(response.body()!!)
                            Toast.makeText(context, "Data received", Toast.LENGTH_SHORT).show()
                            pictureAdapter.addData(testList)
                            pictureAdapter.notifyDataSetChanged()
                        }
                        //mainProgressBar.visibility = View.GONE
                    } else {
                        Log.e("ERROR", "No response")
                    }
                }
            }
        )
    }

    private fun initRecycler(){
        getTestList(10,30)
        fragmentRecyclerList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = pictureAdapter
            addItemDecoration(PictureCardDecoration())
        }
    }
}