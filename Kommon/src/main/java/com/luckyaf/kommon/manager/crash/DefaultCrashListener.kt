package com.luckyaf.kommon.manager.crash

import android.support.v7.app.AppCompatActivity


/**
 * 类描述：
 * @author Created by luckyAF on 2018/10/17
 *
 */
class DefaultCrashListener : CrashListener {
    override fun handleCrashInUiThread(t: Throwable?, activity: AppCompatActivity) {
    }

    override fun handleCrashInAsync(t: Throwable?) {
    }
}