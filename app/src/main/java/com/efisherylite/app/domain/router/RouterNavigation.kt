package com.efisherylite.app.domain.router

import android.app.ActivityOptions
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.efisherylite.app.data.constant.AppConstant
import com.efisherylite.app.data.constant.DialogConstant
import com.efisherylite.app.data.dao.storagelist.StorageListEntity

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
class RouterNavigation(private val screenRouter: ScreenRouter) {

    fun goToHomepage(context: AppCompatActivity) {
        val screen = screenRouter.getScreenIntent(context, ScreenRouter.ActivityScreen.Homepage)
        screen?.run {
            context.startActivity(
                this,
                ActivityOptions.makeSceneTransitionAnimation(context).toBundle()
            )
        }
    }


    /**
     * Dialog Navigation
     */
    fun openSortFilterDialog(context: AppCompatActivity) {
        val screen =
            screenRouter.getBottomSheetDialogFragmentScreenLayout(ScreenRouter.DialogScreen.SortFilter)
        screen?.show(context.supportFragmentManager, DialogConstant.DIALOG_SCREEN.SORT_FILTER)
    }

    fun openAddNewItemDialog(context: AppCompatActivity) {
        val screen =
            screenRouter.getDialogFragmentScreenLayout(ScreenRouter.DialogScreen.AddNewItem)
        screen?.show(context.supportFragmentManager, DialogConstant.DIALOG_SCREEN.ADD_NEW_ITEM)
    }

    fun openItemDetailDialog(context: AppCompatActivity, itemDetail: StorageListEntity) {
        val screen =
            screenRouter.getDialogFragmentScreenLayout(ScreenRouter.DialogScreen.ItemDetail)
        val arguments = Bundle()
        arguments.putParcelable(AppConstant.INTENT_KEY.ITEM_DETAIL, itemDetail)
        screen?.arguments = arguments
        screen?.show(context.supportFragmentManager, DialogConstant.DIALOG_SCREEN.ITEM_DETAIL)
    }

}