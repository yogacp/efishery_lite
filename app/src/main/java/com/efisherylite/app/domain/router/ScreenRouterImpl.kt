package com.efisherylite.app.domain.router

import android.content.Context
import android.content.Intent
import androidx.fragment.app.DialogFragment
import com.efisherylite.app.presentation.dialogfragment.itemdetail.view.ItemDetailDialogFragment
import com.efisherylite.app.presentation.dialogfragment.newitem.view.NewItemDialogFragment
import com.efisherylite.app.presentation.dialogfragment.sortview.view.SortViewBottomSheetDialog
import com.efisherylite.app.presentation.homepage.view.HomepageActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
class ScreenRouterImpl : ScreenRouter {

    override fun getScreenIntent(context: Context, screen: ScreenRouter.ActivityScreen): Intent? {
        val klazz: Class<*>? = when(screen) {
            ScreenRouter.ActivityScreen.Homepage -> HomepageActivity::class.java
        }

        return if(klazz == null) null else Intent(context, klazz)
    }

    override fun getDialogFragmentScreenLayout(screen: ScreenRouter.DialogScreen): DialogFragment? {
        return when(screen) {
            ScreenRouter.DialogScreen.AddNewItem -> NewItemDialogFragment()
            ScreenRouter.DialogScreen.ItemDetail -> ItemDetailDialogFragment()
            else -> null
        }
    }

    override fun getBottomSheetDialogFragmentScreenLayout(screen: ScreenRouter.DialogScreen): BottomSheetDialogFragment? {
        return when(screen) {
            ScreenRouter.DialogScreen.SortFilter -> SortViewBottomSheetDialog()
            else -> null
        }
    }

}