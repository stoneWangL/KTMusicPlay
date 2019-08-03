package com.stone.ktmusicplay.presenter.interf

/**
 * @Author: stoneWang
 * @CreateDate: 2019/8/2 22:42
 * @Description:
 */
interface HomePresenter {
    /**
     * 伴生对象
     * 和java不同，java在接口里面可以定义常量，但是在kotlin中如果要写，则需要写在伴生对象中
     */
    companion object{
        val TYPE_INIT_OR_REFRESH = 1
        val TYPE_LOAD_MORE = 2
    }

    fun loadDatas()
    fun loadMore(offset: Int)

}