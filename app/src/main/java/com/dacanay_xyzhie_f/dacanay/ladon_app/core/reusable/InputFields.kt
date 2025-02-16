package com.dacanay_xyzhie_f.dacanay.ladon_app.core.reusable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.dacanay_xyzhie_f.dacanay.ladon_app.R
import com.dacanay_xyzhie_f.dacanay.ladon_app.ui.theme.BlackLa
import com.dacanay_xyzhie_f.dacanay.ladon_app.ui.theme.BlueLa
import com.dacanay_xyzhie_f.dacanay.ladon_app.ui.theme.GrayLa


//Email Text Fields
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputFields(labelValue: String, painterResource: Painter) {
    val textValue = remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            value = textValue.value,
            onValueChange = { textValue.value = it },
            placeholder = { Text(text = labelValue, color = Color.Gray) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                cursorColor = colorResource(id = R.color.black),
                containerColor = colorResource(id = R.color.tfBackground),
            ),
            shape = RoundedCornerShape(20.dp),
            keyboardOptions = KeyboardOptions.Default,
            leadingIcon = {
                Icon(
                    painter = painterResource,
                    contentDescription = null,
                    tint = colorResource(id=R.color.primaryColor),
                    modifier = Modifier.size(24.dp)
                )
            }
        )
    }
}


//Label
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


//Password TextFields
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PassFields(labelValue: String, painterResource: Painter) {
    val password = remember { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) } // ✅ Boolean state

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = password.value,
        onValueChange = { password.value = it },
        placeholder = { Text(text = labelValue, color = Color.Gray) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            cursorColor = colorResource(id = R.color.black),
            containerColor = colorResource(id = R.color.tfBackground),
        ),
        shape = RoundedCornerShape(20.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        leadingIcon = {
            Icon(painter = painterResource,
                contentDescription = null,
                tint = colorResource(id=R.color.primaryColor),
                modifier = Modifier.size(24.dp)
                )
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
                Icon(imageVector = iconImage,
                    contentDescription = description,
                    tint = colorResource(id=R.color.primaryColor),
                    modifier = Modifier.size(24.dp)
                    )
            }
        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation()
    )
}


//Checkbox
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
            modifier = Modifier.size(8.dp)
        )

        Spacer(modifier = Modifier.width(15.dp))

        RememberTxt(value)
    }
}


//Button
@Composable
fun ButtonComponent (value: String) {
    Button(onClick = {},
        modifier = Modifier.fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ) {

        Box(modifier = Modifier.fillMaxWidth()
            .heightIn(50.dp).background(
                color = BlueLa,
                shape = RoundedCornerShape(50.dp)

           ),
            contentAlignment = Alignment.Center

        ){

            Text(text=value,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold)


        }

    }
}

//Divider
@Composable
fun DividerComponent() {
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically)
    {
        Divider(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            color= GrayLa,
            thickness = 1.dp)


        Text(modifier = Modifier.padding(8.dp),
            text="or", fontSize = 18.sp, color = GrayLa)

        Divider(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            color= GrayLa,
            thickness = 1.dp)

    }

}

//Button for login and signup
@Composable
fun ButtonComponent() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .wrapContentSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.faceebook),
            contentDescription = "facebook",
            modifier = Modifier
                .clickable { }
                .size(50.dp)
        )

        Spacer(modifier = Modifier.width(24.dp))



        Image(
            painter = painterResource(id = R.drawable.googlee),
            contentDescription = "google",
            modifier = Modifier
                .clickable { }
                .size(50.dp)
        )
    }
}

//Text Button
@Composable
fun ButtonTextComponent(navController: NavHostController) {
    val context = LocalContext.current

    val annotatedString = buildAnnotatedString {
        append("Don't have an account? ")


        val startIndex = length
        append("Sign up")
        val endIndex = length


        addStyle(
            style = SpanStyle(
                color = Color(0xFF35AEFF),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            ),
            start = startIndex,
            end = endIndex
        )


        addStringAnnotation(
            tag = "SignUp",
            annotation = "sign_up",
            start = startIndex,
            end = endIndex
        )
    }

    ClickableText(
        text = annotatedString,
        onClick = { offset ->
            annotatedString.getStringAnnotations(tag = "SignUp", start = offset, end = offset)
                .firstOrNull()?.let {

                    navController.navigate("SignupScreen")
                }
        }
    )
}






