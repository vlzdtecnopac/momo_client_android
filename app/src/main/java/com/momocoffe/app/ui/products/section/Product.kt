package com.momocoffe.app.ui.products.section

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.momocoffe.app.ui.components.Category
import com.momocoffe.app.ui.components.Header
import com.momocoffe.app.ui.products.components.DescriptionProduct
import com.momocoffe.app.ui.theme.BlueDark

@Preview(widthDp = 1440, heightDp = 800)
@Composable
fun Product() {
    val navController = rememberNavController()
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(navController)
        Box(
            modifier = Modifier.background(BlueDark).fillMaxWidth(),
            contentAlignment = Alignment.Center
            ) {
            Spacer(modifier = Modifier.height(8.dp))
            Category(navController = navController)

        }
        Box(
            modifier = Modifier
                .widthIn(0.dp, 900.dp)
        ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Row(modifier = Modifier.padding(10.dp)) {
                        Column(
                            modifier = Modifier
                                .weight(.3f)
                                .padding(10.dp),
                            verticalArrangement = Arrangement.Top
                        ) {
                            DescriptionProduct()
                        }
                        Column(
                            modifier = Modifier
                                .weight(.8f)
                                .fillMaxHeight()
                                .clip(RoundedCornerShape(14.dp))
                                .background(BlueDark)
                                .border(
                                    width = 1.2.dp,
                                    color = BlueDark,
                                    shape = RoundedCornerShape(14.dp)
                                )
                                .padding(10.dp),
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.Start
                        ) {
                            OptionsModifier()
                        }

                    }

            }
        }
    }

}