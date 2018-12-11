package com.luckyaf.kommon.delegate

import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import kotlin.reflect.KProperty

/**
 * 类描述：
 * @author Created by luckyAF on 2018/10/10
 *
 */
class ExtrasDelegate<out T>(private val extraName: String, private val defaultValue: T) {
    private var extraValue: T? = null
    operator fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): T {
        extraValue = getExtra(extraValue,extraName,thisRef)
        return extraValue ?: defaultValue
    }

    operator fun getValue(thisRef: androidx.fragment.app.Fragment, property: KProperty<*>): T {
        extraValue = getExtra(extraValue,extraName,thisRef)
        return extraValue ?: defaultValue
    }
}

fun <T> extraDelegate(extraName: String,defaultValue: T) = ExtrasDelegate(extraName,defaultValue)

fun extraDelegate(extraName: String) = ExtrasDelegate(extraName,null)

@Suppress("UNCHECKED_CAST")
private fun <T> getExtra(oldExtra : T?,extraName: String,thisRef: AppCompatActivity): T? =
        oldExtra ?: thisRef.intent?.extras?.get(extraName) as T?

@Suppress("UNCHECKED_CAST")
private fun <T> getExtra(oldExtra: T?, extraName: String, thisRef: androidx.fragment.app.Fragment): T? =
        oldExtra ?: thisRef.arguments?.get(extraName) as T?

