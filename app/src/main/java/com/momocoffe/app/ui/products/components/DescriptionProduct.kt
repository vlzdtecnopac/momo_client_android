package com.momocoffe.app.ui.products.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.momocoffe.app.R
import com.momocoffe.app.ui.theme.BlueDark
import com.momocoffe.app.ui.theme.OrangeDark
import com.momocoffe.app.ui.theme.redhatFamily


@Composable
fun DescriptionProduct() {
    Column {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://lh3.googleusercontent.com/3bwSMf2jd8omjWNYsrUKcvyqduZfJnrkQnEfovtjjlXwXguqpu7CFcGNy59xEuIc0uCnAj5tuQ1J96996zTLy4PgtfIGwjivEQ")
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.no_found),
            contentDescription = stringResource(R.string.momo_coffe),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .clip(
                    shape = RoundedCornerShape(8.dp)
                )
                .width(280.dp)
                .height(280.dp)
        )
        Text("Croissant de\n" +
                "almendras", fontSize = 28.sp, fontFamily = redhatFamily, fontWeight = FontWeight(700), color = BlueDark
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text("Bebida de chocolate con macadamia.", fontSize = 22.sp, fontFamily = redhatFamily, fontWeight = FontWeight(400)
        )
    }
}