package com.oth.utils

import android.app.Application
import com.oth.utils.context.ContextComponent
import com.oth.utils.context.ContextModule
import com.oth.utils.context.DaggerContextComponent

class UtilsApplication : Application() {

    lateinit var contextComponent: ContextComponent


    override fun onCreate() {
        super.onCreate()

        INSTANCE = this

        // init ContextComponent
        contextComponent = DaggerContextComponent.builder().contextModule(ContextModule(this)).build()

    }


    companion object {
        /**
         * Global instance
         */
        private var INSTANCE: UtilsApplication? = null

        @JvmStatic
        fun get(): UtilsApplication = INSTANCE!!
    }
}