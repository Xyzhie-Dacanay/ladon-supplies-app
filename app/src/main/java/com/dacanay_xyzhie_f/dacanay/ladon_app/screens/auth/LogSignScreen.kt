package com.dacanay_xyzhie_f.dacanay.ladon_app.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.dacanay_xyzhie_f.dacanay.ladon_app.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun LogSignScreen() {


    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

    Image(painter = painterResource(id = R.drawable.primary), contentDescription = "Firt Image")

        Spacer(modifier = Modifier.height(36.dp))
      Button(
          onClick = {},
          modifier = Modifier.height(55.dp).width(250.dp),
          colors = ButtonDefaults.buttonColors(
              containerColor = Color(0xFF35AEFF),

              )) {
          Text(text="LogIn",
              fontSize = 14.sp
              )
      }

        Spacer(modifier = Modifier.height(14.dp))

        ElevatedButton(
            onClick = {},
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 8.dp,
                pressedElevation = 2.dp,
                hoveredElevation = 10.dp
            ),
            modifier = Modifier.height(55.dp).width(250.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFFFFF),

            )
        ) {
            Text(text="SignUp",
                fontSize = 14.sp,
                color = Color.Black

            )
        }

        Spacer(modifier = Modifier.height(36.dp))
        TextButton(
            onClick = {},
            colors = ButtonDefaults.textButtonColors(


            )
            ) {
            Text(text="Continue as Guest",
                fontSize = 14.sp,
                textDecoration = TextDecoration.Underline,
                color = Color.Black,
                modifier = Modifier.clickable


                {

                })
        }

}


    }

