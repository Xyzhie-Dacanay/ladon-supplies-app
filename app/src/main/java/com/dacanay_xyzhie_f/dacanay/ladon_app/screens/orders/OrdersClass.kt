package com.dacanay_xyzhie_f.dacanay.ladon_app.screens.orders

data class Order(
    val orderId: String,
    val status: String,
    val totalAmount: Double
)

val orders = listOf(
    Order("ORD-001", "Processing", 1299.99),
    Order("ORD-002", "Shipped", 850.00),
    Order("ORD-003", "Delivered", 440.50)
)
