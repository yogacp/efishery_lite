package com.efisherylite.app.presentation.dialogfragment.itemdetail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.efisherylite.app.R
import com.efisherylite.app.data.constant.AppConstant
import com.efisherylite.app.data.dao.storagelist.StorageListEntity
import com.efisherylite.app.databinding.DialogItemDetailBinding
import com.efisherylite.app.domain.base.dialogfragment.BaseDialogFragment
import com.efisherylite.app.external.extensions.notNull
import com.efisherylite.app.external.extensions.setBackgroundDialog
import com.efisherylite.app.external.extensions.toIDR

/**
 * Created by Yoga C. Pranata on 16/11/2020.
 * Android Engineer
 */
class ItemDetailDialogFragment : BaseDialogFragment() {

    private var binding: DialogItemDetailBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, R.style.DialogStyle90)
        isCancelable = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogItemDetailBinding.inflate(LayoutInflater.from(context))
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.setBackgroundDialog()
        initArguments()
        setupButton()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun initArguments() {
        val data = arguments
        data.notNull {
            val itemDetail = it.getParcelable<StorageListEntity>(AppConstant.INTENT_KEY.ITEM_DETAIL)
            setupView(itemDetail)
        }
    }

    private fun setupButton() {
        binding?.btnClose?.setOnClickListener {
            dismissDialog()
        }
    }

    private fun setupView(itemDetail: StorageListEntity?) {
        binding?.tvItemDetailTitle?.text = itemDetail?.komoditas
        binding?.tvAreaProvinsi?.text = itemDetail?.areaProvinsi
        binding?.tvAreaKota?.text = itemDetail?.areaKota
        binding?.tvHarga?.text = itemDetail?.price.toIDR()
        binding?.tvUkuran?.text = itemDetail?.size
    }

}