package com.efisherylite.app.domain.base.dialogfragment

import android.app.Dialog
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.efisherylite.app.R
import com.efisherylite.app.external.extensions.notNull
import com.efisherylite.app.external.extensions.toast
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * Created by Yoga C. Pranata on 15/11/2020.
 * Android Engineer
 */
abstract class BaseBottomSheetDialogFragment : BottomSheetDialogFragment() {

    abstract fun getHeightDialog() : Int

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener { setupHeight(it as BottomSheetDialog, getHeightDialog()) }
        return dialog
    }

    private fun setupHeight(bottomSheetDialog: BottomSheetDialog, height: Int) {
        val frameLayout = bottomSheetDialog.findViewById<FrameLayout>(R.id.design_bottom_sheet)
        val behavior = frameLayout?.let { BottomSheetBehavior.from(it) }
        val layoutParams = frameLayout?.layoutParams

        layoutParams.notNull {
            it.height = height
        }

        frameLayout?.layoutParams = layoutParams
        frameLayout?.background =
            ResourcesCompat.getDrawable(resources, R.drawable.bg_bottomsheet_rounded, null)
        behavior?.state = BottomSheetBehavior.STATE_EXPANDED
    }

    fun dismissBottomSheet() {
        dismissAllowingStateLoss()
    }

    fun showMessage(message: String?) {
        context?.toast(message)
    }
}