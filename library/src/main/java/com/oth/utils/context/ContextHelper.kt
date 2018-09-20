package com.oth.utils.context

object ContextHelper {

    fun context() = ContextInjector.get().appContext()

}