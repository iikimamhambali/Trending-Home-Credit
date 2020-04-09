package com.android.homecreditindonesia.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.android.homecreditindonesia.base.BaseRecyclerView
import com.android.homecreditindonesia.entity.ContentArticle
import kotlinx.android.synthetic.main.layout_item_main.view.*

class MainArticleViewHolder(view: View, private val listener: ArticleViewHolder.SetOnClickArticle) :
    RecyclerView.ViewHolder(view) {

    fun bind(items: ContentArticle) {
        with(itemView) {
            tvTitle.visibility = View.VISIBLE
            tvTitle.text = items.sectionTitle
            rvMain.initRecyclerView(ArticleAdapter(items.items, listener), BaseRecyclerView.LayoutManager.VERTICAL)
        }
    }
}