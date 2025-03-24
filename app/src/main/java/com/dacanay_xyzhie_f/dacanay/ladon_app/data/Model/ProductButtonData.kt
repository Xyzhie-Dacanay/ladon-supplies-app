package com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model

import com.dacanay_xyzhie_f.dacanay.ladon_app.R

data class ProductButtonData(
    val imageResId: Int,
    val text: String,
    val onClick: (String) -> Unit
)

val productButtonList = listOf(
    ProductButtonData(R.drawable.pencils, "Ballpen") { category ->},
    ProductButtonData(R.drawable.ballpen, "Pencil") {category ->},
    ProductButtonData(R.drawable.book, "Pentel Pen") { category -> },
    ProductButtonData(R.drawable.bondpaper, "Chalk") {category ->},
    ProductButtonData(R.drawable.bondpaper, "Velum") { category ->},
    ProductButtonData(R.drawable.bondpaper, "Paper") { category -> },
    ProductButtonData(R.drawable.bondpaper, "Notebook") { category ->},
    ProductButtonData(R.drawable.ballpen, "Photo Paper") {category ->},
    ProductButtonData(R.drawable.ballpen, "Envelopes") { category ->},
    ProductButtonData(R.drawable.ballpen, "Bond Paper") { category ->},
    ProductButtonData(R.drawable.ballpen, "Eraser") { category ->},
    ProductButtonData(R.drawable.ballpen, "Stapler") { category ->},
    ProductButtonData(R.drawable.ballpen, "Stamp Pad") { category ->},
    ProductButtonData(R.drawable.ballpen, "Tape") { category -> },
    ProductButtonData(R.drawable.ballpen, "Paper Clip") {category -> },
    ProductButtonData(R.drawable.ballpen, "Pins") { category ->},
    ProductButtonData(R.drawable.ballpen, "Folder") {category -> },
    ProductButtonData(R.drawable.ballpen, "Crayons") { category ->},
    ProductButtonData(R.drawable.ballpen, "Ink") { category ->},
    ProductButtonData(R.drawable.ballpen, "Others") {category ->},

    )