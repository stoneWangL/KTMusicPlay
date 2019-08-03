package com.stone.ktmusicplay.net

/**
 * @Author: stoneWang
 * @CreateDate: 2019/8/3 10:09
 * @Description: 请求的回调
 */
interface ResponseHandler<RESPONSE> {
    /**
     * 成功的回调
     */
    fun onError(type:Int, msg: String?)

    /**
     * 失败的回调
     * 泛型：RESPONSE
     */
    fun onSuccess(type: Int, result: RESPONSE)
}