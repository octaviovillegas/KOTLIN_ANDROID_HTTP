package com.example.comunicacionhttp_istic

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Button
import android.widget.Toast
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        val btnVerificar=findViewById<Button>(  R.id.btnVerificar)
        val btnConsultaSimple=findViewById<Button>(  R.id.btnConsultaSimple)
        val btnIrListado=findViewById<Button>(  R.id.btnIrListado)
        btnIrListado.setOnClickListener{
            val intento1 = Intent(this, ListadoPaises::class.java)
            startActivity(intento1)
        }



        btnVerificar.setOnClickListener{
            if (ControlDeConexion.hayConexion(this))
            {
                Toast.makeText(this,"Estamos conectados",Toast.LENGTH_SHORT).show()

            }else
            {
                Toast.makeText(this,"Sin Conexión",Toast.LENGTH_SHORT).show()
            }
        }
        btnConsultaSimple.setOnClickListener{
            val datos=consultarDatos("https://api.mocki.io/v1/0b4979ea")
           // http://jsonviewer.stack.hu/
           /*
           https://restcountries.eu/rest/v2/
           https://restcountries.eu/rest/v2/region/europe
           https://restcountries.eu/rest/v2/name/aruba?fullText=true*/
            Log.d("ConsultaSimple" ,datos)

            val datosArrayJson= JSONArray(datos)

            for ( i in 0..datosArrayJson.length()-1)
            {
                var pais= datosArrayJson.getJSONObject(i)
               // Log.d(pais.getString("name") ,pais.toString(0) )
                Log.d("pais", pais.getString("first_name") )
            }

        }

    }
/*
* TESTIN API REST
* https://www.mockaroo.com/ GENERAR DATOS
* https://mocki.io/fake-json-api API REST FAKE
* https://api.mocki.io/v1/0b4979ea
* */
    override fun onStart() {
        super.onStart()
        if (ControlDeConexion.hayConexion(this))
        {
            Toast.makeText(this,"Estamos conectados",Toast.LENGTH_SHORT).show()

        }else
        {
            Toast.makeText(this,"Sin Conexión",Toast.LENGTH_SHORT).show()
        }
    }


    @Throws(IOException::class)
    private fun consultarDatos(url:String):String{
        val policy=StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var datosDescargados: InputStream?=null
        try{
            val direccionWEB= URL(url)
            val conexion=direccionWEB.openConnection() as HttpURLConnection
            conexion.requestMethod="GET"
            conexion.connect()
            datosDescargados=conexion.inputStream
            return datosDescargados.bufferedReader().use{
                it.readText()
            }
        }catch (e:IOException)
        {
            Toast.makeText(this,"${e.message}",Toast.LENGTH_SHORT).show()
        }
        finally {
            if(datosDescargados!=null)
            {
                datosDescargados.close()
            }
        }
        return "NADA"
    }
}
