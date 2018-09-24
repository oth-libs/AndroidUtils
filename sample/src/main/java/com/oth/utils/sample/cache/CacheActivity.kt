package com.oth.utils.sample.cache

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.orhanobut.hawk.Hawk
import com.oth.utils.extentions.stringText
import com.oth.utils.sample.R
import kotlinx.android.synthetic.main.activity_cache.*


class CacheActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cache)

        saveCache.setOnClickListener {
            val obj = ComplexObject(text.stringText(), text.stringText().length)

            // write into cache
            Hawk.put(CacheKeys.DATA_KEY.name, obj)
        }

        readCache.setOnClickListener {
            // read from cache
            val obj = Hawk.get<ComplexObject>(CacheKeys.DATA_KEY.name, null)

            obj?.let {
                value.text = "${it.text} - ${it.length} chars"
            }
        }
    }

    enum class CacheKeys {
        DATA_KEY
    }

}
