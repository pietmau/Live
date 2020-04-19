package com.pietrantuono.live.contentlist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG
import com.google.android.material.snackbar.Snackbar
import com.pietrantuono.live.R
import com.pietrantuono.live.application.LiveApp
import com.pietrantuono.live.contentlist.pokos.ContentListItem
import com.pietrantuono.live.contentlist.viewmodel.ContentListIntent.OpenDetail
import com.pietrantuono.live.contentlist.viewmodel.ContentViewModel
import com.pietrantuono.live.contentlist.viewmodel.ContentListViewState
import com.pietrantuono.live.databinding.ActivityMainBinding
import javax.inject.Inject
import  com.pietrantuono.live.contentlist.viewmodel.ContentListViewState.Loading
import  com.pietrantuono.live.contentlist.viewmodel.ContentListViewState.Error
import  com.pietrantuono.live.contentlist.viewmodel.ContentListViewState.Content
import com.pietrantuono.live.contentlist.viewmodel.ContentListTransientEvent
import com.pietrantuono.live.detail.DetailFragment

class ContentListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var contentViewModel: ContentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        performDependencyInjection()
        contentViewModel.viewStates.observe(this, Observer {
            render(it)
        })
        binding.list.callback = {
            contentViewModel.acceptIntent(OpenDetail(it))
        }
        contentViewModel.transientEvents.observe(this, Observer { onTransientEventReceived(it) })
    }

    private fun onTransientEventReceived(transientEvent: ContentListTransientEvent) =
        when (transientEvent) {
            is ContentListTransientEvent.OpenDetail -> DetailFragment.newInstance(transientEvent.detailItem).show(supportFragmentManager, DetailFragment.TAG)
        }

    private fun render(viewState: ContentListViewState) =
        when (viewState) {
            Loading -> onLoading()
            is Error -> onError(viewState.message)
            is Content -> onContent(viewState.constntListContentListItems)
        }

    private fun onContent(items: List<ContentListItem>) {
        binding.progress.visibility = GONE
        binding.list.submitList(items)
    }

    private fun onError(message: String?) {
        binding.progress.visibility = GONE
        Snackbar.make(binding.root, message ?: getString(R.string.error), LENGTH_LONG).show()
    }

    private fun onLoading() {
        binding.progress.visibility = VISIBLE
    }

    private fun performDependencyInjection() =
        (application as LiveApp).appComponent.contentListSubComponentFactory.create(this).inject(this)
}
