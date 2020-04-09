package com.android.homecreditindonesia.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.android.homecreditindonesia.R
import com.android.homecreditindonesia.entity.ContentItemArticle
import com.android.homecreditindonesia.helper.loadFromUrlWithPlaceholder
import kotlinx.android.synthetic.main.layout_item_article.view.*

class ArticleViewHolder(
    view: View,
    private val listener: SetOnClickArticle
) : RecyclerView.ViewHolder(view) {

    fun bind(items: ContentItemArticle) {
        with(itemView) {
            ivArticle.loadFromUrlWithPlaceholder(
                items.articleImage,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background
            )
            tvTitleArticle.text = items.articleTitle

            cvArticle.setOnClickListener { listener.onClickArticle(items) }
        }
    }

    interface SetOnClickArticle {

        fun onClickArticle(items: ContentItemArticle)
    }

}