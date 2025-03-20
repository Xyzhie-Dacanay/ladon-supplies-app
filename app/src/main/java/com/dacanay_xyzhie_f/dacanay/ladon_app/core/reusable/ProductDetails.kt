package com.dacanay_xyzhie_f.dacanay.ladon_app.core.reusable

import com.dacanay_xyzhie_f.dacanay.ladon_app.R

data class Product(
    val id: Int,
    val name: String,
    val price: String,
    val imageRes: Int,
    val category: String
)

val productLists = listOf(
    Product(1,"Short Bond Paper (1 Ream)", "P39.00", R.drawable.oneream,  "Bond Paper"),
    Product(2, "1 pc Epson Ink", "P250.00", R.drawable.soloink,  "Ink"),
    Product(3,"Pilot Ballpen (1 Box)", "P400.00", R.drawable.pilotballpen,  "Ballpen"),
    Product(4,"Pilot Ballpen (1 Box)", "P400.00", R.drawable.pilotballpen,  "Ballpen"),
    Product(5,"Pilot Ballpen (1 Box)", "P400.00", R.drawable.pilotballpen,  "Ballpen"),
    Product(6,"Pilot Ballpen (1 Box)", "P400.00", R.drawable.pilotballpen,  "Ballpen"),
    Product(7,"Pilot Ballpen (1 Box)", "P400.00", R.drawable.pilotballpen,  "Ballpen"),
    Product(8,"Pilot Ballpen (1 Box)", "P400.00", R.drawable.pilotballpen,  "Ballpen"),
    Product(9,"Pilot Ballpen (1 Box)", "P400.00", R.drawable.pilotballpen,  "Ballpen"),
    Product(10,"Pilot Ballpen (1 Box)", "P400.00", R.drawable.pilotballpen,  "Ballpen"),
    Product(11,"Pilot Ballpen (1 Box)", "P400.00", R.drawable.pilotballpen,  "Ballpen"),
    Product(12,"Pilot Ballpen (1 Box)", "P400.00", R.drawable.pilotballpen,  "Ballpen"),
    Product(13,"Pilot Ballpen (1 Box)", "P400.00", R.drawable.pilotballpen,  "Ballpen"),
    Product(14,"Pilot Ballpen (1 Box)", "P400.00", R.drawable.pilotballpen,  "Ballpen"),
    Product(15,"Colored Chalk (1 Box)", "P30.00", R.drawable.coloredchalk,  "Chalk"),
    Product(16,"Notebook A5", "P50.00", R.drawable.book,  "Notebook"),
    Product(17,"Notebook A5", "P50.00", R.drawable.book,  "Notebook"),
    Product(18,"Notebook A5", "P50.00", R.drawable.book,  "Notebook"),
    Product(19,"Notebook A5", "P50.00", R.drawable.book,  "Notebook"),
    Product(20,"Notebook A5", "P50.00", R.drawable.book,  "Notebook"),
    Product(21,"Notebook A5", "P50.00", R.drawable.book,  "Notebook"),
    Product(22,"Notebook A5", "P50.00", R.drawable.book,  "Notebook"),
)
