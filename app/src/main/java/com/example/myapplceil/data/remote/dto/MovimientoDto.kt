package com.example.myapplceil.data.remote.dto

import com.example.myapplceil.ApartmentExpense

data class MovimientoDto(
    val id_movimiento: Int,
    val concepto: String,     // Este será el "name" en tu UI
    val monto: Double,        // Este será el "amount" en tu UI
    val categoria_nombre: String // Podrías usar esto como el "emoji" temporalmente
)

// Función de extensión para convertir de DTO a Modelo de Dominio
fun MovimientoDto.toDomain() = ApartmentExpense(
    id = id_movimiento,
    name = concepto,
    amount = monto,
    emoji = "💰" // Puedes poner lógica aquí para asignar un emoji según la categoría
)
