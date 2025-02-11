package com.dacanay_xyzhie_f.dacanay.ladon_app.screens.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dacanay_xyzhie_f.dacanay.ladon_app.R
import com.dacanay_xyzhie_f.dacanay.ladon_app.core.reusable.DescriptionText
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import com.dacanay_xyzhie_f.dacanay.ladon_app.core.reusable.HeadingText
import com.dacanay_xyzhie_f.dacanay.ladon_app.core.reusable.InputFields
import com.dacanay_xyzhie_f.dacanay.ladon_app.core.reusable.LabelText
import com.dacanay_xyzhie_f.dacanay.ladon_app.core.reusable.PassFields
import com.dacanay_xyzhie_f.dacanay.ladon_app.core.reusable.RememberComp
import com.dacanay_xyzhie_f.dacanay.ladon_app.navigation.Routes


@Composable
fun LoginScreen(navController: NavHostController) {


    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 32.dp, start = 10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start


        ) {


            IconButton(
                onClick = { navController.navigate(Routes.LogSign) }
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_arrow_back_24),
                    contentDescription = "Arrow Back image Button"
                )
            }








            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 140.dp, horizontal = 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                HeadingText(value = stringResource(id = R.string.welcome))
                DescriptionText(value = stringResource(id = R.string.description_text))

                //email
                LabelText(value = stringResource(id = R.string.email))
                Spacer(modifier = Modifier.height(10.dp))
                InputFields(
                    labelValue = stringResource(id = R.string.emailInt),
                    painterResource(id = R.drawable.envelope)
                )

                Spacer(modifier = Modifier.height(20.dp))

                //password
                LabelText(value = stringResource(id = R.string.password))
                Spacer(modifier = Modifier.height(10.dp))
                PassFields(
                    labelValue = stringResource(id = R.string.passwordInt),

                    painterResource(id = R.drawable.lock_line_icon)
                )


                //remember me
                RememberComp(value = stringResource(id = R.string.rememberme))
            }
        }
    }}


















