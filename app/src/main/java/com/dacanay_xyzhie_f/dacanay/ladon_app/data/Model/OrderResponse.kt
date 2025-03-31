package com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model

data class OrderResponse(
    val order_id: String,
    val status: String,
    val total: String,              // ✅ matches backend
    val date: String,               // ✅ matches backend
    val items: List<String>,        // ✅ strings like "Burger x2"
    val payment_method: String
)
data class OrderItem(
    val name: String,
    val quantity: Int
)

