package com.dacanay_xyzhie_f.dacanay.ladon_app.screens.orders

import com.dacanay_xyzhie_f.dacanay.ladon_app.core.reusable.Product

data class CartItem(
    val product: Product,
    val quantity: Int = 1,
    val isSelected: Boolean = true
)
