package sv.edu.bitlab.pupusap


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.text.DecimalFormat
import sv.edu.bitlab.pupusap.DetalleOrdeActivity.*

class FragmentDetalleOrden : Fragment() {

  var arroz = arrayListOf<Int>()
  var maiz = arrayListOf<Int>()

  val lineItemsIDs = arrayOf(
    arrayOf(R.id.lineItemDetail1, R.id.lineItemPrice1),
    arrayOf(R.id.lineItemDetail2, R.id.lineItemPrice2),
    arrayOf(R.id.lineItemDetail3, R.id.lineItemPrice3),
    arrayOf(R.id.lineItemDetail4, R.id.lineItemPrice4),
    arrayOf(R.id.lineItemDetail5, R.id.lineItemPrice5),
    arrayOf(R.id.lineItemDetail6, R.id.lineItemPrice6)
  )

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

    val params = activity!!.intent.extras
    arroz = params!!.getIntegerArrayList(CONTADOR_ARROZ)!!
    maiz = params!!.getIntegerArrayList(CONTADOR_MAIZ)!!
    displayDetalle()

    return inflater.inflate(R.layout.fragment_detalle_orden, container, false)

  }

  fun displayDetalle() {
    val arr = arroz + maiz
    var total = 0.0f
    for((index, contador) in arr.withIndex()){
      val ids = lineItemsIDs[index]
      val detailTexview = activity!!.findViewById<TextView>(ids[0])
      val priceTextView= activity!!.findViewById<TextView>(ids[1])
      if(contador > 0){
        val totalUnidad = contador * DetalleOrdeActivity.VALOR_PUPUSA
        val descripcion = getDescripcion(index)
        detailTexview.text = getString(R.string.pupusa_line_item_description,
          contador, descripcion)
        total += totalUnidad
        val precio = DecimalFormat("$#0.00").format(totalUnidad)
        priceTextView.text = precio
      } else{
        detailTexview.visibility = View.GONE
        priceTextView.visibility = View.GONE
      }
    }
    val totalPrecio = activity!!.findViewById<TextView>(R.id.lineItemPriceTotal)
    val precio = DecimalFormat("$#0.00").format(total)
    totalPrecio.text = precio
  }

  fun getDescripcion(index: Int): String {
    return when(index){
      DetalleOrdeActivity.QUESO -> "Queso de arroz"
      DetalleOrdeActivity.FRIJOLES -> "Frijol con queso de arroz"
      DetalleOrdeActivity.REVUELTAS -> "Revueltas de arroz"
      DetalleOrdeActivity.QUESO_MAIZ -> "Queso de maiz"
      DetalleOrdeActivity.FRIJOLES_MAIZ -> "Frijol con queso de maiz"
      DetalleOrdeActivity.REVUELTAS_MAIZ -> "Revueltas de maiz"
      else -> throw RuntimeException("Pupusa no soportada")
    }
  }

  companion object{
    const val QUESO = 0//3
    const val FRIJOLES = 1//4
    const val REVUELTAS = 2//5
    const val QUESO_MAIZ = 3//3
    const val FRIJOLES_MAIZ = 4//4
    const val REVUELTAS_MAIZ = 5//5
    const val CONTADOR_ARROZ = "ARROZ"
    const val CONTADOR_MAIZ = "MAIZ"
    const val VALOR_PUPUSA = 0.5F
    const val FRAGMENT_TAG = "FRAGMENT_TAG"
  }


}
