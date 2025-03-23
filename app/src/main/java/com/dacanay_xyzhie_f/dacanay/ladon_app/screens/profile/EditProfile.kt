package com.dacanay_xyzhie_f.dacanay.ladon_app.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.dacanay_xyzhie_f.dacanay.ladon_app.R
import com.dacanay_xyzhie_f.dacanay.ladon_app.core.reusable.EmailEdit
import com.dacanay_xyzhie_f.dacanay.ladon_app.core.reusable.LabelText
import com.dacanay_xyzhie_f.dacanay.ladon_app.core.reusable.PassFields
import com.dacanay_xyzhie_f.dacanay.ladon_app.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfile(navController: NavHostController) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var contact by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Edit Profile",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFE6F8FF))
            )
        },
        containerColor = Color(0xFFE6F8FF)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 24.dp)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(20.dp))
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                // Profile Image with Edit Icon
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
                            imageVector = Icons.Outlined.CameraAlt,
                            contentDescription = "Edit Profile",
                            tint = Color.White,
                            modifier = Modifier
                                .background(Color(0xFF35AEFF), shape = CircleShape)
                                .padding(4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Username
                LabelText(value = stringResource(id = R.string.username))
                Spacer(modifier = Modifier.height(5.dp))
                EmailEdit(
                    labelValue = stringResource(id = R.string.usernameInt),
                    painterResource = painterResource(id = R.drawable.user),
                    value = username,
                    onValueChange = { username = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Email
                LabelText(value = stringResource(id = R.string.email))
                Spacer(modifier = Modifier.height(5.dp))
                EmailEdit(
                    labelValue = stringResource(id = R.string.edemail),
                    painterResource = painterResource(id = R.drawable.envelope),
                    value = email,
                    onValueChange = { email = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Contact
                LabelText(value = stringResource(id = R.string.contact))
                Spacer(modifier = Modifier.height(5.dp))
                EmailEdit(
                    labelValue = stringResource(id = R.string.edecontact),
                    painterResource = painterResource(id = R.drawable.contact),
                    value = contact,
                    onValueChange = { contact = it }
                )






                }

                Button(
                    onClick = {
                        // Save logic using username, email, contact
                    },
                    modifier = Modifier.fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF35AEFF))
                ) {
                    Text(
                        text = "SAVE",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

            }

            }
        }
    }

    }

