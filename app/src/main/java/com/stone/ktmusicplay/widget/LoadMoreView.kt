package com.stone.ktmusicplay.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.stone.ktmusicplay.R

/**
 * @Author: stoneWang
 * @CreateDate: 2019/8/2 21:12
 * @Description:
 */
class LoadMoreView : RelativeLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    /**
     * 初始化方法，加载条目
     */
    init{
        View.inflate(context, R.layout.view_loadmore, this)
    }

}