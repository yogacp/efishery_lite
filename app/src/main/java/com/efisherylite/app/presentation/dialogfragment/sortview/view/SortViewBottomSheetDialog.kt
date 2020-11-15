package com.efisherylite.app.presentation.dialogfragment.sortview.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.efisherylite.app.R
import com.efisherylite.app.databinding.BottomsheetSortViewBinding
import com.efisherylite.app.domain.base.dialogfragment.BaseBottomSheetDialogFragment
import com.efisherylite.app.domain.callback.DialogFragmentCallback
import com.efisherylite.app.external.extensions.setupWithRadioButton
import com.efisherylite.app.presentation.dialogfragment.sortview.viewmodel.SortViewModel
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by Yoga C. Pranata on 15/11/2020.
 * Android Engineer
 */
class SortViewBottomSheetDialog : BaseBottomSheetDialogFragment() {

    private val viewModel: SortViewModel by viewModel()
    var binding: BottomsheetSortViewBinding? = null

    override fun getHeightDialog(): Int = resources.getDimension(R.dimen.space_250).toInt()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomsheetSortViewBinding.inflate(LayoutInflater.from(context))
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeSelectedFilter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun observeSelectedFilter() {
        viewModel.selectedFilter.observe(this, Observer {
            (activity as DialogFragmentCallback).onResultDialog(it)
            dismissBottomSheet()
        })
    }

    private fun setupRecyclerView() {
        val layoutManager = GridLayoutManager(context, 1)
        val sortList = resources.getStringArray(R.array.sort_list).toList()
        binding?.recyclerView?.setupWithRadioButton(
            sortList,
            {
                viewModel.setSelectedSortFilter(it)
            },
            layoutManager
        )
    }

}