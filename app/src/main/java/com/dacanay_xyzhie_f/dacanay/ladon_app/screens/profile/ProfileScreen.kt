package com.dacanay_xyzhie_f.dacanay.ladon_app.screens.profile

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import base64ToImageBitmap
import com.dacanay_xyzhie_f.dacanay.ladon_app.R
import com.dacanay_xyzhie_f.dacanay.ladon_app.core.reusable.NavBar
import com.dacanay_xyzhie_f.dacanay.ladon_app.core.reusable.ProfileOption
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.storage.TokenManager
import com.dacanay_xyzhie_f.dacanay.ladon_app.navigation.Routes
import com.dacanay_xyzhie_f.dacanay.ladon_app.presentation.auth.AuthViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController,
    tokenManager: TokenManager,
    authViewModel: AuthViewModel = viewModel()
) {
    val context = LocalContext.current
    val profileImage by rememberUpdatedState(newValue = authViewModel.profileImageBase64)
    val imageBitmap = remember(profileImage) {
        profileImage?.let {
            base64ToImageBitmap(context, it)
        }
    }


    Scaffold(
        bottomBar = { NavBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE6F8FF))
                .padding(paddingValues)
                .padding(horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.primary),
                    contentDescription = "App Logo",
                    modifier = Modifier.size(100.dp)
                )

                Row {
                    IconButton(onClick = {
                        navController.navigate(Routes.AddtoCartScreen)
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.ShoppingCart,
                            contentDescription = "Cart",
                            modifier = Modifier.size(32.dp),
                            tint = Color.Black
                        )
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    IconButton(onClick = { /* TODO: Navigate to Notifications */ }) {
                        Icon(
                            Icons.Outlined.Notifications,
                            contentDescription = "Notifications",
                            modifier = Modifier.size(32.dp),
                            tint = Color.Black
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Profile Info
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(contentAlignment = Alignment.Center) {
                        Box(
                            modifier = Modifier
                                .size(130.dp)
                                .clip(CircleShape)
                                .background(Color.LightGray),
                            contentAlignment = Alignment.Center
                        ) {
                            if (imageBitmap != null) {
                                Image(
                                    bitmap = imageBitmap,
                                    contentDescription = "Profile Image",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Crop
                                )
                            } else {
                                Icon(
                                    Icons.Default.Person,
                                    contentDescription = "Profile",
                                    modifier = Modifier.size(80.dp)
                                )
                            }

                        }

                        IconButton(
                            onClick = { navController.navigate(Routes.EditProfileScreen) },
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
                    Text(
                        text = authViewModel.loggedInUserName,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Options Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
                    .padding(24.dp)
            ) {
                ProfileOption(Icons.Outlined.Help, "Help Center", Color(0xFF35AEFF)) {
                    navController.navigate(Routes.HelpCenter)
                }
                Spacer(modifier = Modifier.height(8.dp))
                ProfileOption(Icons.Outlined.Info, "About", Color(0xFF35AEFF)) {
                    navController.navigate(Routes.About)
                }
                Spacer(modifier = Modifier.height(8.dp))
                ProfileOption(Icons.Outlined.Description, "Privacy & Policy", Color(0xFF35AEFF)) {
                    navController.navigate(Routes.PrivacyPolicy)
                }
                Spacer(modifier = Modifier.height(8.dp))
                ProfileOption(Icons.Outlined.Report, "Report a problem", Color(0xFF35AEFF)) {
                    navController.navigate(Routes.Report)
                }



                Spacer(modifier = Modifier.height(8.dp))

                if (authViewModel.isLoggedIn) {
                    ProfileOption(Icons.Outlined.ExitToApp, "Log out", Color.Red) {
                        authViewModel.logout(tokenManager) {
                            navController.navigate(Routes.LogIn) {
                                popUpTo(0)
                            }
                        }
                    }
                } else {
                    ProfileOption(Icons.Outlined.ExitToApp, "Log in", Color.Red) {
                        navController.navigate(Routes.LogIn)
                    }
                }
            }
        }
    }

    // Auto-load user if token exists
    LaunchedEffect(Unit) {
        authViewModel.loadUserFromToken(tokenManager)
    }
}
