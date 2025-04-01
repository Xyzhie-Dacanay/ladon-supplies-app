package com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model

data class TicketRequest(
    val subject: String,
    val description: String,
    val ticket_type: String,
    val screenshot: String? = null // Optional base64 image
)
