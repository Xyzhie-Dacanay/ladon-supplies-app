package com.dacanay_xyzhie_f.dacanay.ladon_app.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dacanay_xyzhie_f.dacanay.ladon_app.R
import androidx.navigation.compose.rememberNavController
import com.dacanay_xyzhie_f.dacanay.ladon_app.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportScreen(navController: NavController?) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Report a Problem",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(0.8f)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController?.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFE8F3F7))
            )
        },
        containerColor = Color(0xFFE8F3F7)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .background(Color(0xFFE8F3F7)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // Problem Icon
            Image(
                painter = painterResource(id = R.drawable.anxiety),
                contentDescription = "Problem Icon",
                modifier = Modifier
                    .size(280.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Title Text
            Text(
                text = "Got a concern or problem?",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Subtitle Text
            Text(
                text = "Fill the form after this so the developer will try their best to fix it",
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 32.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Continue Button
            Button(
                onClick = { navController?.navigate(Routes.ReportProblemScreen) },
                colors = ButtonDefaults.buttonColors(Color(0xFF3498DB)),
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(0.6f)
                    .height(50.dp)
            ) {
                Text(text = "CONTINUE", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
