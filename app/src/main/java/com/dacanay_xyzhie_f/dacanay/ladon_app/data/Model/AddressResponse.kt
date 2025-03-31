package com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model

data class AddressResponse(
    val id: Int,
    val user_id: Int,
    val street: String,
    val barangay: String,
    val municipality: String,
    val province: String
) {
    val full_address: String
        get() = "$street, $barangay, $municipality, $province"
}
