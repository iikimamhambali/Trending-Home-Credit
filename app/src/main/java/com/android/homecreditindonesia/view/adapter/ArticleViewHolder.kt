package com.android.homecreditindonesia.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.android.homecreditindonesia.R
import com.android.homecreditindonesia.data.entity.ContentItemArticle
import com.android.homecreditindonesia.extention.loadFromUrlWithPlaceholder
import kotlinx.android.synthetic.main.layout_item_article.view.*

class ArticleViewHolder(
    view: View,
    private val listener: SetOnClickArticle
) : RecyclerView.ViewHolder(view) {

    fun bind(items: ContentItemArticle) {
        with(itemView) {
            ivArticle.loadFromUrlWithPlaceholder(
                items.articleImage,
                R.drawable.ic_placeholder_article,
                R.drawable.ic_placeholder_article
            )
            tvTitleArticle.text = items.articleTitle

            cvArticle.setOnClickListener { listener.onClickArticle(items) }
        }
    }

    interface SetOnClickArticle {

        fun onClickArticle(items: ContentItemArticle)
    }

}