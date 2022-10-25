package com.example.r_a.model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainActivityModel :ViewModel(){
    private val _nombre= mutableStateOf("")
    val nombre: State <String> =_nombre
    private val _marca= mutableStateOf("")
    val marca: State <String> =_marca
    private val _enganche= mutableStateOf(0.0)
    val enganche:State<Double> =_enganche
    private val _porcentaje= mutableStateOf(0)
    val porcentaje:State<Int> =_porcentaje
    private val _porcentajePlazo= mutableStateOf(0.0)
    val porcentajePlazo:State<Double> =_porcentajePlazo
    private val _interes= mutableStateOf(0.0)
    val interes:State<Double> =_interes
    private val _precios= mutableStateOf(0.0)
    val precios:State<Double> =_precios
    private val _financiamiento= mutableStateOf(0)
    val financiamiento:State<Int> =_financiamiento
    private val _a= mutableStateOf(0)
    val a:State<Int> =_a
    private val _plazo= mutableStateOf("")
    val plazo:State<String> =_plazo
    private val _monto= mutableStateOf(0.0)
    val monto:State<Double> =_monto
    private val _suma2= mutableStateOf(0.0)
    val suma2:State<Double> =_suma2
    private val _pagos= mutableStateOf(0.0)
    val pagos:State<Double> =_pagos
    private val _total= mutableStateOf(0.0)
    val total:State<Double> =_total


    fun nombre(nombre:String)
    {
        _nombre.value=nombre

    }
    fun calcularMontoaFinanciarMasInteres(){
        _suma2.value = _monto.value + _interes.value
    }
    fun Calcular_enganche()
    {
        _enganche.value= _porcentaje.value * _precios.value / 100

    }
    fun calcularInteres(){
        _interes.value =  _monto.value * _porcentajePlazo.value / 100 * _a.value
    }
    fun financiamiento(plazo:String, año:Int, porcentaje: Double)
    {
        _plazo.value=plazo
        _a.value=año
        _porcentajePlazo.value = porcentaje
    }
    fun definirMarca(nombreMarca:String,precio: Double)
    {
        _marca.value= nombreMarca
        _precios.value=precio

    }
    fun definirPorcentaje(tipo:Int)
    {
        _porcentaje.value=tipo


    }
    fun calcularSumaTotal(){
        _total.value = _suma2.value + _enganche.value
    }
    fun calcularPagosMensuales(){
        _pagos.value = _suma2.value / (_a.value * 12)
    }
    fun calcularMontoAFinanciar(){
        _monto.value = _precios.value - _enganche.value
    }
    fun addnombre(nombre:String){
        _nombre.value = nombre
    }
    fun limpiar()
    {
        _enganche.value = 0.0
        _marca.value = ""
        _pagos.value = 0.0
        _precios.value = 0.0
        _porcentaje.value = 0
        _plazo.value = ""
        _a.value = 0
        _total.value = 0.0
        _nombre.value = ""
        _interes.value = 0.0
        _porcentajePlazo.value = 0.0

    }


}


