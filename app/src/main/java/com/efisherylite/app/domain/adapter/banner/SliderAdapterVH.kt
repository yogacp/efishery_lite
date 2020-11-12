package com.efisherylite.app.domain.adapter.banner

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.efisherylite.app.R
import com.smarteist.autoimageslider.SliderViewAdapter

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
class SliderAdapterVH(itemView: View) : SliderViewAdapter.ViewHolder(itemView) {
    var imageViewBackground: ImageView = itemView.findViewById(R.id.iv_auto_image_slider)
    var imageTitle: TextView = itemView.findViewById(R.id.tvBannerTitle)
    var imageDesc: TextView = itemView.findViewById(R.id.tvBannerDesc)
}