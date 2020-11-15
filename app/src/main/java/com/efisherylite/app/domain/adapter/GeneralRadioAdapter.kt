package com.efisherylite.app.domain.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.efisherylite.app.R
import com.efisherylite.app.external.extensions.inflate

/**
 * Created by Yoga C. Pranata on 15/11/2020.
 * Android Engineer
 */
class GeneralRadioAdapter<ITEM>(
    val context: Context,
    val items: List<ITEM>
) : RecyclerView.Adapter<GeneralRadioAdapter<ITEM>.ViewHolder>() {

    private var mSelectedItem = -1
    private var itemClick: View.(ITEM) -> Unit = {}

    constructor(
        context: Context,
        items: List<ITEM>,
        itemViewClick: View.(ITEM) -> Unit = {}
    ) : this(context, items) {
        this.itemClick = itemViewClick
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.radioButton.isChecked = (position == mSelectedItem)
        viewHolder.radioButton.text = items[position].toString()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view: View = viewGroup inflate R.layout.item_radio_button
        val viewHolder = ViewHolder(view)
        val itemView = viewHolder.itemView
        itemView.tag = viewHolder
        itemView.setOnClickListener {
            val adapterPosition = viewHolder.adapterPosition
            if (adapterPosition != RecyclerView.NO_POSITION) {
                mSelectedItem = adapterPosition
                itemView.itemClick(items[adapterPosition])
                notifyItemRangeChanged(0, items.size)
            }
        }

        return viewHolder
    }

    inner class ViewHolder(inflate: View) : RecyclerView.ViewHolder(inflate) {
        val radioButton: RadioButton = inflate.findViewById(R.id.radioButton)

        init {
            val listener: View.OnClickListener = View.OnClickListener {
                mSelectedItem = adapterPosition
                notifyItemRangeChanged(0, items.size)
            }

            itemView.setOnClickListener(listener)
            radioButton.setOnClickListener(listener)
        }
    }
}