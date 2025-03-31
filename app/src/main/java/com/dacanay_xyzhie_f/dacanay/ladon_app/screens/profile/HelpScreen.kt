package com.dacanay_xyzhie_f.dacanay.ladon_app.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun HelpScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE6F7FF))
            .padding(16.dp)
            .verticalScroll(rememberScrollState()) // ⬅️ Ensures the whole screen scrolls
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .padding(bottom = 4.dp, top = 50.dp)
                    .size(24.dp)
                    .clickable { navController.popBackStack() }
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Help Center",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 4.dp, top = 50.dp)
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(16.dp))

        HelpSection(
            title = "Account & Profile",
            faqs = listOf(
                "How do I update my profile information?" to "Just head to the account page and click the 'My Account'.",
                "Can I change my email address?" to "Yes, you can update your email address in the 'My Account' section of your Account page."
            )
        )
        HelpSection(
            title = "Orders & Payments",
            faqs = listOf(
                "How can I track my order?" to "Visit the Order History section in your Account page.",
                "What payment methods do you accept?" to "We accept credit/debit cards, PayPal, and other popular payment methods."
            )
        )
        HelpSection(
            title = "Shipping & Delivery",
            faqs = listOf(
                "What are the delivery options?" to "We offer standard and express shipping.",
                "How long does delivery take?" to "Standard shipping takes 5-7 business days, while express shipping takes 2-3 business days."
            )
        )
    }
}

@Composable
fun HelpSection(title: String, faqs: List<Pair<String, String>>) {
    Column(modifier = Modifier.padding(bottom = 16.dp)) {
        Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        faqs.forEach { (question, answer) ->
            Card(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Q: $question", fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 4.dp))
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "A: $answer", modifier = Modifier.padding(bottom = 4.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHelpScreen() {
    HelpScreen(navController = rememberNavController())
}
