package com.oth.utils.sample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.oth.utils.context.ContextHelper
import com.oth.utils.extentions.Extensions
import com.oth.utils.sample.cache.CacheActivity
import kotlinx.android.synthetic.main.activity_sample.*

class ContextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)

        // test context
        tvPackage.text = ContextHelper.context().packageName


        cache.setOnClickListener {
            Extensions.startActivity(Intent(this@ContextActivity, CacheActivity::class.java))
        }
    }
}
