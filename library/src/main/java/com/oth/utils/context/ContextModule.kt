package com.oth.utils.context

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val context: Context) {

    @Provides
    fun providesContext(): Context = context
}

