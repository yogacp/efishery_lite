package com.efisherylite.app.domain.base.dialogfragment

import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.efisherylite.app.external.extensions.toast

/**
 * Created by Yoga C. Pranata on 15/11/2020.
 * Android Engineer
 */
abstract class BaseDialogFragment : DialogFragment() {

    fun dismissDialog() {
        dismissAllowingStateLoss()
    }

    fun showMessage(message: String?) {
        context?.toast(message)
    }

}