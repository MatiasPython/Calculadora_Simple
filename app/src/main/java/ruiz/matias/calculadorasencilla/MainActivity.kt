package ruiz.matias.calculadorasencilla

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat
import kotlin.Double.Companion.NaN



class MainActivity : AppCompatActivity() {
    val SUMA = "+"
    val RESTA = "-"
    val MULTIPLICACIÓN = "*"
    val DIVISIÓN = "/"
    val PORCENTAJE = "%"

    var operacionActual = ""

    var primerNumero: Double = NaN
    var segundoNumero: Double = NaN

    lateinit var  textView1: TextView
    lateinit var  textView2: TextView

    lateinit var formatoDecimal: DecimalFormat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        formatoDecimal = DecimalFormat("#.#######")
        textView1 = findViewById(R.id.textView1)
        textView2 = findViewById(R.id.textView2)
    }
    fun cambiarOperador(b: View) {
        if (textView1.text.isNotEmpty() || primerNumero.toString() != "Nan") {
                calcular()
                val boton: Button = b as Button
                if (boton.text.toString().trim() == "/") {
                    operacionActual = "/"
                } else if (boton.text.toString().trim() == "*") {
                    operacionActual = "*"
                } else {
                    operacionActual = boton.text.toString().trim()
                }
                if(textView1.text.toString().isEmpty()){
                    textView1.text=textView2.text
                }
                textView2.text = formatoDecimal.format(primerNumero) + operacionActual
                textView1.text = ""
        }
    }
        fun calcular(){
            try {
                if(primerNumero.toString() !="NaN"){
                    if(textView1.text.toString().isEmpty()){
                        textView1.text = textView2.text.toString()
                    }
                    segundoNumero = textView1.text.toString().toDouble()
                    textView1.text = ""

                    when(operacionActual){
                        "+"-> primerNumero = (primerNumero + segundoNumero)
                        "-"-> primerNumero = (primerNumero - segundoNumero)
                        "*"-> primerNumero = (primerNumero * segundoNumero)
                        "/"-> primerNumero = (primerNumero / segundoNumero)
                        "%"-> primerNumero = (primerNumero % segundoNumero)
                    }
                }else {
                    primerNumero = textView1.text.toString().toDouble()
                }
            }catch (_: Exception){

            }

        }

        fun seleccionarNumero(b: View){
            val boton:Button = b as Button
            textView1.text = textView1.text.toString() + boton.text.toString()
        }
    fun igual(b: View){
        calcular()
        textView2.text = formatoDecimal.format(primerNumero)
       // primerNumero = Double.NaN
        operacionActual = ""
    }
    fun borrar(b: View) {
        val boton: Button = b as Button
        if (boton.text.toString().trim() == "C") {
            if(textView1.text.toString().isNotEmpty()){
                var datosActuales:CharSequence = textView1.text as CharSequence
                textView1.text =datosActuales.subSequence(0, datosActuales.length-1)
            }else {
                primerNumero = Double.NaN
                segundoNumero = Double.NaN
                textView1.text = ""
                textView2.text = ""
            }
        } else if (boton.text.toString().trim() == "CA") {
            primerNumero = Double.NaN
            segundoNumero = Double.NaN
            textView1.text = ""
            textView2.text = ""
        }
    }
}

