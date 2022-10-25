package com.example.r_a.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.r_a.R
import com.example.r_a.model.MainActivityModel

data class name(val namee:String)
val nombress= mutableStateListOf<name>()
class MainActivity : ComponentActivity() {

    val objeto_view_model:MainActivityModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
          Column(
              modifier = Modifier
                  .fillMaxWidth()
                  .padding(top = 5.dp),

              horizontalAlignment = Alignment.CenterHorizontally
          )
          {
              var text = remember { mutableStateOf("") }
              Text(text = "Cotizador de Autos Nuevos",fontWeight = FontWeight.Bold)
              Spacer(modifier = Modifier.padding(5.dp))
              imagen()
              //Nombre
              var nombre = remember { mutableStateOf("") }
              var namee by remember { mutableStateOf("") }
              Row(modifier = Modifier
                  .fillMaxWidth()
                  .padding(0.dp),
                  horizontalArrangement = Arrangement.Center
              ){
                  //Get data
                  Text(text = "Nombre")
                  Spacer(modifier = Modifier.padding(10.dp))
                  TextField(
                      value = namee,
                      onValueChange = {
                          namee = it
                          objeto_view_model.addnombre(namee)            },
                      modifier= Modifier
                          .fillMaxWidth()
                          .padding(5.dp)
                  )
                  LazyColumn() {
                      items (nombress) { most->
                          Mostra(most)
                      }
                  }
              }
              //Marca
              Row(modifier = Modifier
                  .fillMaxWidth(),
                  horizontalArrangement = Arrangement.Center
              ){
                  //Get data
                  Column(
                      modifier = Modifier
                          .padding(5.dp),
                      horizontalAlignment = Alignment.CenterHorizontally,
                      verticalArrangement = Arrangement.Center
                  ) {
                      Text(text = "Marca")
                  }
                  Spacer(modifier = Modifier.padding(10.dp))
                  generarSpinner(objeto_view_model,text.value.toString() )
              }

              //Enganche
              var enganche= remember { mutableStateOf("") }
              Row(modifier = Modifier
                  .fillMaxWidth(),
                  horizontalArrangement = Arrangement.Center
              ){
                  //Get data
                  Column(
                      modifier = Modifier
                          .padding(5.dp),
                      horizontalAlignment = Alignment.CenterHorizontally,
                      verticalArrangement = Arrangement.Center
                  ) {

                      Text(text = "Enganche")
                  }
                  Spacer(modifier = Modifier.padding(5.dp))
                  generarSpinner2(objeto_view_model,text.value.toString() )
              }

              //Financiamiento
              Row(modifier = Modifier
                  .fillMaxWidth(),
                  horizontalArrangement = Arrangement.Center
              ){
                  //Get data
                  Column(
                      modifier = Modifier
                          .padding(5.dp),
                      horizontalAlignment = Alignment.CenterHorizontally,
                      verticalArrangement = Arrangement.Center
                  ) {
                      Text(text = "Financiamiento")
                      Text(text = "(anual)")
                  }
                  Spacer(modifier = Modifier.padding(5.dp))
                  generarSpinner3(objeto_view_model,text.value.toString())

              }
              //Button
              Row(modifier = Modifier
                  .fillMaxWidth()
                  .padding(5.dp),
                  horizontalArrangement = Arrangement.SpaceBetween
              ){
                  OutlinedButton(onClick = {
                          objeto_view_model.Calcular_enganche()
                          objeto_view_model.calcularMontoAFinanciar()
                          objeto_view_model.calcularInteres()
                          objeto_view_model.calcularMontoaFinanciarMasInteres()
                          objeto_view_model.calcularPagosMensuales()
                          objeto_view_model.calcularSumaTotal()
                  }
                      , border = BorderStroke(
                          width = 3.dp,
                          brush = Brush.horizontalGradient(
                              listOf(
                                  Color(0xFF94133F),
                                  Color(0xFFC79911)
                              )
                          )
                      )
                  ) {
                      Text(text = "Cotizar", color = Color(0xFF2C3568))
                  }
                  OutlinedButton(onClick = {
                      objeto_view_model.limpiar()

                  }
                      , border = BorderStroke(
                          width = 3.dp,
                          brush = Brush.horizontalGradient(
                              listOf(
                                  Color(0xFF94133F),
                                  Color(0xFFC79911)
                              )
                          )
                      )
                  ) {
                      Text(text = "Limpiar", color = Color(0xFF2C3568))
                  }

              }
              //out
              Column(
                  modifier = Modifier
                      .fillMaxWidth()
                      .padding(15.dp),
                  horizontalAlignment = Alignment.Start
              ){
                  Text(text = "Cliente: ${objeto_view_model.nombre.value.toString()}")
                  Text(text = "Vehivulo ${objeto_view_model.marca.value.toString()} $ ${objeto_view_model.precios.value.toString()}")
                  Text(text = "Enganche de ${objeto_view_model.porcentaje.value.toString()}  $ ${objeto_view_model.enganche.value.toString()}")
                  Text(text = "Monto A Financiar: $ ${objeto_view_model.monto.value.toString()}")
                  Text(text = "Financiamiento a ${objeto_view_model.plazo.value.toString()}")
                  Text(text = "Monto de Intereses por ${objeto_view_model.a.value.toString()} años : ${objeto_view_model.interes.value.toString()}")
                  Text(text = "Monto a financiar + interes : $ ${objeto_view_model.monto.value.toString()} + $ ${objeto_view_model.interes.value.toString()} = $ ${objeto_view_model.suma2.value.toString()}")
                  Text(text = "Pagos Mensuales por: $ ${objeto_view_model.pagos.value.toString()}")
                  Text(text = "Costo Total: $${objeto_view_model.total.value.toString()}")
              }
          }
        }
    }
}
@Composable
fun Mostra(most:name) {
    Text(text = most.namee)
    Divider(modifier= Modifier
        .fillMaxWidth()
        .width(4.dp),color= Color.Black)
}
@Composable
fun imagen(){
    Row(
        Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center

    ) {
        Image(painterResource(id = R.drawable.autos),"")

    }
}
@Composable
fun generarSpinner(objeto_view_model: MainActivityModel, texto:String)
{
    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Honda Accord    $678,026.22", "Vw Touareg     $879,266.15","BMW X5       $1,025,366.87","Mazda CX7      $988,641.02")


    Box (
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        contentAlignment = Alignment.TopEnd
       )
    {
        Button(onClick = { expanded = !expanded },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFFF9800)
            ))
        {
            Text ("Seleccione una Opción")
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = null,
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {

                DropdownMenuItem(onClick = {
                    expanded = false
                    objeto_view_model.definirMarca("Honda Accord", 678026.22)
                }) {
                    Text(text = "Honda Accord    $678,026.22")
                }
                DropdownMenuItem(onClick = {
                    expanded = false
                    objeto_view_model.definirMarca("Vw Touareg", 879266.15)
                }) {
                    Text(text = "Vw Touareg     $879,266.15")
                }
                DropdownMenuItem(onClick = {
                    expanded = false

                    objeto_view_model.definirMarca("BMW X5", 1025366.15)
                }) {
                    Text(text ="BMW X5       $1,025,366.87" )
                }
                DropdownMenuItem(onClick = {
                    expanded = false
                    objeto_view_model.definirMarca("Mazda CX7", 988641.02)
                }) {
                    Text(text = "Mazda CX7      $988,641.02")
                }
            }
        }

    }
