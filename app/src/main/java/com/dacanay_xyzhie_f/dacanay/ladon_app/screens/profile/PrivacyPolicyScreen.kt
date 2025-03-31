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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun PrivacyPolicyScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD)) // Light background
            .padding(16.dp)
            .verticalScroll(rememberScrollState()) // Enable scrolling
    ) {
        // Align Back Button and Title in a Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp, bottom = 8.dp), // Reduced bottom padding to bring them closer
            verticalAlignment = Alignment.CenterVertically // Align items vertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
            Spacer(modifier = Modifier.width(4.dp)) // Reduced space between icon and text
            Text(
                text = "Privacy & Policy",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f) // Centering while keeping it dynamic
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White, shape = RoundedCornerShape(24.dp)) // Rounded corners for container
                .padding(24.dp), // Consistent padding inside
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Sections
                PolicyText(title = "TERMS OF USE", content = """
                    Welcome to Ladon’s Photocopies & Supplies! By using our platform, you agree to the following terms:
                    1. You must be at least 18 years old to create an account and make purchases.
                    2. You are responsible for maintaining the confidentiality of your account and password.
                    3. All content on this platform (e.g., product descriptions, images) is owned by us and cannot be used without permission.
                    4. We reserve the right to suspend or terminate accounts that violate our policies.
                """)

                PolicyText(title = "PRIVACY POLICY", content = """
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

                PolicyText(title = "RETURN & REFUND POLICY", content = """
                    1. Items must be returned in their original condition within 30 days of delivery.
                    2. Refunds will be issued to the original payment method.
                    3. Shipping costs for returns are the responsibility of the customer unless the item is defective or incorrect.
                """)
            }
        }
    }
}

@Composable
fun PolicyText(title: String, content: String) {
    Column(modifier = Modifier.padding(bottom = 12.dp)) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold, // Bold section titles
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

@Preview(showBackground = true)
@Composable
fun PreviewPrivacyPolicyScreen() {
    PrivacyPolicyScreen(navController = rememberNavController())
}
