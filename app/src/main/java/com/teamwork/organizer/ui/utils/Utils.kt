package com.teamwork.organizer.ui.utils

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Victor Tellez on 08/02/2018.
 */

/**
 * Inflates a view with the layoutRes id.
 */
fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

/**
 * Formats a date to DAY_OF_MONTH MM/YY
 */
fun Date.formatMonthYear(): String {
    val calendar = Calendar.getInstance()
    calendar.time = this
    //return calendar.get(Calendar.DAY_OF_MONTH).toString() + " " +  SimpleDateFormat("DD/MMM/YY").format(calendar.time)
    return SimpleDateFormat("dd/MM/yyyy").format(calendar.time)
}