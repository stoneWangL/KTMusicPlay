package com.stone.ktmusicplay.ui.activity

import android.preference.PreferenceManager
import androidx.appcompat.widget.Toolbar
import com.stone.ktmusicplay.R
import com.stone.ktmusicplay.base.BaseActivity
import com.stone.ktmusicplay.util.ToolBarManager
import org.jetbrains.anko.find

/**
 * @Author: stoneWang
 * @CreateDate: 2019/8/1 17:01
 * @Description: 设置界面
 */
class SettingActivity : BaseActivity() , ToolBarManager{
    override val toolbar by lazy {
        find<Toolbar>(R.id.toolbar)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_setting
    }

    override fun initData() {
        initSettingToolbar()
        //获取推送通知有没有选中
        val sp = PreferenceManager.getDefaultSharedPreferences(this)
        val push = sp.getBoolean("push", false)
        println("push = $push")

    }
}