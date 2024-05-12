package com.momocoffe.app.ui.orderhere.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.momocoffe.app.R
import com.momocoffe.app.navigation.Destination
import com.momocoffe.app.ui.theme.BlueDark
import com.momocoffe.app.ui.theme.redhatFamily

@Composable
fun ContentNotEffecty(navController : NavController) {
    Dialog(
        onDismissRequest = {},
        DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Surface(
            modifier = Modifier
                .padding(0.dp)
                .zIndex(88f),
            color = BlueDark ) {
            Column(
                modifier = Modifier
                    .fillMaxSize() // Fills entire Surface
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(4f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text("No efectivo", color = Color.White, fontSize = 26.sp ,fontFamily = redhatFamily)
                    Spacer(modifier = Modifier.height(10.dp))
                    Image(
                        painter = painterResource(id = R.drawable.coffe_momo),
                        contentDescription = stringResource(id = R.string.momo_coffe),
                        modifier = Modifier.width(240.dp)
                    )
                }

                Column(
                    modifier =Modifier
                        .widthIn(0.dp, 852.dp),
                    verticalArrangement = Arrangement.Top
                ){

                    Text("En MOMO Coffee, estamos comprometidos con la mejora continua de nuestros servicios y la seguridad de nuestros clientes y empleados.\n" +
                            "Es por ello que al no manejar efectivo: \n" +
                            "* Mejoramos condiciones de higiene y evitamos contaminación de productos \n" +
                            "* Agilizamos nuestras operaciones para que recibas tu café en pocos minutos \n" +
                            "* Reducimos riesgos asociados con robos a local y a nuestros clientes \n" +
                            "Nos comprometemos a proporcionar una experiencia de compra eficiente, segura y moderna. Para consultas, contáctanos sin dudarlo",
                        color = Color.White, fontSize = 18.sp ,fontFamily = redhatFamily)

                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(4f),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(5f)
                            .padding(20.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Row{
                            ButtonLang(onclick= {}, text= "Español", icon = painterResource(id = R.drawable.mexico_flag))
                            Spacer(modifier = Modifier.width(15.dp))
                            ButtonLang(onclick= {}, text="Ingles", icon = painterResource(id = R.drawable.usa_flag))
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(7f)
                            .padding(2.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ButtonField(
                            text = stringResource(id = R.string.orderhere),
                            onclick = {
                                navController.navigate(Destination.Client.route)
                            },
                            enabled = true
                        )
                    }
                    Column(
                        horizontalAlignment =  Alignment.End,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(5f)
                            .padding(20.dp)
                    ) {
                        ButtonBack(onclick = {
                            navController.navigate(Destination.OrderHere.route)
                        })
                    }
                }

            }
        }
    }

}

