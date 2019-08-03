package com.stone.ktmusicplay.net

import com.stone.ktmusicplay.model.HomeItemBean
import com.stone.ktmusicplay.util.URLProviderUtils

/**
 * @Author: stoneWang
 * @CreateDate: 2019/8/3 10:51
 * @Description: 首页数据请求类
 */
class HomeRequest(type: Int, offset: Int, handler: ResponseHandler<List<HomeItemBean>>):
    MRequest<List<HomeItemBean>> (type, URLProviderUtils.getHomeUrl(offset, 20), handler){
}