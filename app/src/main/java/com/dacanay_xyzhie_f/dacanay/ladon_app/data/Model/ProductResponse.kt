package com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model

data class ProductResponse(
    val id: Int,
    val product_name: String,
    val product_category: String?,
    val product_price: Double,
    val stock: Int,
    val product_image: String? // base64
)
