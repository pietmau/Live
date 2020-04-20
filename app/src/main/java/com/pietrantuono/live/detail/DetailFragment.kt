package com.pietrantuono.live.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pietrantuono.live.application.AppComponent
import com.pietrantuono.live.application.LiveApp
import com.pietrantuono.live.contentlist.network.ContentListClient
import com.pietrantuono.live.contentlist.pokos.ContentListItem
import com.pietrantuono.live.contentlist.pokos.Detail
import com.pietrantuono.live.databinding.FragmentDetailBinding
import kotlinx.android.synthetic.main.fragment_detail.body
import kotlinx.android.synthetic.main.view_item.date
import kotlinx.android.synthetic.main.view_item.subtitle
import kotlinx.android.synthetic.main.view_item.title
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailFragment : BottomSheetDialogFragment() {
    private var binding: FragmentDetailBinding? = null
    @Inject
    lateinit var detailViewModel: DetailViewModel

    private val contentListItem
        get() = arguments?.getParcelable<ContentListItem>(KEY)

    private val itemId
        get() = requireNotNull(contentListItem?.id)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentDetailBinding = FragmentDetailBinding.inflate(layoutInflater)
        this.binding = fragmentDetailBinding
        return fragmentDetailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        contentListItem?.let { item ->
            binding?.run {
                title.text = item.title
                subtitle.text = item.subtitle
                date.text = item.date
                itemText.text = "${item.id}"
            }
        }
        detailViewModel.liveData.observe(this, Observer { detail ->
            binding?.let { body.text = detail.body }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as LiveApp).appComponent.detailSubComponentFactory.create(requireActivity(), itemId).inject(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        internal const val KEY = "key"
        internal val TAG = DetailFragment::class.simpleName!!
        fun newInstance(item: ContentListItem) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY, item)
                }
            }
    }
}