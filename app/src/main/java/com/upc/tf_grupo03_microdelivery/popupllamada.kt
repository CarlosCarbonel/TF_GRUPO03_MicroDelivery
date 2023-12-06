package com.upc.tf_grupo03_microdelivery

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupWindow

class popupllamada : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_repartidor)

        val miBoton = findViewById<Button>(R.id.btnpopupllamada)
        miBoton.setOnClickListener {
            mostrarPopup(miBoton)
        }

    }
    private fun mostrarPopup(anchorView: View) {
        val layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = layoutInflater.inflate(R.layout.activity_popupllamada, null)

        val popupWindow = PopupWindow(
            popupView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        // Configura si el popup se cierra al tocar fuera de él
        popupWindow.isFocusable = true
        popupWindow.isOutsideTouchable = true

        // Muestra el PopupWindow
        popupWindow.showAtLocation(anchorView, Gravity.CENTER, 0, 0)
    }
}