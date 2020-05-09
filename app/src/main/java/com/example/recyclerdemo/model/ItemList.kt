package com.example.recyclerdemo.model

import java.time.LocalDate

data class ItemList(var title: String, var date: LocalDate, var url: String, var isStar: Boolean, var isWebView : Boolean, var http : String?)

