package com.efisherylite.app.domain.adapter

import android.view.View
import androidx.viewbinding.ViewBinding

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
class GeneralAdapter<T : ViewBinding, ITEM>(
    items: List<ITEM>,
    bindingClass: Class<T>,
    private val bindHolder: View.(T?, ITEM) -> Unit
) : AbstractAdapter<T, ITEM>(items, bindingClass) {

    private var itemClick: View.(ITEM) -> Unit = {}
    var viewBinding: T? = null

    constructor(
        items: List<ITEM>,
        bindingClass: Class<T>,
        bindHolder: View.(T?, ITEM) -> Unit,
        itemViewClick: View.(ITEM) -> Unit = {}
    ) : this(items, bindingClass, bindHolder) {
        this.itemClick = itemViewClick
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        super.onBindViewHolder(holder, position)
        if (position == holder.adapterPosition) {
            this.viewBinding = binding
            holder.itemView.bindHolder(binding, itemList[position])
        }
    }

    override fun onItemClick(itemView: View, position: Int) {
        itemView.itemClick(itemList[position])
    }
}