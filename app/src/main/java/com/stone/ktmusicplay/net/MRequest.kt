package com.stone.ktmusicplay.net

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stone.ktmusicplay.model.HomeItemBean
import java.lang.reflect.ParameterizedType

/**
 * @Author: stoneWang
 * @CreateDate: 2019/8/3 10:07
 * @Description: 所有请求的基类
 * MRequest也不知道具体的泛型，所以也声明一下泛型RESPONSE
 */
open class MRequest<RESPONSE>(val type: Int, val url:String, val handler: ResponseHandler<RESPONSE>) {

    /**
     * 解析网络请求的一个结果
     */
    fun parseResult(result: String?): RESPONSE {
        val gson = Gson()
        //获取泛型类型
        val type = (this.javaClass
            .genericSuperclass as ParameterizedType).actualTypeArguments[0]
        val list = gson.fromJson<RESPONSE>(result, type)
        return list
    }

    /**
     * 发送网络请求
     */
    fun excute() {
        NetManager.manager.sendRequest(this)
    }
}