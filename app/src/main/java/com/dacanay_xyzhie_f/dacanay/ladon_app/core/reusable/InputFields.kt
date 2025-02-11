package com.dacanay_xyzhie_f.dacanay.ladon_app.core.reusable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dacanay_xyzhie_f.dacanay.ladon_app.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputFields(labelValue: String, painterResource : Painter) {
    val textValue = remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = textValue.value,
        onValueChange = { textValue.value = it },
        placeholder = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(

            focusedBorderColor = colorResource(id = R.color.black),
            focusedLabelColor = colorResource(id = R.color.black),
            cursorColor =  colorResource(id = R.color.black),
            containerColor = colorResource(id = R.color.tfBackground),



        ),
        shape = RoundedCornerShape(30.dp),
        keyboardOptions = KeyboardOptions.Default,
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = null )
        }


    )
}

@Composable

fun LabelText(value:String){

    Text(
        text = value,
        modifier = Modifier.fillMaxWidth().heightIn(min = 10.dp),
        style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        )

        ,color = colorResource(id = R.color.black),

    )}

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun PassFields(labelValue: String, painterResource: Painter) {
    val password = remember { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) } // ✅ Boolean state

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = password.value,
        onValueChange = { password.value = it },
        placeholder = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.black),
            focusedLabelColor = colorResource(id = R.color.black),
            cursorColor = colorResource(id = R.color.black),
            containerColor = colorResource(id = R.color.tfBackground),
        ),
        shape = RoundedCornerShape(30.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = null)
        },
        trailingIcon = {
            val iconImage = if (passwordVisible.value) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }

            val description = if (passwordVisible.value) {
                "Hide Password"
            } else {
                "Show Password"
            }

            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(imageVector = iconImage, contentDescription = description)
            }
        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation()
    )
}


@Composable
fun RememberComp(value: String) {
    val checkedState = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checkedState.value,
            onCheckedChange = { checkedState.value = !checkedState.value },
            modifier = Modifier.size(14.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        RememberTxt(value)
    }
}








