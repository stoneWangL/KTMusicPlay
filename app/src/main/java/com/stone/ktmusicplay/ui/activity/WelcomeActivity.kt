package com.stone.ktmusicplay.ui.activity

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorListener
import com.stone.ktmusicplay.R
import com.stone.ktmusicplay.base.BaseActivity
import kotlinx.android.synthetic.main.activity_welcome.*

/**
 * @Author: stoneWang
 * @CreateDate: 2019/7/29 21:51
 * @Description:
 */
class WelcomeActivity : BaseActivity() , ViewPropertyAnimatorListener {
    override fun onAnimationEnd(view: View?) {
        //进主界面
        startActivityAndFinish<LocalMainActivity>()
    }

    override fun onAnimationCancel(view: View?) {

    }

    override fun onAnimationStart(view: View?) {

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_welcome
    }

    override fun initData() {
        ViewCompat.animate(welcome_log)
            .scaleX(1.0f)
            .scaleY(1.0f)
            .setListener(this) //设置动画监听
            .setDuration(2600)
    }


}