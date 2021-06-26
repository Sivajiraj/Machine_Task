
package com.appentus.machinetask.views.activities

import androidx.activity.viewModels
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.akiniyalocts.pagingrecycler.PagingDelegate
import com.appentus.machinetask.BR
import com.appentus.machinetask.R
import com.appentus.machinetask.databinding.ActivityMainBinding
import com.appentus.machinetask.model.response.ImageResponseItem
import com.appentus.machinetask.viewModel.ImageViewModel
import com.appentus.machinetask.views.adapters.ImageAdapter

class MainActivity : BaseActivity<ActivityMainBinding>(), PagingDelegate.OnPageListener {

    private val imageViewModel :ImageViewModel by viewModels()
    private val MAX_ITEM :Int = 10
    lateinit var adapter: ImageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpContentView(R.layout.activity_main)
        setUpObservers()
        setUpData()
    }
    //Calling Api
    private fun setUpData() {
        imageViewModel.getAllImages()
    }

    //Get the response of the api
    private fun setUpObservers() {
        imageViewModel.handleSuccess.observe(this, {
            if (!it.isNullOrEmpty()){
                mainViewDatBinding.imageRV.visibility = View.VISIBLE
                mainViewDatBinding.imageRVError.visibility = View.GONE
                adapter = ImageAdapter(it as ArrayList<ImageResponseItem>, this)
                val layoutManager = GridLayoutManager(this,2)
                mainViewDatBinding.imageRV.layoutManager = layoutManager
                mainViewDatBinding.imageRV.addItemDecoration(DividerItemDecoration(this,layoutManager.orientation))
                generateData(adapter)
            } else {
                mainViewDatBinding.imageRV.visibility = View.GONE
                mainViewDatBinding.imageRVError.visibility = View.VISIBLE
            }
        })
    }

    //send the data to Adapter
    private fun generateData(adapter: ImageAdapter) {
        val detailsList :MutableList<ImageResponseItem> = ArrayList()
        for (i in 0..10) detailsList
        val pagingDelegate = PagingDelegate.Builder(adapter)
            .attachTo(mainViewDatBinding.imageRV)
            .listenWith(this)
            .build()
        mainViewDatBinding.imageRV.adapter = adapter

    }

    override fun setupViewModel() {
        mainViewDatBinding.setVariable(BR._all, imageViewModel)
        mainViewDatBinding.lifecycleOwner = this
    }

    // Pagination
    override fun onPage(item: Int) {
        mainViewDatBinding.cardviewProgressBar.visibility = View.VISIBLE
        if (item < MAX_ITEM){
            Handler(Looper.getMainLooper())
                .postDelayed({
                             val lastSize = adapter!!.pagingItemCount - 1
                    mainViewDatBinding.cardviewProgressBar.visibility = View.GONE
                    adapter.addNewItem(10)
                    mainViewDatBinding.imageRV.smoothScrollToPosition(lastSize+3)

                },1500)
        }else{
            onDonePaging()
        }

    }

    override fun onDonePaging() {
        mainViewDatBinding.cardviewProgressBar.visibility = View.GONE
        Toast.makeText(this,"Max Loading....", Toast.LENGTH_SHORT).show()
    }

}