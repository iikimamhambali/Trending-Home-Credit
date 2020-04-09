package com.android.homecreditindonesia.entity

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class ContentDeserializer : JsonDeserializer<ContentData> {

    companion object {
        const val ARTICLES = "articles"
        const val PRODUCTS = "products"
    }

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): ContentData? {

        val jsonObject = json?.asJsonObject
        val section = jsonObject?.get("section")

        section?.let {

            when (it.asString) {

                ARTICLES ->
                    return context?.deserialize<ContentArticle>(
                        jsonObject, ContentArticle::class.java
                    )

                PRODUCTS ->
                    return context?.deserialize<ContentProduct>(
                        jsonObject, ContentProduct::class.java
                    )

                else ->
                    return context?.deserialize<ContentData>(
                        jsonObject, ContentData::class.java
                    )
            }
        }
        return null
    }
}