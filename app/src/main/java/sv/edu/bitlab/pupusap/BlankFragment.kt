package sv.edu.bitlab.pupusap


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.w3c.dom.Text
import androidx.appcompat.app.AppCompatActivity



class BlankFragment : Fragment() {

  var text : Text? = null


  override fun onCreateView(

  inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {

      return inflater.inflate(R.layout.fragment_detalle_orden, container)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

  }
}


