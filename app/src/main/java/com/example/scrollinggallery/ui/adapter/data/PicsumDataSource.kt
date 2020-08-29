package com.example.scrollinggallery.ui.adapter.data

import androidx.paging.PageKeyedDataSource
import com.example.scrollinggallery.network.ApiUtils
import com.example.scrollinggallery.network.data.PicsumDTO
import com.example.scrollinggallery.network.utils.FIRST_PAGE
import com.example.scrollinggallery.network.utils.PAGE_SIZE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class PicsumDataSource : PageKeyedDataSource<Int, PicsumDTO>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, PicsumDTO>
    ) {
        ApiUtils.getPicsumApi()?.getPicturesByPage(FIRST_PAGE, PAGE_SIZE)
            ?.enqueue(object : Callback<List<PicsumDTO>> {
                override fun onResponse(
                    call: Call<List<PicsumDTO>>,
                    response: Response<List<PicsumDTO>>
                ) {
                    if (response.body() != null)
                        callback.onResult(response.body()!!, null, FIRST_PAGE + 1)
                    else
                        Timber.e("No Response")
                }

                override fun onFailure(call: Call<List<PicsumDTO>>, t: Throwable) =
                    Timber.e("Failure")
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, PicsumDTO>) {
        ApiUtils.getPicsumApi()?.getPicturesByPage(params.key, PAGE_SIZE)
            ?.enqueue(object : Callback<List<PicsumDTO>> {
                override fun onResponse(
                    call: Call<List<PicsumDTO>>,
                    response: Response<List<PicsumDTO>>
                ) {
                    if (response.body() != null){
                        var key = params.key
                        if(key > 1)
                            key--
                        callback.onResult(response.body()!!, key)
                    }
                    else
                        Timber.e("No Response")
                }

                override fun onFailure(call: Call<List<PicsumDTO>>, t: Throwable) =
                    Timber.e("Failure")
            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PicsumDTO>) {
        ApiUtils.getPicsumApi()?.getPicturesByPage(params.key, PAGE_SIZE)
            ?.enqueue(object : Callback<List<PicsumDTO>> {
                override fun onResponse(
                    call: Call<List<PicsumDTO>>,
                    response: Response<List<PicsumDTO>>
                ) {
                    if (response.body() != null){
                        val key = params.key + 1
                        callback.onResult(response.body()!!, key)
                    }
                    else
                        Timber.e("No Response")
                }

                override fun onFailure(call: Call<List<PicsumDTO>>, t: Throwable) =
                    Timber.e("Failure")
            })
    }
}