package com.pietrantuono.live.contentlist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.pietrantuono.live.R
import com.pietrantuono.live.application.LiveApp
import com.pietrantuono.live.contentlist.model.RetrofitContentListModel
import com.pietrantuono.live.contentlist.network.RetrofitContentListClient
import com.pietrantuono.live.contentlist.pokos.ContentList
import com.pietrantuono.live.contentlist.viewmodel.ContentListViewModel
import com.pietrantuono.live.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ContentListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var contentListViewModel: ContentListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        performDependencyInjection()
        contentListViewModel.viewStates.observe(this, Observer {
            render(it)
        })
    }

    private fun render(viewState: ContentListViewState) {

    }

    private fun performDependencyInjection() =
        (application as LiveApp).appComponent.contentListSubComponentFactory.create(this).inject(this)

    private fun ff() {

    }
}
