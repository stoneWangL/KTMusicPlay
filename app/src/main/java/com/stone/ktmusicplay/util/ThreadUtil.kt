package com.stone.ktmusicplay.util

import android.os.Handler
import android.os.Looper


/**
 * @Author: stoneWang
 * @CreateDate: 2019/8/2 18:44
 * @Description:
 */
object ThreadUtil {
    val handler = Handler(Looper.getMainLooper())

    /**
     * 运行在主线程中
     */
    fun runOnMainThread(runnable:Runnable) {
        handler.post(runnable)
    }
}