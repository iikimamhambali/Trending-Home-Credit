package com.android.homecreditindonesia.ui.adapter

import android.annotation.SuppressLint
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.android.homecreditindonesia.R
import com.android.homecreditindonesia.base.BaseRecyclerView
import com.android.homecreditindonesia.entity.ContentProduct
import com.android.homecreditindonesia.helper.getDrawableCompat
import kotlinx.android.synthetic.main.layout_item_main.view.*
import org.jetbrains.anko.backgroundDrawable

class MainProductViewHolder(view: View, private val listener: ProductViewHolder.SetOnClickProduct) :
    RecyclerView.ViewHolder(view) {

    companion object {
        const val SPAN = 3
    }

    fun bind(items: ContentProduct) {
        with(itemView) {
            tvTitle.visibility = View.GONE
            rvMain.initRecyclerView(
                ProductAdapter(items.items, listener),
                BaseRecyclerView.LayoutManager.GRID, SPAN
            )

//            setBackground()
            sectionContent.backgroundDrawable =
                context.getDrawableCompat(R.drawable.bg_container_round)
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun setBackground() {
        with(itemView){
            val gradientDrawable = GradientDrawable()
            gradientDrawable.cornerRadius = 20F
            gradientDrawable.setColor(R.color.colorAccent)

            sectionContent.background = gradientDrawable
        }
    }
}