package com.stone.ktmusicplay.presenter.impl

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stone.ktmusicplay.model.HomeItemBean
import com.stone.ktmusicplay.presenter.interf.HomePresenter
import com.stone.ktmusicplay.util.ThreadUtil
import com.stone.ktmusicplay.util.URLProviderUtils

import com.stone.ktmusicplay.view.HomeView
import okhttp3.*
import java.io.IOException

/**
 * @Author: stoneWang
 * @CreateDate: 2019/8/2 22:43
 * @Description:
 */
class HomePresenterImpl(var homeView:HomeView) : HomePresenter{


    val TAG = "HomePresenterImpl"

    /**
     * 初始化数据或者刷新数据
     */
    override fun loadDatas() {
        val path = URLProviderUtils.getHomeUrl(0,20)
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(path)
            .get() //请求方式
            .build()


        client.newCall(request).enqueue(object : Callback {
                /**
                 * 子线程调用
                 */
                override fun onFailure(call: Call, e: IOException) {
                    ThreadUtil.runOnMainThread(object : Runnable{
                        override fun run() {
                            //隐藏刷新控件
//                            refreshLayout.isRefreshing = false
                            //回调到view层处理
                            homeView.onError(e.message)
                        }
                    })
//                    myToast("获取数据失败")

                }

                /**
                 * 子线程调用
                 */
                override fun onResponse(call: Call, response: Response) {
//                    myToast("获取数据成功")
                    val result = response.body?.string()
                    val gson = Gson()
                    val list = gson.fromJson<List<HomeItemBean>>(result,object : TypeToken<List<HomeItemBean>>(){}.type)

                    //刷新列表
                    ThreadUtil.runOnMainThread(object : Runnable{
                        override fun run() {
                            //隐藏刷新控件
//                            refreshLayout.isRefreshing = false
                            //刷新列表
//                            adapter.updateList(list)
                            //将正确的结果回调给view层
                            homeView.loadSuccess(list)

                        }

                    })


                }

            })
    }

    override fun loadMore(offset: Int) {
        //        getResult()
        val path = URLProviderUtils.getHomeUrl(offset,20)
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(path)
            .get() //请求方式
            .build()

        client.newCall(request)
            .enqueue(object : Callback {
                /**
                 * 子线程调用
                 */
                override fun onFailure(call: Call, e: IOException) {
                    ThreadUtil.runOnMainThread(object : Runnable{
                        override fun run() {
                            //隐藏刷新控件
//                            refreshLayout.isRefreshing = false
                            //回调到view层处理
                            homeView.onError(e?.message)
                        }
                    })
//                    myToast("获取数据失败")

                }

                /**
                 * 子线程调用
                 */
                override fun onResponse(call: Call, response: Response) {
//                    myToast("获取数据成功")
                    val result = response.body?.string()
                    Log.i(TAG, "result=$result")
                    val gson = Gson()
                    val list = gson.fromJson<List<HomeItemBean>>(result,object : TypeToken<List<HomeItemBean>>(){}.type)
                    Log.i(TAG, "list的状态是" + list[0].id)
                    //刷新列表
                    ThreadUtil.runOnMainThread(object : Runnable{
                        override fun run() {
                            //隐藏刷新控件
//                            refreshLayout.isRefreshing = false
                            //刷新列表
//                            adapter.loadMore(list)
                            //将正确的结果回调给view层
                            homeView.loadMore(list)

                        }

                    })


                }

            })
    }
}