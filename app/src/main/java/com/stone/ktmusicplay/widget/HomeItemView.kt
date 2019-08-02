package com.stone.ktmusicplay.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.squareup.picasso.Picasso
import com.stone.ktmusicplay.R
import com.stone.ktmusicplay.model.HomeItemBean
import kotlinx.android.synthetic.main.item_home.view.*

/**
 * @Author: stoneWang
 * @CreateDate: 2019/8/2 9:00
 * @Description:
 */
class HomeItemView : RelativeLayout {

    /**
     * 刷新条目view数据
     */
    fun setData(data: HomeItemBean) {
        //歌曲名称
        title.setText(data.title)
        //简介
        desc.setText(data.description)
        //背景图片
        Picasso.with(context).load(data.posterPic).into(bg)
    }

    //new出来需要他
    constructor(context: Context?) : super(context)
    //清单文件需要他
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    //主题相关，需要他
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    /**
     * 初始化方法，加载条目
     */
    init{
        View.inflate(context, R.layout.item_home, this)
    }
}