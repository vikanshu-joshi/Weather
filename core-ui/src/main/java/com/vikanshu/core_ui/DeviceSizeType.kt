package com.vikanshu.core_ui

import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass


/**
 * Map window size class to show UI accordingly - for portrait, landscape, tablet
 * TODO - add support for Folded devices as well
 * */
enum class DeviceSizeType {

    PORTRAIT,
    LANDSCAPE,
    TABLET;

    companion object {
        fun calculateFromWindowSizeClass(windowSizeClass: WindowSizeClass): DeviceSizeType {
            return if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact && (windowSizeClass.heightSizeClass == WindowHeightSizeClass.Medium || windowSizeClass.heightSizeClass == WindowHeightSizeClass.Expanded)) {
                PORTRAIT
            } else if ((windowSizeClass.widthSizeClass == WindowWidthSizeClass.Medium || windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded) && windowSizeClass.heightSizeClass == WindowHeightSizeClass.Compact) {
                LANDSCAPE
            } else {
                TABLET
            }
        }
    }
}