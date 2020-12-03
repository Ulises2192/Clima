package com.ulisesdiaz.clima

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.ulisesdiaz.clima.models.Ciudad
import com.ulisesdiaz.clima.models.WeatherMap
import com.ulisesdiaz.clima.utils.NetWork

class MainActivity : AppCompatActivity() {

    var txtCiudad: TextView? = null
    var txtGrados: TextView? =  null
    var txtEstatus: TextView? = null

    var TAG:String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtCiudad = findViewById<TextView>(R.id.txtCiudad)
        txtGrados = findViewById<TextView>(R.id.txtGrados)
        txtEstatus = findViewById<TextView>(R.id.txtEstatus)
        val ciudad = intent.getStringExtra("com.platasdiaz.clima.ciudades.CIUDAD")


        /*
         Ejemplo de asignacion de informacion a traves de Modelos de manera estatica
         Se obtiene los datos a traves de Intent el cual proviene de Ciudades.Ky
         */
        /*
        val ciudadMX = Ciudad("Ciudad de México", 15, "Soleado")
        val ciudadBerlin = Ciudad("Ciudad de Berlin", 30, "Cielo despejado")
        val ciudadShanghai = Ciudad("Ciudad de Shanghai", 13, "Lluvia")


        if (ciudad == "ciudad-mexico"){
            txtCiudad?.text = ciudadMX.nombre;
            txtGrados?.text = ciudadMX.grados.toString() + "°";
            txtEstatus?.text = ciudadMX.estatus
        }else if (ciudad == "ciudad-berlin"){
            txtCiudad?.text = ciudadBerlin.nombre;
            txtGrados?.text = ciudadBerlin.grados.toString() + "°";
            txtEstatus?.text = ciudadBerlin.estatus
        }else if(ciudad == "ciudad-shanghai"){
            txtCiudad?.text = ciudadShanghai.nombre;
            txtGrados?.text = ciudadShanghai.grados.toString() + "°";
            txtEstatus?.text = ciudadShanghai.estatus
        }else{
            Toast.makeText(this, "NO se encontro la ciudad", Toast.LENGTH_LONG)
        }*/

        /*
          Integrando urel con token de API para WheatherMap para consultar informacion dinamica
          a traves de una solcitud http
         */
        if (NetWork.isNetwork(this)){
            // Ejecutar codigo http
            requestHttpVolley("http://api.openweathermap.org/data/2.5/weather?id=" + ciudad
                    +"&appid=434c7afe4cc095e201350d4143652345&units=metric&lang=es")
        }else{
            // Mostrar mensaje de error
            Toast.makeText(this, "No hay conexion a internet", Toast.LENGTH_SHORT)
        }
    }

    /**
     * Funcion que rescibe el string ded una url para realizar la peticion usando la libreria de Volley
     */
    private fun requestHttpVolley(url:String){
        val queue = Volley.newRequestQueue(this)

        val request = StringRequest(Request.Method.GET, url, Response.Listener<String>{
            response ->
            try{
                Log.d(TAG, response)
                val gson = Gson()
                val country = gson.fromJson(response, WeatherMap::class.java)
                txtCiudad?.text = country.name;
                txtGrados?.text = country.main?.temp.toString() + "°";
                txtEstatus?.text = country.weather?.get(0)?.description
            }catch (e:Exception){
                Log.e(TAG, e.message.toString())
            }
        }, Response.ErrorListener{
            error->
            Log.e(TAG, error.message.toString())
        })

        queue.add(request)
    }
}