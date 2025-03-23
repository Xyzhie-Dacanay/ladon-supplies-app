package com.dacanay_xyzhie_f.dacanay.ladon_app.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Help
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Report
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dacanay_xyzhie_f.dacanay.ladon_app.core.reusable.NavBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.dacanay_xyzhie_f.dacanay.ladon_app.R
import com.dacanay_xyzhie_f.dacanay.ladon_app.core.reusable.ProfileOption
import com.dacanay_xyzhie_f.dacanay.ladon_app.navigation.Routes


@Composable
fun ProfileScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = { NavBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE6F8FF))
                .padding(paddingValues)
                .padding(horizontal = 18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.primary),
                    contentDescription = "App Logo",
                    modifier = Modifier.size(100.dp)
                )

                Row {
                    IconButton(onClick = { /* Navigate to Cart */ }) {
                        Icon(
                            imageVector = Icons.Outlined.ShoppingCart,
                            contentDescription = "Cart",
                            modifier = Modifier.size(32.dp),
                            tint = Color.Black
                        )
                    }

                    Spacer(modifier = Modifier.width(5.dp))


                    IconButton(onClick = { /* Navigate to Notifications */ }) {
                        Icon(
                            imageVector = Icons.Outlined.Notifications,
                            contentDescription = "Notifications",
                            modifier = Modifier.size(32.dp),
                            tint = Color.Black
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(5.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
                    .padding(20.dp),
                contentAlignment = Alignment.Center

            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                    ) {
                        // Profile Picture
                        Box(
                            modifier = Modifier
                                .size(130.dp)
                                .clip(CircleShape)
                                .background(Color.LightGray),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Profile",
                                modifier = Modifier.size(80.dp)
                            )
                        }

                        // Edit Profile Button (Overlapping)
                        IconButton(
                            onClick = { navController.navigate(Routes.EditProfileScreen)},
                            modifier = Modifier
                                .size(32.dp)
                                .align(Alignment.BottomEnd)
                                .offset(x = 1.dp, y = 1.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Edit,
                                contentDescription = "Edit Profile",
                                tint = Color.White,
                                modifier = Modifier
                                    .background(Color(0xFF35AEFF), shape = CircleShape)
                                    .padding(4.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = "John Ross Doe", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }

            }

                Spacer(modifier = Modifier.height(12.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, shape = RoundedCornerShape(16.dp))
                        .padding(16.dp)
                ) {
                    ProfileOption(
                        Icons.Outlined.Help,
                        "Help Center",
                        Color.Red) {
                        navController.navigate(Routes.HelpCenter) }

                    ProfileOption(
                        Icons.Outlined.Info,
                        "About", Color.Red
                    ) { navController.navigate(Routes.About)
                    }

                    ProfileOption(
                        Icons.Outlined.Description,
                        "Privacy & Policy",
                        Color.Red
                    ) { navController.navigate(Routes.PrivacyPolicy)}

                    ProfileOption(
                        Icons.Outlined.Report,
                        "Report a problem",
                        Color.Red
                    ) { navController.navigate(Routes.Report)}

                    ProfileOption(
                        Icons.Outlined.Settings,
                        "Settings", Color.Red

                    ) {navController.navigate(Routes.Settings)
                    }


                    ProfileOption(Icons.Outlined.ExitToApp, "Logout", Color.Red) {
                        /* Handle Logout */
                    }
            }
        }

    }}
