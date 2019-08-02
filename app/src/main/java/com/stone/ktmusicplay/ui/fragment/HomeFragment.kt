package com.stone.ktmusicplay.ui.fragment

import android.graphics.Color
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stone.ktmusicplay.R
import com.stone.ktmusicplay.adapter.HomeAdapter
import com.stone.ktmusicplay.base.BaseFragment
import com.stone.ktmusicplay.model.HomeItemBean
import com.stone.ktmusicplay.presenter.impl.HomePresenterImpl
import com.stone.ktmusicplay.util.ThreadUtil
import com.stone.ktmusicplay.util.URLProviderUtils
import com.stone.ktmusicplay.view.HomeView
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.*
import java.io.IOException


/**
 * @Author: stoneWang
 * @CreateDate: 2019/8/1 18:23
 * @Description:
 */
class HomeFragment : BaseFragment(), HomeView {
    override fun onError(message: String?) {
        myToast("加载数据失败")
    }

    override fun loadSuccess(list: List<HomeItemBean>?) {
        //隐藏刷新控件
        refreshLayout.isRefreshing = false
        //刷新列表
        adapter.updateList(list)
    }

    override fun loadMore(list: List<HomeItemBean>?) {
        adapter.loadMore(list)
    }

    val TAG : String = "HomeFragment"

    //适配
    val adapter by lazy { HomeAdapter() }
    val presenter by lazy { HomePresenterImpl(this) }
    override fun initView(): View? {

        return View.inflate(context, R.layout.fragment_home, null)
    }

    override fun initListener() {
        //初始化recycle view -> 显示列表布局
        recycle_view.layoutManager = LinearLayoutManager(context)

        recycle_view.adapter = adapter

        //初始化刷新控件
        refreshLayout.setColorSchemeColors(Color.RED, Color.YELLOW, Color.GREEN)
        //刷新监听
        refreshLayout.setOnRefreshListener {
            //刷新的监听
            presenter.loadDatas()
        }

        //监听列表滑动
        recycle_view.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                when(newState) {
//                    RecyclerView.SCROLL_STATE_IDLE->{
//                        Log.d(TAG, "idle")
//                    }
//                    RecyclerView.SCROLL_STATE_DRAGGING->{
//                        Log.d(TAG, "dragging")
//                    }
//                    RecyclerView.SCROLL_STATE_SETTLING->{
//                        Log.d(TAG, "settling")
//                    }
//                }
                //空闲状态
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //是否最后一条已经显示
                    val layoutManager = recyclerView.layoutManager
                    if (layoutManager is LinearLayoutManager) {
                        val manager : LinearLayoutManager = layoutManager
                        val lastPosition = manager.findLastVisibleItemPosition()
                        if (lastPosition == adapter.itemCount-1) {
                            //最后一条已经显示了
                            presenter.loadMore(adapter.itemCount - 1)
                        }
                    }
                }
            }

//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
////                Log.d(TAG, "onScrolled dx=$dx dy=$dy")
////            }
        })
    }

    override fun initData() {
        //初始化数据
        presenter.loadDatas()
    }

}