package cl.pandaadolfo.felicidadconperros

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import cl.pandaadolfo.felicidadconperros.api.ApiCallBack
import cl.pandaadolfo.felicidadconperros.api.ApiTask
import com.bumptech.glide.Glide
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity(), ApiCallBack {
    private lateinit var botonGenerar: Button
    private lateinit var texto: TextView
    private lateinit var imagen: ImageView
    private var URL1 : String = "https://random.dog/woof.json"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        botonGenerar = findViewById(R.id.generateButton)
        texto = findViewById(R.id.textoURL)
        imagen = findViewById(R.id.imagenPerro)

        botonGenerar.setOnClickListener {
            val apiRequestTask = ApiTask(this)
            apiRequestTask.execute(URL1)
        }
    }

    override fun OnRequestComplete(result: String) {

        Toast.makeText(this, result, Toast.LENGTH_LONG).show()
        procesar(result)
    }

    fun procesar (result: String){

        try {
            // Parse the JSON string into a JSONObject
            val jsonObject = JSONObject(result)

            // Access values from the JSON object
            val fileSizeBytes = jsonObject.getInt("fileSizeBytes")
            val url = jsonObject.getString("url")

            texto.text = url


            Glide.with(this).load(url).into(imagen)

        } catch  (e: JSONException) {
            e.printStackTrace()
        }
    }
}