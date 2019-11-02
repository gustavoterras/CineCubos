package br.com.cubos.cinemacubos.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import br.com.cubos.cinemacubos.base.BaseViewHolder

class RecyclerBindingAdapter<T>(
    private var itemLayout: Int,
    private var itemVarId: Int,
    private var items: ArrayList<T>
) : RecyclerView.Adapter<RecyclerBindingAdapter.BindingHolder>() {

    private var headerLayout: Int = 0
    private var footerLayout: Int = 0
    private var headerVarId: Int = 0
    private var footerVarId: Int = 0
    private var isHeaderAdded: Boolean = false
    private var isFooterAdded: Boolean = false

    private var headerViewHolder: BaseViewHolder? = null
    private var footerViewHolder: BaseViewHolder? = null

    private var onItemClickListener: OnItemClickListener<T>? = null

    interface OnItemClickListener<T> {
        fun onItemClick(position: Int, view: View, item: T)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener<T>) {
        this.onItemClickListener = onItemClickListener
    }

    fun setList(items: ArrayList<T>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun getItems(): ArrayList<T> {
        return this.items
    }

    fun addItem(item: T) {
        this.items.add(item)
        notifyItemInserted(itemCount - 1)
    }

    fun addAll(items: ArrayList<T>) {
        for (item in items) {
            addItem(item)
        }
    }

    fun clear() {
        val starRemovePosition = if (isHeaderAdded) 1 else 0
        while (itemCount > starRemovePosition) {
            removeItem(itemCount - 1)
        }
    }

    fun removeItem(position: Int) {
        this.items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun removeAll() {
        items.clear()
        notifyDataSetChanged()
    }

    fun addHeader(headerLayout: Int, headerVarId: Int, headerViewHolder: BaseViewHolder) {
        this.headerViewHolder = headerViewHolder
        this.headerLayout = headerLayout
        this.headerVarId = headerVarId
        this.isHeaderAdded = true

        addItem(Any() as T)
    }

    fun addFooter(footerLayout: Int, footerVarId: Int, footerViewHolder: BaseViewHolder) {
        this.footerViewHolder = footerViewHolder
        this.footerLayout = footerLayout
        this.footerVarId = footerVarId
        this.isFooterAdded = true

        addItem(Any() as T)
    }

    fun removeHeader() {
        this.isHeaderAdded = false

        if (items.isNotEmpty()) {
            removeItem(0)
        }
    }

    fun removeFooter() {
        this.isFooterAdded = false

        val position = itemCount - 1

        if (isHeaderAdded && position == 0) return

        if (position >= 0) {
            val item = items[position]

            if (item != null)
                removeItem(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return BindingHolder(view)
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {

        if (position == 0 && isHeaderAdded) {
            holder.binding!!.setVariable(headerVarId, headerViewHolder)
            holder.binding.executePendingBindings()
        } else if (position == itemCount - 1 && isFooterAdded) {
            holder.binding!!.setVariable(footerVarId, footerViewHolder)
            holder.binding.executePendingBindings()
        } else {

            val item = items[position]

            holder.binding!!.root.setOnClickListener { view ->
                onItemClickListener?.onItemClick(holder.adapterPosition, view, item)
            }

            holder.binding.setVariable(itemVarId, item)
            holder.binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (isHeaderAdded && isFooterAdded)
            if (position == 0) headerLayout else if (position == itemCount - 1) footerLayout else itemLayout
        else if (isHeaderAdded)
            if (position == 0) headerLayout else itemLayout
        else if (isFooterAdded)
            if (position == itemCount - 1) footerLayout else itemLayout
        else
            itemLayout
    }

    class BindingHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ViewDataBinding? = DataBindingUtil.bind(view)
    }
}