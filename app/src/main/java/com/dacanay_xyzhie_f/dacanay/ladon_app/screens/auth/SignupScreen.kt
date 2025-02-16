package com.dacanay_xyzhie_f.dacanay.ladon_app.screens.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dacanay_xyzhie_f.dacanay.ladon_app.R
import com.dacanay_xyzhie_f.dacanay.ladon_app.navigation.Routes

@Composable
fun SignUpScreen(navController: NavHostController) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(12.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            //Back Button
            IconButton(
                onClick = { navController.navigate(Routes.LogSign) },
                modifier = Modifier
                    .padding(top = 30.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_arrow_back_24),
                    contentDescription = "Arrow Back image Button"

                )
            }

        }

    }}


