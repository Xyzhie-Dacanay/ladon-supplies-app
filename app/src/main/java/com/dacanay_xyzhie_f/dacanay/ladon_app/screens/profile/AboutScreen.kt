package com.dacanay_xyzhie_f.dacanay.ladon_app.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dacanay_xyzhie_f.dacanay.ladon_app.R

@Composable
fun AboutScreen(
    navController: NavController,
    topMargin: Dp = 100.dp,
    bottomMargin: Dp = 4.dp
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title Bar (Same as Help Screen)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "About",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // App Description Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Ladon’s Photocopies and Supplies",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF35AEFF)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Ladon Photocopies and Supplies is a mobile app for quick and hassle-free ordering of school supplies anytime, anywhere.",
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mission Section - Two Column Layout
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Text(
                        text = "Our Mission",
                        color = Color(0xFF35AEFF),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.primary),
                        contentDescription = "Ladon Logo",
                        modifier = Modifier
                            .size(270.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }

            Card(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Our mission is to provide a fast, easy, and reliable way to order school supplies. We aim to make shopping more convenient and accessible, helping students and professionals get what they need without the hassle.",
                        fontSize = 14.sp

                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Team Section
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row {
                    Text(
                        text = "The people behind ",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "this project",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF35AEFF)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                val teamMembers = listOf(
                    "Lead App Developer:" to "Xyzhie Dacanay",
                    "Assist. App Developer:" to "Nathaniel Sison",
                    "Lead Web Developer:" to "Ken Camagay",
                    "Assist. Web Developer:" to "Thea Ladon",
                    "Backend Dev App and Web:" to "Kristian Narvas",
                    "UI Designer App and Web:" to "Terrence Lopez"
                )


                val roleColumnWidth = 180.dp

                teamMembers.forEach { (role, name) ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            text = role,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.width(roleColumnWidth)
                        )
                        Text(
                            text = name,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF35AEFF),
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAboutScreen() {
    AboutScreen(navController = rememberNavController(), topMargin = 16.dp, bottomMargin = 16.dp)
}
