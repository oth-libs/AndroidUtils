package com.oth.utils.context

import com.oth.utils.UtilsApplication

class ContextInjector private constructor() {

    companion object {
        fun get(): ContextComponent = UtilsApplication.get().contextComponent
    }

}