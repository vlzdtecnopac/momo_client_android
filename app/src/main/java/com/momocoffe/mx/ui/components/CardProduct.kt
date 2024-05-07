package com.momocoffe.mx.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.momocoffe.mx.R
import com.momocoffe.mx.ui.theme.BlueDark
import com.momocoffe.mx.ui.theme.redhatFamily
import com.momocoffe.mx.ui.theme.stacionFamily

@Composable
fun CardProduct() {
    Column(
        modifier = Modifier.padding(5.dp)
    ){

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://lh3.googleusercontent.com/3bwSMf2jd8omjWNYsrUKcvyqduZfJnrkQnEfovtjjlXwXguqpu7CFcGNy59xEuIc0uCnAj5tuQ1J96996zTLy4PgtfIGwjivEQ")
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.no_found),
            contentDescription = stringResource(R.string.momo_coffe),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.clip(shape = RoundedCornerShape(
                topStart = 12.dp,
                topEnd = 12.dp
            ))
        )
        Box(
            modifier =  Modifier.background(BlueDark).fillMaxWidth().padding(top= 10.dp, bottom = 10.dp),
            contentAlignment = Alignment.Center
        ){
            Text("\$ 31", color = Color.White, fontFamily = stacionFamily)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text("Macadamia Black Tea Soda.", color = BlueDark, fontFamily = redhatFamily, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
    }


}