@Composable
fun generarSpinner2(objeto_view_model: MainActivityModel, texto:String)
{
    var expanded by remember { mutableStateOf(false) }
    var suggestions = listOf("20%", "40%", "60%")


    Box (
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        contentAlignment = Alignment.TopEnd
    )
    {
        Button(onClick = { expanded = !expanded },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF009688)
            ))
        {
            Text ("Seleccione")
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = null,
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {

                DropdownMenuItem(onClick = {
                    expanded = false
                    //action for click
                    objeto_view_model.definirPorcentaje(20)
                }) {
                    Text(text = "20%")
                }
                DropdownMenuItem(onClick = {
                    expanded = false
                    objeto_view_model.definirPorcentaje(40)
                }) {
                    Text("40%")
                }
                DropdownMenuItem(onClick = {
                    expanded = false
                    objeto_view_model.definirPorcentaje(60)
                }) {
                    Text("60%")
                }
            }
        }
    }

@Composable
fun generarSpinner3(objeto_view_model: MainActivityModel, texto:String)
{
    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("1 Año 7.5%", "2 Años 9.5%", "3 Años 10.3%", "4 Años 12.6", "5 Años 13.5%")


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        contentAlignment = Alignment.TopEnd
    )
    {
        Button(onClick = { expanded = !expanded },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFDA5E8F)
            )){
            Text ("Seleccione Plazo")
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = null,
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {

                DropdownMenuItem(onClick = {
                    expanded = false
                    objeto_view_model.financiamiento("1 Año 7.5%",1, 7.5)
                }) {
                    Text(text = "1 Año 7.5%")
                }
                DropdownMenuItem(onClick = {
                    expanded = false
                    objeto_view_model.financiamiento("2 Años 9.5%", 2, 9.5)
                }) {
                    Text(text = "2 Años 9.5%")
                }
                DropdownMenuItem(onClick = {
                    expanded = false
                    objeto_view_model.financiamiento("3 Años 10.3%", 3, 10.3)

                }) {
                    Text(text ="3 Años 10.3%" )
                }
                DropdownMenuItem(onClick = {
                    expanded = false
                    objeto_view_model.financiamiento("4 Años 12.6", 4, 12.6)
                }) {
                    Text(text = "4 Años 12.6")
                }
                DropdownMenuItem(onClick = {
                    expanded = false
                    objeto_view_model.financiamiento("5 Años 13.5%", 5, 13.5)
                }) {
                    Text(text = "5 Años 13.5%")
                }
            }
        }
    }


