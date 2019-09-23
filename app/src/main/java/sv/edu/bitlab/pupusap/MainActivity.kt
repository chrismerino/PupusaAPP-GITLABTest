package sv.edu.bitlab.pupusap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.FrameLayout
import sv.edu.bitlab.pupusap.DetalleOrdeActivity.Companion.CONTADOR_ARROZ
import sv.edu.bitlab.pupusap.DetalleOrdeActivity.Companion.CONTADOR_MAIZ


class MainActivity : AppCompatActivity() {


    private var fragmentContainer: FrameLayout? = null
    var botonEnviar: Button? = null



    var contadoresMaiz = hashMapOf(
        QUESO to 0,
        FRIJOLES to 0,
        REVUELTAS to 0
    )

    var contadoresArroz = hashMapOf(
        QUESO to 0,
        FRIJOLES to 0,
        REVUELTAS to 0
    )

    val pupusaStringResources = hashMapOf(
        QUESO to R.string.pupusa_queso,
        FRIJOLES to R.string.frijol_con_queso,
        REVUELTAS to R.string.revueltas
    )

    var botonesMaiz = hashMapOf<String, Button>()
    var botonesArroz = hashMapOf<String, Button>()
    var quesoIzquierda: Button? = null
    var frijolIzquierda: Button? = null
    var revueltaIzquierda: Button? = null

    var quesoDerecha: Button? = null
    var frijolDerecha: Button? = null
    var revueltasDerecha: Button? = null







    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        fragmentContainer = findViewById(R.id.contenedor_fragment)

        botonEnviar = findViewById(R.id.botonEnviar)
        botonEnviar!!.setOnClickListener {

            openFragment()

            // confirmarOrden()

        }


        quesoIzquierda = findViewById(R.id.quesoIzquierda)
        frijolIzquierda = findViewById(R.id.frijolIzquierdaMaiz)
        revueltaIzquierda = findViewById(R.id.revueltasIzquierda)

        botonesMaiz= hashMapOf(
            QUESO to quesoIzquierda!!,
            FRIJOLES to frijolIzquierda!!,
            REVUELTAS to revueltaIzquierda!!
        )

        quesoIzquierda!!.setOnClickListener { addMaiz(QUESO) }
        frijolIzquierda!!.setOnClickListener { addMaiz(FRIJOLES) }
        revueltaIzquierda!!.setOnClickListener { addMaiz(REVUELTAS) }


        quesoDerecha = findViewById(R.id.quesoDerecha)
        frijolDerecha = findViewById(R.id.frijolIDerechaArroz)
        revueltasDerecha = findViewById(R.id.revueltasDerecha)

        botonesArroz= hashMapOf(
            QUESO to quesoDerecha!!,
            FRIJOLES to frijolDerecha!!,
            REVUELTAS to revueltasDerecha!!
        )

        quesoDerecha!!.setOnClickListener { addArroz(QUESO) }
        frijolDerecha!!.setOnClickListener { addArroz(FRIJOLES) }
        revueltasDerecha!!.setOnClickListener { addArroz(REVUELTAS) }






        displayCounters()
    }




    fun openFragment(){

        val fragmento = BlankFragment()
        val builder = supportFragmentManager
            .beginTransaction()
            .add(R.id.contenedorFragment, fragmento)
        builder.commit()

//    val fragment = OrdenFragment.newInstance("Hey", "Stranger")
//    val builder = supportFragmentManager.beginTransaction().add(R.id.contenedorFragment, fragment)
//    builder.commit()
  }







    fun displayCounters() {
        for ((key,value) in contadoresMaiz){
            val resource = pupusaStringResources[key]
            val text = this.resources.getString(resource!!, value)
            botonesMaiz[key]!!.text = text
        }


        for ((key,value) in contadoresArroz){
            val resource = pupusaStringResources[key]
            val text = this.resources.getString(resource!!, value)
            botonesArroz[key]!!.text = text
        }

    }

    fun addMaiz(relleno: String) {
        contadoresMaiz[relleno] = contadoresMaiz[relleno]!! + 1
        val contador = contadoresMaiz[relleno]
        val resource = pupusaStringResources[relleno]
        val text = this.resources.getString(resource!!, contador)
        botonesMaiz[relleno]!!.text = text
    }
    fun addArroz(relleno: String) {
        contadoresArroz[relleno] = contadoresArroz[relleno]!! + 1
        val contador = contadoresArroz[relleno]
        val resource = pupusaStringResources[relleno]
        val text = this.resources.getString(resource!!, contador)
        botonesArroz[relleno]!!.text = text
    }

    private fun confirmarOrden() {
        val intent = Intent(this, DetalleOrdeActivity::class.java)
        val arroz = arrayListOf<Int>(
            contadoresArroz[QUESO]!!,
            contadoresArroz[FRIJOLES]!!,
            contadoresArroz[REVUELTAS]!!)
        val maiz = arrayListOf<Int>(
            contadoresMaiz[QUESO]!!,
            contadoresMaiz[FRIJOLES]!!,
            contadoresMaiz[REVUELTAS]!!)


        intent.putExtra(CONTADOR_ARROZ, arroz)
        intent.putExtra(CONTADOR_MAIZ, maiz)

        this.startActivity(intent)
    }



    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)

    }

    companion object{
        const val QUESO = "QUESO"
        const val FRIJOLES = "FRIJOLES"
        const val REVUELTAS = "REVUELTAS"
        const val MAIZ = "MAIZ"
        const val ARROZ = "ARROZ"
    }

}
