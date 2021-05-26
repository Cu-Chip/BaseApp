package com.apcc.utils

import androidx.annotation.IntDef


////////////////////////////////////////
// using for view
/////////////////////////////////////////
@IntDef(
    TextType.TEXT_NORMAL,
    TextType.TEXT_HTML,
    TextType.TEXT_BIG,
    TextType.TEXT_SMALL,
    TextType.TEXT_NORMAL_ACCENT,
    TextType.TEXT_BIG_ACCENT,
    TextType.TEXT_SMALL_ACCENT,
    TextType.TEXT_NORMAL_SUCCESS,
    TextType.TEXT_BIG_SUCCESS,
    TextType.TEXT_SMALL_SUCCESS,
    TextType.TEXT_NORMAL_IMPORTANT,
    TextType.TEXT_BIG_IMPORTANT,
    TextType.TEXT_SMALL_IMPORTANT
)
@Retention(AnnotationRetention.SOURCE)
annotation class TextType {
    companion object {
        const val TEXT_HTML = 0

        /*normal*/
        const val TEXT_NORMAL = 1
        const val TEXT_BIG = 2
        const val TEXT_HUGE = 3
        const val TEXT_SMALL = 4

        /*accent*/
        const val TEXT_NORMAL_ACCENT = 5
        const val TEXT_BIG_ACCENT = 6
        const val TEXT_HUGE_ACCENT = 7
        const val TEXT_SMALL_ACCENT = 8

        /*success*/
        const val TEXT_NORMAL_SUCCESS = 9
        const val TEXT_BIG_SUCCESS = 10
        const val TEXT_HUGE_SUCCESS = 11
        const val TEXT_SMALL_SUCCESS = 12

        /*important*/
        const val TEXT_NORMAL_IMPORTANT = 13
        const val TEXT_BIG_IMPORTANT = 14
        const val TEXT_HUGE_IMPORTANT = 15
        const val TEXT_SMALL_IMPORTANT = 16

        /*disable*/
        const val TEXT_NORMAL_DISABLE = 17
        const val TEXT_BIG_DISABLE = 18
        const val TEXT_HUGE_DISABLE = 19
        const val TEXT_SMALL_DISABLE = 20
    }
}



@IntDef(
    RepeatType.NONE,
    RepeatType.DAILY,
    RepeatType.WEEKLY,
    RepeatType.MONTHLY,
    RepeatType.YEARLY
)
@Retention(AnnotationRetention.SOURCE)
annotation class RepeatType {
    companion object {
        const val NONE = 1 // no repeat, do once time
        const val DAILY = 2
        const val WEEKLY = 4
        const val MONTHLY = 8
        const val YEARLY = 16
    }
}



@IntDef(Gender.UNISEX, Gender.MALE, Gender.FEMALE)
@Retention(AnnotationRetention.SOURCE)
annotation class Gender {
    companion object {
        const val UNISEX = 0
        const val MALE = 1
        const val FEMALE = 2
    }
}