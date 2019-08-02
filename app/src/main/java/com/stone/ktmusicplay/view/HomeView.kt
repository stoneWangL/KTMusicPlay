package com.stone.ktmusicplay.view

import com.stone.ktmusicplay.model.HomeItemBean

/**
 * @Author: stoneWang
 * @CreateDate: 2019/8/2 22:39
 * @Description: home界面和presenter交互
 */
interface HomeView {
    /**
     * 获取数据失败
     */
    fun onError(message: String?)

    /**
     * 初始化数据或者刷新数据成功
     */
    fun loadSuccess(list: List<HomeItemBean>?)

    /**
     * 加载更多成功
     */
    fun loadMore(list: List<HomeItemBean>?)
}