package com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model

data class CartItemResponse(
    val product_id: Int,
    val quantity: Int,
    val product_name: String,
    val product_price: Double,
    val product_image: String?,
    val stock: Int
)
