package com.dacanay_xyzhie_f.dacanay.ladon_app.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacyPolicyScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Privacy Policy",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth(0.8f),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFE3F2FD))
            )
        },
        containerColor = Color(0xFFE3F2FD)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    PolicyText(title = "TERMS OF USE", content = """
                        Welcome to Ladon’s Photocopies & Supplies! By using our platform, you agree to the following terms:
                        1. You must be at least 13 years old to create an account and make purchases.
                        2. You are responsible for maintaining the confidentiality of your account and password.
                        3. All content on this platform (e.g., product descriptions, images) is owned by us and cannot be used without permission.
                        4. We reserve the right to suspend or terminate accounts that violate our policies.
                    """)

                    PolicyText(title = "DATA PRIVACY", content = """
                        1. We collect personal information (e.g., name, email, address) to process orders and improve your experience.
                        2. Your data is securely stored and will not be shared with third parties without your consent.
                        3. We use cookies to enhance site functionality and analyze user behavior.
                        4. You can request to delete your data by contacting our support team.
                    """)

                    PolicyText(title = "SHIPPING POLICY", content = """
                        1. Orders are processed within 1-2 business days.
                        2. Delivery times vary based on your location and the shipping method selected.
                        3. Shipping costs are calculated at checkout and are non-refundable.
                        4. Delays due to unforeseen circumstances (e.g., weather, customs) are not our responsibility.
                    """)

                    PolicyText(title = "PAYMENT POLICY", content = """
                        1. To ensure smooth transactions, all payments via Stripe must be at least ₱30.00 PHP (approx.$0.50 USD). Payments below this amount will be declined.
                        2. This policy helps cover Stripe's processing fees and ensures efficient service.
                        
                    """)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun PolicyText(title: String, content: String) {
    Column(modifier = Modifier.padding(bottom = 12.dp)) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = content.trimIndent(),
            fontSize = 14.sp,
            lineHeight = 20.sp,
            color = Color.Black
        )
    }
}
