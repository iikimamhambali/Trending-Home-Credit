package com.android.homecreditindonesia.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.android.homecreditindonesia.R
import com.android.homecreditindonesia.entity.ContentItemProduct
import com.android.homecreditindonesia.helper.loadFromUrlWithPlaceholder
import kotlinx.android.synthetic.main.layout_item_product.view.*

class ProductViewHolder(
    view: View,
    private val listener: SetOnClickProduct
) : RecyclerView.ViewHolder(view) {

    fun bind(items: ContentItemProduct) {
        with(itemView) {
            ivProduct.loadFromUrlWithPlaceholder(
                items.productImage,
                R.drawable.ic_placeholder_product,
                R.drawable.ic_placeholder_product
            )

            val title = items.productName
            val resultProduct = title.substringAfter(delimiter = " ")
            val resultPayment = title.substringBefore(delimiter = " ")
            val textTitle = "$resultPayment \n $resultProduct"

            tvTitleProduct.text = textTitle
            sectionProduct.setOnClickListener { listener.onClickProduct(items) }
        }
    }

    interface SetOnClickProduct {

        fun onClickProduct(items: ContentItemProduct)
    }
}