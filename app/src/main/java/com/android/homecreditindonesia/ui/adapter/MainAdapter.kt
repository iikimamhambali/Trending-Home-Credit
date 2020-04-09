package com.android.homecreditindonesia.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.homecreditindonesia.R
import com.android.homecreditindonesia.entity.ContentArticle
import com.android.homecreditindonesia.entity.ContentData
import com.android.homecreditindonesia.entity.ContentProduct

class MainAdapter(
    private val listenerProduct: ProductViewHolder.SetOnClickProduct,
    private val listenerArticle: ArticleViewHolder.SetOnClickArticle,
    private val items: List<ContentData>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val ITEM_PRODUCT = 1
        const val ITEM_ARTICLE = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_PRODUCT -> MainProductViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.layout_item_main,
                    parent,
                    false
                ), listenerProduct
            )
            ITEM_ARTICLE -> MainArticleViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.layout_item_main,
                    parent,
                    false
                ), listenerArticle
            )
            else -> MainArticleViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.layout_item_main,
                    parent,
                    false
                ), listenerArticle
            )
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = items[position]
        when (holder) {
            is MainProductViewHolder -> if (data is ContentProduct) holder.bind(data)
            is MainArticleViewHolder -> if (data is ContentArticle) holder.bind(data)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is ContentProduct -> ITEM_PRODUCT
            is ContentArticle -> ITEM_ARTICLE
            else -> ITEM_ARTICLE
        }
    }
}