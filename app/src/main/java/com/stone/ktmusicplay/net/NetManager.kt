package com.stone.ktmusicplay.net

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stone.ktmusicplay.model.HomeItemBean
import com.stone.ktmusicplay.util.ThreadUtil
import com.stone.ktmusicplay.util.URLProviderUtils
import okhttp3.*
import java.io.IOException

/**
 * @Author: stoneWang
 * @CreateDate: 2019/8/3 10:15
 * @Description: 发送网络请求的类
 */
class NetManager private constructor(){
    val client by lazy { OkHttpClient() }
    companion object{
        val manager by lazy { NetManager() }
    }

    /**
     * 发送网络请求
     */
    fun <RESPONSE>sendRequest(req: MRequest<RESPONSE>) {
//        val path = URLProviderUtils.getHomeUrl(0,20) //MRequest中就有url地址，所以不需要这么写
//        val client = OkHttpClient()
        val request = Request.Builder()
            .url(req.url)
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
//                        homeView.onError(e.message)
                        req.handler.onError(req.type, e.message)
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
                val parseResult = req.parseResult(result)
//                val gson = Gson()
//                val list = gson.fromJson<List<HomeItemBean>>(result,object : TypeToken<List<HomeItemBean>>(){}.type)

                //刷新列表
                ThreadUtil.runOnMainThread(object : Runnable{
                    override fun run() {
                        //隐藏刷新控件
//                            refreshLayout.isRefreshing = false
                        //刷新列表
//                            adapter.updateList(list)
                        //将正确的结果回调给view层
//                        homeView.loadSuccess(list)
                        req.handler.onSuccess(req.type, parseResult)

                    }

                })


            }

        })
    }
}