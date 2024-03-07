package com.route.todoappc39g_mon_wed

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@BindingAdapter("error")
fun TextInputEditText.setCustomError(errorMessage: String?) {
    error = errorMessage
}

@BindingAdapter("formatDate")
fun TextView.formattingDate(date: Date?) {
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val dateAsString = simpleDateFormat.format(date ?: Date())
    text = dateAsString
}