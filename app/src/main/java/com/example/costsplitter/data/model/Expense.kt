package com.example.costsplitter.data.model

import java.util.Date

data class Expense(
    val id: String = "",
    val date: Date = Date(),
    val title: String = "",
    val description: String? = null,
    val icon: String = "",
    val amount: Double = 0.0,
    val payer: String = "",
    val sharedWith: Map<String, Double> = emptyMap(), // UID to amount map
    val receiptPhotoUrl: String? = null
)