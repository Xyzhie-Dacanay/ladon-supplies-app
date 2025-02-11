package com.dacanay_xyzhie_f.dacanay.ladon_app.core.reusable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dacanay_xyzhie_f.dacanay.ladon_app.R


@Composable

fun HeadingText(value:String){

    Text(
        text = value,
        modifier = Modifier.fillMaxWidth().heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        )

        ,color = colorResource(id = R.color.black),
        textAlign = TextAlign.Center
    )



}
@Composable

fun DescriptionText(value:String) {

    Text(
        text = value,
        modifier = Modifier.fillMaxWidth().heightIn(min = 80.dp),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ), color = colorResource(id = R.color.black),
        textAlign = TextAlign.Center
    )

}

    @Composable

    fun RememberTxt(value: String) {

        Text(
            text = value,
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal
            ), color = colorResource(id = R.color.primaryColor),

        )


    }





