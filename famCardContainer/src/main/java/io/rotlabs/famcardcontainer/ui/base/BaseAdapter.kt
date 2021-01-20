package io.rotlabs.famcardcontainer.ui.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T : Any, VH : BaseViewHolder<T>>(private val dataList: ArrayList<T>) :
    RecyclerView.Adapter<VH>() {


    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size


    fun updateAllItems(list: ArrayList<T>) {
        dataList.clear()
        dataList.addAll(list)
        notifyDataSetChanged()
    }
}