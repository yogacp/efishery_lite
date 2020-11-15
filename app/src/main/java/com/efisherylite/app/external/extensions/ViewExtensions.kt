package com.efisherylite.app.external.extensions

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.efisherylite.app.R
import com.efisherylite.app.domain.adapter.GeneralAdapter
import com.efisherylite.app.domain.adapter.GeneralRadioAdapter

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
infix fun ViewGroup.inflate(layoutResId: Int): View =
    LayoutInflater.from(context).inflate(layoutResId, this, false)

fun View.setVisibleIf(condition: Boolean) {
    visibility = if (condition) View.VISIBLE else View.GONE
}

fun Dialog?.setBackgroundDialog() {
    val window = this?.window
    window.notNull {
        window?.setBackgroundDrawableResource(R.drawable.bg_dialog)
        window?.setGravity(Gravity.CENTER)
    }
}

fun EditText.hideSoftKeyboard(context: Context) {
    val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(this.windowToken, 0)
}

fun <T: ViewBinding, ITEM> RecyclerView.setup(
    items: List<ITEM>,
    bindingClass: Class<T>,
    bindHolder: View.(T?, ITEM) -> Unit,
    itemClick: View.(ITEM) -> Unit = {},
    manager: RecyclerView.LayoutManager = LinearLayoutManager(this.context)
): GeneralAdapter<T, ITEM> {
    val generalAdapter by lazy {
        GeneralAdapter(items, bindingClass,
            { binding: T?, item: ITEM ->
                bindHolder(binding, item)
            }, {
                itemClick(it)
            }
        )
    }

    layoutManager = manager
    adapter = generalAdapter
    (adapter as GeneralAdapter<*, *>).notifyDataSetChanged()

    return generalAdapter
}

fun <ITEM> RecyclerView.setupWithRadioButton(
    items: List<ITEM>,
    itemClick: View.(ITEM) -> Unit = {},
    manager: RecyclerView.LayoutManager = LinearLayoutManager(this.context)
): GeneralRadioAdapter<ITEM> {

    val radioAdapter by lazy {
        GeneralRadioAdapter(this.context, items) {
            itemClick(it)
        }
    }

    layoutManager = manager
    adapter = radioAdapter
    (adapter as GeneralRadioAdapter<*>).notifyDataSetChanged()

    return radioAdapter

}