package com.oth.utils.kotlinExtentions


import com.google.gson.GsonBuilder

class GsonProvider @Throws(IllegalAccessException::class)

private constructor() {

    init {
        throw IllegalAccessException()
    }

    companion object {
        val gson = GsonBuilder().disableHtmlEscaping().create()
        val gsonPrettyPrinting = GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create()
    }
}
