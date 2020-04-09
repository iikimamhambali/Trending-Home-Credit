package com.android.homecreditindonesia.helper

import android.content.Context
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources

fun Context.getDrawableCompat(@DrawableRes drawableId: Int) =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        this.resources.getDrawable(drawableId, null)
    } else AppCompatResources.getDrawable(this, drawableId)
