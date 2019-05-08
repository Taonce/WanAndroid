package com.taonce.wankotlin.util

import android.view.View
import androidx.viewpager.widget.ViewPager

/**
 * 实现3D翻转+缩放的效果
 */
class RotaScalePageTransformer : ViewPager.PageTransformer {

    override fun transformPage(page: View, position: Float) {
        page.apply {
            val pageWidth: Int = width
            val pageHeight: Int = height

            when {
                position < -1 -> {
                    alpha = 0f
                }
                position <= 0 -> { // [-1,0]
                    alpha = 1 + position
                    scaleX = 1 + position
                    scaleY = 1 + position
                    rotationY = 180 * position
                }
                position <= 1 -> { // (0,1]
                    alpha = 1 - position
                    scaleX = 1 - position
                    scaleY = 1 - position
                    rotationY = 180 * position
                }
                else -> {
                    alpha = 0f
                }
            }
        }
    }
}

