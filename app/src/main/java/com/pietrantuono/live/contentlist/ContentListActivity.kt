package com.pietrantuono.live.contentlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pietrantuono.live.R
import com.pietrantuono.live.application.LiveApp
import com.pietrantuono.live.contentlist.model.RetrofitContentListModel
import com.pietrantuono.live.contentlist.network.RetrofitContentListClient
import com.pietrantuono.live.contentlist.pokos.ContentList
import com.pietrantuono.live.contentlist.viewmodel.ContentListViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ContentListActivity : AppCompatActivity() {
    @Inject
    lateinit var contentListViewModel: ContentListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GlobalScope.launch {
            val flow: Flow<ContentList> = RetrofitContentListModel(RetrofitContentListClient()).contentList
            flow.collect {

            }
        }
        (application as LiveApp).appComponent.contentListSubComponentFactory.create(this).inject(this)
        ff()
    }

    private fun ff() {

    }
}
