package com.oth.utils.context

import android.content.Context
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ContextModule::class])
@Singleton
interface ContextComponent {
    fun appContext(): Context
}