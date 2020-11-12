package com.efisherylite.app.presentation.homepage.view

import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import com.efisherylite.app.databinding.ActivityHomepageBinding
import com.efisherylite.app.domain.base.activity.BaseActivity

class HomepageActivity : BaseActivity() {

    private val binding: ActivityHomepageBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}