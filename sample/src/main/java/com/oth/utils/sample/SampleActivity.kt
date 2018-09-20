package com.oth.utils.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.oth.utils.context.ContextHelper
import kotlinx.android.synthetic.main.activity_sample.*

class SampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)


        // test context
        tvPackage.text = ContextHelper.context().packageName
    }
}