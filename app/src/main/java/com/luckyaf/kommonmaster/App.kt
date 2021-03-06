package com.luckyaf.kommonmaster

import android.app.Application
import com.luckyaf.kommon.Kommon
import com.luckyaf.kommon.debug.ShakeManager
import com.luckyaf.kommon.manager.crash.CrashHandler
import com.luckyaf.kommon.manager.netstate.NetStateManager

/**
 * 类描述：
 * @author Created by luckyAF on 2018/10/15
 *
 */
class App : Application(){
    override fun onCreate() {
        super.onCreate()
        Kommon.init(this)
        CrashHandler.init()
        ShakeManager.init(this)
        NetStateManager.registerNetworkStateReceiver(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        NetStateManager.unRegisterNetworkStateReceiver(this)
        ShakeManager.clear()
    }
    override fun onLowMemory() {
        super.onLowMemory()
        NetStateManager.unRegisterNetworkStateReceiver(this)
        android.os.Process.killProcess(android.os.Process.myPid())
    }
}