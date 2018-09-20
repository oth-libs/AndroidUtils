package com.oth.utils.kotlinExtentions

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import android.text.Html
import android.text.Spanned
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.oth.utils.context.ContextHelper
import java.util.regex.Pattern

object KotlinExtentions {


    fun Any.log(message: Any?) = Log.v("class-${this.javaClass.simpleName}", "$message")
    fun log(message: Any?) = "".log(message)

    /////////////////////////////////////////////////////
    // Strings //////////////////////////////////////////
    /////////////////////////////////////////////////////
    fun String.capitalize() = when { length < 2 -> toUpperCase()
        else -> Character.toUpperCase(toCharArray()[0]) + substring(1).toLowerCase()
    }

    fun String.capitalizeWords() = when {
        length < 2 -> {
            toUpperCase()
        }
        else -> {
            val arr = this.trim().toLowerCase().split(" ")
            val sb = StringBuffer()

            for (i in arr.indices) {
                sb.append(Character.toUpperCase(arr[i].get(0)))
                        .append(arr[i].substring(1)).append(" ")
            }
            sb.toString().trim { it <= ' ' }
        }
    }

    fun String.asCsv(delimiter: String): List<String> = this.split(delimiter).map(String::trim).toList()

    fun String.toIntOrMinValue(): Int {
        return try {
            this.toInt()
        } catch (e: Exception) {
            Int.MIN_VALUE
        }
    }


    fun String.isValidEmail(): Boolean {
        val EMAIL_ADDRESS = Pattern.compile(
                "[a-zA-Z0-9\\.\\!\\#\\$\\%\\&\\'\\*\\+\\-\\/\\=\\?\\^\\_\\`\\{\\|\\}\\~]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+"
        )

        return !isNullOrEmpty() && EMAIL_ADDRESS.matcher(this).matches()
    }

    fun String.ellipsize(length: Int) = if (this.length > length) "${this.subSequence(0, length - 1)}..." else this

    fun String.toValidHttp() = if (this.contains("http://", true) || this.contains("https://", true)) this else "http://$this"

    @StringRes
    fun String.fromStringResource(): Int {
        try {
            return ContextHelper.context().let { it.resources.getIdentifier(this, "string", it.packageName) }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return 0
    }

    @DrawableRes
    fun String.fromDrawableResource(): Int {
        try {
            return ContextHelper.context().let { it.resources.getIdentifier(this, "drawable", it.packageName) }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return 0
    }

    fun String.fromHtml(): Spanned {
        var result: Spanned
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
        } else {
            result = Html.fromHtml(this)
        }
        return result
    }

    /////////////////////////////////////////////////////
    // VIEWS ////////////////////////////////////////////
    /////////////////////////////////////////////////////
    fun View.setDimension(width: Int? = null, height: Int? = null): View {

        val params = layoutParams

        params.width = width ?: params.width
        params.height = height ?: params.height
        layoutParams = params

        return this
    }

    fun View?.show(isShowing: Boolean = true) {
        this?.visibility = if (isShowing) View.VISIBLE else View.INVISIBLE
    }

    fun View?.hide(isHiding: Boolean = true) {
        this?.visibility = if (isHiding) View.INVISIBLE else View.VISIBLE
    }

    fun View?.gone(isGone: Boolean = true) {
        this?.visibility = if (isGone) View.GONE else View.VISIBLE
    }

    fun TextView.boldText() {
        setTypeface(null, Typeface.BOLD)
    }

    fun TextView.italicText() {
        setTypeface(null, Typeface.ITALIC)
    }

    fun TextView.normalText() {
        setTypeface(null, Typeface.NORMAL)
    }

    fun TextView.capitalize() {
        text = text.toString().trim().toLowerCase().capitalize()
    }

    fun View.resize(width: Float? = null, height: Float? = null) {
        width?.let { layoutParams.width = it.toInt() }
        height?.let { layoutParams.height = it.toInt() }
    }

    fun TextView.underline() {
        paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }

    fun ImageView?.setImageResourceAnim(resId: Int) {
        if (this == null) return

        val anim_out = AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_out).apply { duration = 200 }
        val anim_in = AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_in).apply { duration = 200 }


        anim_out.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                setImageResource(resId)

                startAnimation(anim_in)
            }

            override fun onAnimationStart(animation: Animation?) {
            }
        })

        startAnimation(anim_out)
    }

    fun TextView.stringText() = text.toString()

    fun EditText.showKeyboard(): EditText {
        this.requestFocus()
        val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
        return this
    }

    fun EditText.hideKeyboard(): EditText {
        val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(this.windowToken, 0)
        return this
    }

    fun TextView.clearText() {
        this.text = ""
    }

    fun View.locationInWindow(): Rect {
        val rect = Rect()
        val location = IntArray(2)

        getLocationInWindow(location)

        rect.left = location[0]
        rect.top = location[1]
        rect.right = location[0] + width
        rect.bottom = location[1] + height

        return rect
    }

    fun View.locationOnScreen(): Rect {
        val rect = Rect()
        val location = IntArray(2)

        getLocationOnScreen(location)

        rect.left = location[0]
        rect.top = location[1]
        rect.right = location[0] + width
        rect.bottom = location[1] + height

        return rect
    }

    @ColorInt
    fun View.getCurrentColor(@ColorInt default: Int = Color.TRANSPARENT): Int = (background as? ColorDrawable)?.color
            ?: default

    /////////////////////////////////////////////////////
    // OTHERS ///////////////////////////////////////////
    /////////////////////////////////////////////////////

    fun Int.dpToPx(): Int {
        return toFloat().dpToPx().toInt()
    }

    fun Float.dpToPx(): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics)
    }

    fun Int.spToPx(): Int {
        return toFloat().spToPx().toInt()
    }

    fun Float.spToPx(): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this, Resources.getSystem().displayMetrics)
    }

    fun Int.pxToDp(): Int {
        return toFloat().pxToDp().toInt()
    }

    fun Float.pxToDp(): Float {
        return this / Resources.getSystem().displayMetrics.density
    }

    fun Int.dpToSp(): Int {
        return toFloat().spToPx().toInt()
    }

    fun Float.dpToSp(): Float {
        return dpToPx() / spToPx()
    }


    fun TextView.bold() = setTypeface(typeface, Typeface.DEFAULT_BOLD.style)

    inline fun <reified T> Gson.fromJson(json: String): T = this.fromJson<T>(json, object : TypeToken<T>() {}.type)

    inline fun <reified T> String.fromJson(): T = GsonProvider.gson.fromJson(this)

    fun Any.toJson(): String = GsonProvider.gson.toJson(this)

    fun Any.toJsonPrettyPrinting(): String = GsonProvider.gsonPrettyPrinting.toJson(this)


    fun Int.asColor() = ContextHelper.context().let { ContextCompat.getColor(it, this) }

    fun Int.asDrawable() = ContextHelper.context().let { ContextCompat.getDrawable(it, this) }

    fun Int.asBoolean() = ContextHelper.context().let { it.resources.getBoolean(this) }

    fun Int.asInt() = ContextHelper.context().let { it.resources.getInteger(this) }

    fun Int.asString() = ContextHelper.context().let { it.resources.getString(this) }


    inline fun <reified T> T.addToList(list: MutableList<T>): T = this.apply {
        list.add(this)
    }

}