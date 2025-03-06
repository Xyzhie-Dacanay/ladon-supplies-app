package com.dacanay_xyzhie_f.dacanay.ladon_app.core.reusable

import com.dacanay_xyzhie_f.dacanay.ladon_app.R

data class ProductButtonData(
    val imageResId: Int,
    val text: String,
    val onClick: () -> Unit
)

val productButtonList = listOf(
    ProductButtonData(R.drawable.pencils, "Ballpen") { /* Handle click */ },
    ProductButtonData(R.drawable.ballpen, "Pencil") { /* Handle click */ },
    ProductButtonData(R.drawable.book, "Pentel Pen") { /* Handle click */ },
    ProductButtonData(R.drawable.bondpaper, "Chalk") { /* Handle click */ },
    ProductButtonData(R.drawable.bondpaper, "Velum") { /* Handle click */ },
    ProductButtonData(R.drawable.bondpaper, "Paper") { /* Handle click */ },
    ProductButtonData(R.drawable.bondpaper, "Notebook") { /* Handle click */ },
    ProductButtonData(R.drawable.ballpen, "Photo Paper") { /* Handle click */ },
    ProductButtonData(R.drawable.ballpen, "Envelopes") { /* Handle click */ },
    ProductButtonData(R.drawable.ballpen, "Bond Paper") { /* Handle click */ },
    ProductButtonData(R.drawable.ballpen, "Eraser") { /* Handle click */ },
    ProductButtonData(R.drawable.ballpen, "Stapler") { /* Handle click */ },
    ProductButtonData(R.drawable.ballpen, "Stamp Pad") { /* Handle click */ },
    ProductButtonData(R.drawable.ballpen, "Tape") { /* Handle click */ },
    ProductButtonData(R.drawable.ballpen, "Paper Clip") { /* Handle click */ },
    ProductButtonData(R.drawable.ballpen, "Pins") { /* Handle click */ },
    ProductButtonData(R.drawable.ballpen, "Folder") { /* Handle click */ },
    ProductButtonData(R.drawable.ballpen, "Crayons") { /* Handle click */ },
    ProductButtonData(R.drawable.ballpen, "Ink") { /* Handle click */ },
    ProductButtonData(R.drawable.ballpen, "Others") { /* Handle click */ },

    )