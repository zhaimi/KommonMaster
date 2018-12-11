package com.luckyaf.kommon.extension

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.luckyaf.kommon.component.SmartJump

/**
 * 类描述：
 * @author Created by luckyAF on 2018/10/11
 *
 */
/**
 * The `fragment` is added to the container view with id `frameId`. The operation is
 * performed by the `fragmentManager`.
 */
fun AppCompatActivity.replaceFragmentInActivity(fragment: androidx.fragment.app.Fragment, frameId: Int) {
    supportFragmentManager.transact {
        replace(frameId, fragment)
    }
}

/**
 * The `fragment` is added to the container view with tag. The operation is
 * performed by the `fragmentManager`.
 */
fun AppCompatActivity.addFragmentToActivity(fragment: androidx.fragment.app.Fragment, tag: String) {
    supportFragmentManager.transact {
        add(fragment, tag)
    }
}

fun AppCompatActivity.setupActionBar(@IdRes toolbarId: Int, action: ActionBar.() -> Unit) {
    setSupportActionBar(findViewById(toolbarId))
    supportActionBar?.run {
        action()
    }
}
/**
 * Runs a FragmentTransaction, then calls commit().
 */
private inline fun androidx.fragment.app.FragmentManager.transact(action: androidx.fragment.app.FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commit()
}


inline val androidx.fragment.app.FragmentActivity.smartJump
    get() = SmartJump.from(this)

inline fun <reified T : Activity> Activity.jumpTo(params: Bundle? = null) {
    val intent = Intent(this, T::class.java)
    params?.let { intent.putExtras(it) }
    startActivity(intent)
}

inline fun <reified T : Activity> androidx.fragment.app.FragmentActivity.jumpForResult(
        params: Bundle? = null,
        crossinline action: (Int, Intent?) -> Unit
) {
    val intent = Intent(this, T::class.java)
    params?.let { intent.putExtras(it) }
    smartJump.startForResult(intent, object : SmartJump.Callback {
        override fun onActivityResult(resultCode: Int, data: Intent?) {
            action(resultCode, data)
        }

    })
}