package io.rotlabs.famcardcontainer.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T : Any>(parent: ViewGroup, @LayoutRes layoutId: Int) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutId, parent, false)) {


    init {
        onCreate()
    }

    private fun onCreate() {
        setupView(itemView)
    }

    abstract fun setupView(view: View)

    abstract fun bind(data: T)
}