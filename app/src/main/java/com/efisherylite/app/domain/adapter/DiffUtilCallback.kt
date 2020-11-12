package com.efisherylite.app.domain.adapter

import androidx.recyclerview.widget.DiffUtil

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
internal class DiffUtilCallback<ITEM>(
    private val oldItems: List<ITEM>,
    private val newItems: List<ITEM>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldItems.size
    override fun getNewListSize(): Int = newItems.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition]
    }
}