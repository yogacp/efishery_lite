package com.efisherylite.app.presentation.dialogfragment.newitem.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import com.efisherylite.app.R
import com.efisherylite.app.databinding.DialogNewItemBinding
import com.efisherylite.app.domain.base.dialogfragment.BaseDialogFragment
import com.efisherylite.app.domain.callback.DialogFragmentCallback
import com.efisherylite.app.external.extensions.hideSoftKeyboard
import com.efisherylite.app.external.extensions.isNetworkActive
import com.efisherylite.app.external.extensions.setBackgroundDialog
import com.efisherylite.app.external.extensions.toast
import com.efisherylite.app.presentation.dialogfragment.newitem.viewmodel.NewItemViewModel
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by Yoga C. Pranata on 15/11/2020.
 * Android Engineer
 */
class NewItemDialogFragment : BaseDialogFragment() {

    private val viewModel: NewItemViewModel by viewModel()
    private var binding: DialogNewItemBinding? = null

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
        binding = DialogNewItemBinding.inflate(LayoutInflater.from(context))
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.setBackgroundDialog()
        observeOptionArea()
        observeAllSizes()
        observeSavedData()
        setupButtons()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun observeOptionArea() {
        viewModel.getOptionAreas().observe(this, Observer { areas ->
            val cities = areas.distinctBy { it.city }.map { it.city }.sortedBy { it }
            val provinces = areas.distinctBy { it.province }.map { it.province }.sortedBy { it }

            context?.let { ctx ->
                val adapterProvinsi = ArrayAdapter(ctx, R.layout.item_list, provinces)
                val adapterKota = ArrayAdapter(ctx, R.layout.item_list, cities)
                binding?.atvProvinsi?.setAdapter(adapterProvinsi)
                binding?.atvKota?.setAdapter(adapterKota)
            }
        })
    }

    private fun observeAllSizes() {
        viewModel.getOptionSizes().observe(this, Observer { sizes ->
            context?.let { ctx ->
                val ukuran = sizes.distinctBy { it.size }.map { it.size }
                val adapter = ArrayAdapter(ctx, R.layout.item_list, ukuran)
                binding?.atvUkuran?.setAdapter(adapter)
            }
        })
    }

    private fun setupButtons() {
        binding?.btnSave?.setOnClickListener {
            saveData()
        }

        binding?.btnCancel?.setOnClickListener {
            dismissDialog()
        }
    }

    private fun observeSavedData() {
        viewModel.dataSaved.observe(this, Observer {
            context?.toast(it)
            val listener = activity as DialogFragmentCallback
            listener.onResultDialog(it)
            dismissDialog()
        })
    }

    private fun saveData() {
        val areaProvinsi = binding?.atvProvinsi?.text.toString().trim()
        val areaKota = binding?.atvKota?.text.toString().trim()
        val komoditas = binding?.etKomoditas?.text.toString().trim()
        val harga = binding?.etHarga?.text.toString().trim()
        val ukuran = binding?.atvUkuran?.text.toString().trim()

        if(context?.isNetworkActive() == true) {
            viewModel.saveData(areaProvinsi, areaKota, komoditas, harga, ukuran)
        } else {
            context?.toast("Please make sure your internet is turned ON.")
        }
    }
}