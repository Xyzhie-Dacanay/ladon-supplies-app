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
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.dacanay_xyzhie_f.dacanay.ladon_app.R
import com.dacanay_xyzhie_f.dacanay.ladon_app.navigation.Routes
import com.dacanay_xyzhie_f.dacanay.ladon_app.presentation.auth.AuthViewModel
import com.dacanay_xyzhie_f.dacanay.ladon_app.ui.theme.BlueLa
import com.dacanay_xyzhie_f.dacanay.ladon_app.ui.theme.GrayLa


//Email Text Fields
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputFields(
    labelValue: String,
    painterResource: Painter,
    value: String,
    onValueChange: (String) -> Unit,
    errorMessage: String? = null
) {


    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            value = value,
            onValueChange = onValueChange,
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
                    tint = colorResource(id = R.color.primaryColor),
                    modifier = Modifier.size(24.dp)
                )
            }
        )

        if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)

            )
        }
    }
}


//Label
@Composable
fun LabelText(value: String) {

    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 10.dp),
        style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        color = colorResource(id = R.color.black),

        )
}


//Password TextField for login and Sign up
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PassFields(
    labelValue: String,
    painterResource: Painter,
    value: String,
    onValueChange: (String) -> Unit,
    errorMessage: String?
) {

    val passwordVisible = remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
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
            Icon(
                painter = painterResource,
                contentDescription = null,
                tint = colorResource(id = R.color.primaryColor),
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
                Icon(
                    imageVector = iconImage,
                    contentDescription = description,
                    tint = colorResource(id = R.color.primaryColor),
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation()
    )
    if (errorMessage != null) {
        Text(
            text = errorMessage,
            color = Color.Red,
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 8.dp, top = 4.dp)
        )
    }
}


//Password Textfield for signup confirmation
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpPasswordField(
    labelValue: String,
    painterResource: Painter,
    value: String,
    onValueChange: (String) -> Unit,
    errorMessage: String?

) {
    val password = remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = labelValue, color = Color.Gray) }, // ✅ Placeholder for password
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Transparent, // ✅ No visible border when focused
            unfocusedBorderColor = Color.Transparent, // ✅ No border when unfocused
            cursorColor = colorResource(id = R.color.black),
            containerColor = colorResource(id = R.color.tfBackground),
        ),
        shape = RoundedCornerShape(20.dp), // ✅ Soft rounded corners
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        leadingIcon = {
            Icon(
                painter = painterResource,
                contentDescription = null,
                tint = colorResource(id = R.color.primaryColor), // ✅ Primary theme color
                modifier = Modifier.size(24.dp)
            )
        },
        visualTransformation = PasswordVisualTransformation() // ✅ Always hides password (No eye icon)
    )
    if (errorMessage != null) {
        Text(
            text = errorMessage,
            color = Color.Red,
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 8.dp, top = 4.dp)
        )
    }
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
            modifier = Modifier.size(8.dp),
            colors = CheckboxDefaults.colors(
                checkedColor = Color(0xFF35AEFF),
                uncheckedColor = Color.Gray,
                checkmarkColor = Color.White
            )
        )

        Spacer(modifier = Modifier.width(12.dp))

        RememberTxt(value)
    }
}


// Login Button
@Composable
fun LoginButtonComponent(value: String, navController: NavHostController) {
    Button(
        onClick = {

            navController.navigate(Routes.HomePage)

        },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(50.dp)
                .background(
                    color = BlueLa,
                    shape = RoundedCornerShape(50.dp)

                ),
            contentAlignment = Alignment.Center

        ) {

            Text(
                text = value,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )


        }

    }
}

// SignUp Button
@Composable
fun SignupButtonComponent(
    value: String,
    authViewModel: AuthViewModel,
    navController: NavHostController
) {
    Button(
        onClick = {
            if (authViewModel.validateSignUp()) {
                navController.navigate(Routes.HomePage)
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(50.dp)
                .background(
                    color = BlueLa,
                    shape = RoundedCornerShape(50.dp)

                ),
            contentAlignment = Alignment.Center

        ) {

            Text(
                text = value,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )


        }

    }
}


//Divider
@Composable
fun DividerComponent() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = GrayLa,
            thickness = 1.dp
        )


        Text(
            modifier = Modifier.padding(8.dp),
            text = "or", fontSize = 18.sp, color = GrayLa
        )

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = GrayLa,
            thickness = 1.dp
        )

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

@Composable
fun ButtonTextComponent(navController: NavHostController, isSignUpScreen: Boolean) {
    val annotatedString = buildAnnotatedString {
        if (isSignUpScreen) {
            // 🔹 Sign-Up Screen: "Already have an account? Log In"
            append("Already have an account? ")
            val startIndex = length
            append("Log In")
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
                tag = "Navigate",
                annotation = "login",
                start = startIndex,
                end = endIndex
            )
        } else {
            // 🔹 Login Screen: "Don't have an account? Sign Up"
            append("Don't have an account? ")
            val startIndex = length
            append("Sign Up")
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
                tag = "Navigate",
                annotation = "signup",
                start = startIndex,
                end = endIndex
            )
        }
    }

    ClickableText(
        text = annotatedString,
        onClick = { offset ->
            annotatedString.getStringAnnotations(tag = "Navigate", start = offset, end = offset)
                .firstOrNull()?.let {
                    if (it.item == "signup") {
                        navController.navigate("SignupScreen") // ✅ Navigate to Sign-Up
                    } else if (it.item == "login") {
                        navController.navigate("LoginScreen") // ✅ Navigate to Login
                    }
                }
        }
    )
}




