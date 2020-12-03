package com.ulisesdiaz.clima

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Ciudades : AppCompatActivity() {

    val TAG = "com.platasdiaz.clima.ciudades.CIUDAD"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciudades)

        val btnMexico = findViewById<Button>(R.id.btnMexico);
        val btnBerlin = findViewById<Button>(R.id.btnBerlin);
        val btnShanghai = findViewById<Button>(R.id.btnShanghai)

        var intent = Intent()
        btnMexico.setOnClickListener(View.OnClickListener {
            intent = Intent(this, MainActivity::class.java)
            intent.putExtra(TAG, "3530597") // Cambiar el value a ciudad-mexico para forma estatica
            startActivity(intent)
        })

        btnBerlin.setOnClickListener(View.OnClickListener {
            intent = Intent(this, MainActivity::class.java)
            intent.putExtra(TAG, "2950159") // Cambiar el value a ciudad-berlin para forma estatica
            startActivity(intent)
        })

        btnShanghai.setOnClickListener({
            intent = Intent(this, MainActivity::class.java)
            intent.putExtra(TAG,"1796236") // cambiar el value a ciudad-shanghai para forma estatica
            startActivity(intent)
        })
    }
}