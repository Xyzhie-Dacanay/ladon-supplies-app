package com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model

import com.dacanay_xyzhie_f.dacanay.ladon_app.R

data class ProductButton(val imageResId: Int, val text: String)

val productButtonList = listOf(
    ProductButton(R.drawable.bondpaper, "Bondpaper"),
    ProductButton(R.drawable.eraser, "Eraser"),
    ProductButton(R.drawable.ballpen, "Ballpen"),
    ProductButton(R.drawable.marker, "Marker"),
    ProductButton(R.drawable.pencils, "Pencil"),
    ProductButton(R.drawable.stamppad, "Stamp Pad"),
    ProductButton(R.drawable.stapler, "Stapler"),
    ProductButton(R.drawable.crayons, "Crayons"),
    ProductButton(R.drawable.tape, "Tape"),
    ProductButton(R.drawable.paperclip, "Paper Clip"),
    ProductButton(R.drawable.velum, "Vellum Paper"),
    ProductButton(R.drawable.photopaper, "Photo Paper"),
    ProductButton(R.drawable.ink, "Ink"),
    ProductButton(R.drawable.envelopes, "Envelope"),
    ProductButton(R.drawable.pins, "Pins"),
    ProductButton(R.drawable.chalks, "Chalk"),
    ProductButton(R.drawable.paper, "Paper"),
    ProductButton(R.drawable.folder, "Folder"),
)
