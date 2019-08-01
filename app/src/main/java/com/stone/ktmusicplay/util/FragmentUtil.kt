package com.stone.ktmusicplay.util

import com.stone.ktmusicplay.R
import com.stone.ktmusicplay.base.BaseFragment
import com.stone.ktmusicplay.ui.fragment.HomeFragment
import com.stone.ktmusicplay.ui.fragment.MvFragment
import com.stone.ktmusicplay.ui.fragment.VBangFragment
import com.stone.ktmusicplay.ui.fragment.YueDanFragment

/**
 * @Author: stoneWang
 * @CreateDate: 2019/8/1 18:35
 * @Description: 管理Fragment的Util类
 */
class FragmentUtil private constructor(){ //私有化构造方法
    val homeFragment by lazy { HomeFragment() }
    val mvFragment by lazy { MvFragment() }
    val vbangFragment by lazy { VBangFragment() }
    val yuedanFragment by lazy { YueDanFragment() }


    //惰性加载，而且by lazy是线程安全的操作
    companion object {
        val fragmentUtil by lazy { FragmentUtil() }
    }

    /**
     * 根据tabId获取对应的Fragment
     */
    fun getFragment(tabId:Int):BaseFragment? {
        when(tabId) {
            R.id.tab_home->return homeFragment
            R.id.tab_mv->return mvFragment
            R.id.tab_vb->return vbangFragment
            R.id.tab_yd->return yuedanFragment
        }
        return null //没找到返回空
    }
}