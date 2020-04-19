package com.pietrantuono.live.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pietrantuono.live.application.LiveApp
import com.pietrantuono.live.contentlist.network.ContentListClient
import com.pietrantuono.live.contentlist.pokos.ContentListItem
import com.pietrantuono.live.databinding.FragmentDetailBinding
import kotlinx.android.synthetic.main.fragment_detail.body
import kotlinx.android.synthetic.main.view_item.date
import kotlinx.android.synthetic.main.view_item.itemId
import kotlinx.android.synthetic.main.view_item.subtitle
import kotlinx.android.synthetic.main.view_item.title
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailFragment : BottomSheetDialogFragment() {
    private var binding: FragmentDetailBinding? = null
    @Inject
    lateinit var client: ContentListClient

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentDetailBinding = FragmentDetailBinding.inflate(layoutInflater)
        this.binding = fragmentDetailBinding
        return fragmentDetailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.getParcelable<ContentListItem>(KEY)?.let { item ->
            binding?.let {
                title.text = item.title
                subtitle.text = item.subtitle
                date.text = item.date
                itemId.text = "${item.id}"
            }
            getAllDeatail(item)
        }
    }

    private fun getAllDeatail(item: ContentListItem) {
        viewLifecycleOwner.lifecycleScope.launch {
            val detail = client.getDetail(item.id)
            binding?.let { body.text = detail.detailItem?.body }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as LiveApp).appComponent.contentListSubComponentFactory.create(requireActivity()).inject(this)
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