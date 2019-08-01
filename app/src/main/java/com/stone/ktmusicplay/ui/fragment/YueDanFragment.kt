package com.stone.ktmusicplay.ui.fragment

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.stone.ktmusicplay.base.BaseFragment

/**
 * @Author: stoneWang
 * @CreateDate: 2019/8/1 18:23
 * @Description:
 */
class YueDanFragment : BaseFragment() {
    override fun initView(): View? {
        val tv = TextView(context)
        tv.gravity = Gravity.CENTER
        tv.setTextColor(Color.RED)
        tv.text = javaClass.simpleName
        return tv
    }
}