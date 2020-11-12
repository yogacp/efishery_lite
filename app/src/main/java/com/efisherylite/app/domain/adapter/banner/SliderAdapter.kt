package com.efisherylite.app.domain.adapter.banner

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.efisherylite.app.R
import com.efisherylite.app.data.model.banner.SliderItem
import com.efisherylite.app.external.extensions.toast
import com.smarteist.autoimageslider.SliderViewAdapter

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
class SliderAdapter(private val context: Context) : SliderViewAdapter<SliderAdapterVH>() {
    private var mSliderItems: MutableList<SliderItem> = ArrayList()

    fun renewItems(sliderItems: MutableList<SliderItem>) {
        mSliderItems = sliderItems
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        mSliderItems.removeAt(position)
        notifyDataSetChanged()
    }

    fun addItem(sliderItem: SliderItem) {
        mSliderItems.add(sliderItem)
        notifyDataSetChanged()
    }

    override fun getCount(): Int = mSliderItems.size

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH? {
        val inflate: View =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_image_slider, null)
        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {
        val sliderItem = mSliderItems[position]

        Glide.with(viewHolder.itemView)
            .load(sliderItem.imageUrl)
            .fitCenter()
            .into(viewHolder.imageViewBackground)

        viewHolder.imageTitle.text = sliderItem.imageTitle
        viewHolder.imageDesc.text = sliderItem.imageDesc
        viewHolder.imageViewBackground.setOnClickListener {
            context.toast("${sliderItem.imageTitle} Clicked.")
        }
    }
}