package com.efisherylite.app.domain.router

import android.content.Context
import android.content.Intent
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
interface ScreenRouter {

    sealed class ActivityScreen {
        object Homepage : ActivityScreen()
    }

    sealed class DialogScreen {
        object SortFilter: DialogScreen()
        object AddNewItem: DialogScreen()
        object ItemDetail: DialogScreen()
    }

    fun getScreenIntent(context: Context, screen: ActivityScreen): Intent?
    fun getDialogFragmentScreenLayout(screen: DialogScreen): DialogFragment?
    fun getBottomSheetDialogFragmentScreenLayout(screen: DialogScreen): BottomSheetDialogFragment?
}