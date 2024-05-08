package com.momocoffe.mx.ui.components.cart

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.gson.Gson
import com.momocoffe.mx.R
import com.momocoffe.mx.ui.theme.BlueDark
import com.momocoffe.mx.ui.theme.GrayLight
import com.momocoffe.mx.ui.theme.OrangeDark
import com.momocoffe.mx.ui.theme.redhatFamily
import com.momocoffe.mx.ui.theme.stacionFamily

data class Coffee(val name: String, val price: Int)

@Preview(widthDp = 330, heightDp = 700, showBackground = true)
@Composable
fun ContentCart() {
    val list = listOf(
        "A", "B", "C", "D"
    ) + ((0..100).map { it.toString() })

    Column(
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier
                    .weight(0.8f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "Resumen de tu pedido",
                    color = Color.Black,
                    fontFamily = redhatFamily,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700)
                )
                Text(
                    "2 productos",
                    color = Color.Black,
                    fontFamily = redhatFamily,
                    fontSize = 12.sp
                )
            }
            Column(
                modifier = Modifier
                    .weight(0.2f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                IconButton(
                    modifier = Modifier
                        .width(32.dp)
                        .height(32.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .background(OrangeDark)
                        .border(
                            width = 0.6.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(50.dp)
                        )
                        .padding(4.dp),
                    onClick = {}) {
                    Icon(
                        Icons.Rounded.Close,
                        contentDescription = stringResource(id = R.string.momo_coffe),
                        tint = Color.White,
                        modifier = Modifier.size(20.dp),
                    )
                };
            }

        }

        LazyColumn(modifier = Modifier.fillMaxHeight(0.8f)) {
            items(items = list, itemContent = { item -> ProductCart() })
        }
        TotalPayment()
    }
}

@Composable
fun ProductCart() {
    val optionsSize = listOf("Chico", "Regular", "Menos azúcar", "Sin tapa")
    val json = """
        [
            {"name": "Extra de café", "price": 10},
            {"name": "Another Coffee", "price": 15}
        ]
    """.trimIndent()

    val coffees = Gson().fromJson(json, Array<Coffee>::class.java)

    Column{
        Row(modifier = Modifier.padding(8.dp)) {
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
                        .width(70.dp)
                        .height(70.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BtnCart(
                        onClickButton = {},
                        icon = R.drawable.menus_icon,
                        color = Color.White,
                        iconColor = OrangeDark,
                        borderColor = OrangeDark
                    )
                    Text(
                        "1",
                        modifier = Modifier.width(22.dp),
                        textAlign = TextAlign.Center,
                        fontFamily = stacionFamily
                    )
                    BtnCart(
                        onClickButton = {},
                        icon = R.drawable.pluss_icon,
                        color = OrangeDark,
                        iconColor = Color.White,
                        borderColor = OrangeDark
                    )
                }
            }
            Column(modifier = Modifier.padding(start = 5.dp)) {

                Text(
                    "Macadamia Black Tea Soda",
                    fontFamily = redhatFamily,
                    fontSize = 14.sp,
                    fontWeight = FontWeight(700)
                )
                Text(
                    optionsSize.joinToString(" | "),
                    fontFamily = redhatFamily,
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400),
                    color = BlueDark
                )
                Spacer(modifier = Modifier.height(8.dp))
                coffees.forEach { coffee ->
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp)
                    ) {
                        Text(
                            "${coffee.name}",
                            modifier = Modifier.weight(0.6f),
                            fontFamily = redhatFamily,
                            fontSize = 12.sp
                        )
                        Text(
                            "${coffee.price}",
                            modifier = Modifier.weight(0.4f),
                            fontFamily = redhatFamily,
                            fontSize = 12.sp
                        )
                    }
                }

            }
        }
        Row(
            modifier = Modifier.padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(modifier = Modifier.weight(0.8f),
                contentAlignment = Alignment.CenterEnd){
                Text("\$ 67.00", fontSize = 20.sp,  fontFamily = stacionFamily)
            }
            Box(modifier = Modifier.weight(0.2f),
                contentAlignment = Alignment.Center){
                BtnCart(
                    onClickButton = {},
                    icon = R.drawable.trash_icon,
                    color = Color.White,
                    iconColor = OrangeDark,
                    borderColor = OrangeDark
                )
            }
        }

    }

}


@Composable
fun TotalPayment(){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(modifier = Modifier.fillMaxWidth(0.95f).background(GrayLight).padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
            ){
            Text("Subtotal (1 producto)", fontSize = 18.sp, fontFamily = redhatFamily)
            Spacer(modifier = Modifier.width(8.dp))
            Text("\$ 67.00", fontSize = 18.sp,  fontFamily = stacionFamily)
        }
        Spacer(modifier = Modifier.width(10.dp))
        Button(
            onClick = { /*TODO*/ }) {
            Text("Continuar al pago")
        }
    }

}


