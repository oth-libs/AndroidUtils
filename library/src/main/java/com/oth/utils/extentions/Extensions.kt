package com.oth.utils.extentions

import android.content.Intent
import com.oth.utils.context.ContextHelper

object Extensions {

    fun startActivity(intent: Intent): Boolean {
        return try {
            ContextHelper.context().startActivity(intent)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

}