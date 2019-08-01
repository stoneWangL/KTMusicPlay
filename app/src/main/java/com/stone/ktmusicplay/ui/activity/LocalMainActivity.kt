package com.stone.ktmusicplay.ui.activity

import androidx.appcompat.widget.Toolbar
import com.stone.ktmusicplay.R
import com.stone.ktmusicplay.base.BaseActivity
import com.stone.ktmusicplay.util.FragmentUtil
import com.stone.ktmusicplay.util.ToolBarManager
import kotlinx.android.synthetic.main.activity_local_main.*
import org.jetbrains.anko.find

/**
 * @Author: stoneWang
 * @CreateDate: 2019/7/30 16:59
 * @Description:
 */
class LocalMainActivity : BaseActivity() , ToolBarManager{
    /**
     * 惰性加载，用的时候才findViewById，不用的时候并不会进行初始化，且线程安全
     */
    override val toolbar by lazy {
        find<Toolbar>(R.id.toolbar)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_local_main
    }

    override fun initData() {
        initMainToolBar()
    }

    override fun initListener() {
        //设置tab切换监听
        bottomBar.setOnTabSelectListener {
//            it代表tabId
            val transaction = supportFragmentManager.beginTransaction()
            FragmentUtil.fragmentUtil.getFragment(it)?.let { it1 ->
                transaction.replace(R.id.container,
                    it1, it.toString())
            }
            transaction.commit()
        }
    }
}