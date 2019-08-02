package com.stone.ktmusicplay.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stone.ktmusicplay.model.HomeItemBean
import com.stone.ktmusicplay.widget.HomeItemView
import com.stone.ktmusicplay.widget.LoadMoreView

/**
 * @Author: stoneWang
 * @CreateDate: 2019/8/2 9:52
 * @Description:
 */
class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeHolder>() {
    private var list = ArrayList<HomeItemBean>()

    /**
     * 更新数据
     */
    fun updateList(list: List<HomeItemBean>?) {
//        if (list == null) return
        list?.let {
            this.list.clear()
            this.list.addAll(list)
            notifyDataSetChanged()
        }

    }

    /**
     * 加载更多
     * 只需要在集合里面进行添加就可以了
     */
    fun loadMore(list : List<HomeItemBean>?) {
        list?.let {
            this.list.addAll(list)
            notifyDataSetChanged()
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        if (viewType == 1) {
            //最后一条
            return HomeHolder(LoadMoreView(parent.context))
        } else {
            //普通条目
            return HomeHolder(HomeItemView(parent.context))
        }

    }

    override fun getItemCount(): Int {
        return list.size + 1 //加的1是最后下拉加载的那个item进度条
    }

    //如果是最后一条的话,就饿没有数据没有条目，就不需要进行刷新
    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        //如果是最后一条 不需要进行刷新view
        if (position == list.size) return

        //条目数据
        val data = list.get(position)
        //条目view
        val itemView = holder.itemView as HomeItemView //转为HomeItemView类型
        //条目刷新
        itemView.setData(data)
    }

    class HomeHolder(itemView : View): RecyclerView.ViewHolder(itemView) {

    }

    //根据位置返回一个type值
    override fun getItemViewType(position: Int): Int {
        if (position == list.size) {
            //最后一条
            return 1
        } else {
            //普通条目
            return 0
        }
    }
}