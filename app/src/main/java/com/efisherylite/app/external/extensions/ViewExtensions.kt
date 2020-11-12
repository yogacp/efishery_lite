package com.efisherylite.app.external.extensions

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.efisherylite.app.domain.adapter.GeneralAdapter

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
fun View.setVisibleIf(condition: Boolean) {
    visibility = if (condition) View.VISIBLE else View.GONE
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