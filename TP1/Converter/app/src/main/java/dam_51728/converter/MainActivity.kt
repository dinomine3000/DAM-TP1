package dam_51728.converter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    val typeOptions = arrayOf("Temperature", "Distance", "Weight", "Volume")
    val tempOptions = arrayOf("Cº", "ºF", "K")
    val distOptions = arrayOf("Meters", "Centimeters", "Kilometers", "Feet", "Miles", "Inches")
    val weightOptions = arrayOf("Grams", "Kilos", "Pounds", "Milligrams", "Ounces")
    val volumeOptions = arrayOf("Cups", "Tea Spoons", "Table Spoons", "Liters", "Milliliters")

    var fromValue: String = ""
    var toValue: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val typeChoiceView: AutoCompleteTextView = findViewById(R.id.type_choice)
        val fromChoiceView: AutoCompleteTextView = findViewById(R.id.from_choice)
        val toChoiceView: AutoCompleteTextView = findViewById(R.id.to_choice)

        val adapterItems: ArrayAdapter<String> = ArrayAdapter(this, R.layout.list_item, typeOptions)
        typeChoiceView.setAdapter(adapterItems)
        typeChoiceView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
            val selected = adapterView.getItemAtPosition(position).toString()
            Toast.makeText(this, "Item: $selected", Toast.LENGTH_SHORT).show()
            val updatedOptions: Array<String> = when(selected){
                "Temperature" -> tempOptions
                "Distance" -> distOptions
                "Weight" -> weightOptions
                else -> volumeOptions
            }
            val adapterItems: ArrayAdapter<String> = ArrayAdapter(this, R.layout.list_item, updatedOptions)
            fromChoiceView.setAdapter(adapterItems)
            toChoiceView.setAdapter(adapterItems)

            val onFromSelected = AdapterView.OnItemClickListener { adapterView, view, position, id ->
                val selected = adapterView.getItemAtPosition(position).toString()
                Toast.makeText(this, "Item: $selected", Toast.LENGTH_SHORT).show()
                fromValue = selected
                updateText()
            }

            val onToSelected = AdapterView.OnItemClickListener { adapterView, view, position, id ->
                val selected = adapterView.getItemAtPosition(position).toString()
                Toast.makeText(this, "Item: $selected", Toast.LENGTH_SHORT).show()
                toValue = selected
                updateText()
            }
            fromChoiceView.onItemClickListener = onFromSelected
            toChoiceView.onItemClickListener = onToSelected
        }

        //text input listerner
        val textInputView: TextInputEditText = findViewById(R.id.value_input)
        textInputView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                updateText()
            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {}
        })
    }

    fun updateText(){
        if(fromValue.isEmpty() || toValue.isEmpty()) return
        val textInput: TextInputEditText = findViewById(R.id.value_input)
        var input: String = textInput.text.toString()
        if(input.isEmpty() || input.isBlank()) input = "0"
        val value = input.toFloatOrNull() ?: return

        val textView: TextView = findViewById(R.id.resultOutput)
        textView.text = getString(R.string.result_is, convertValue(value), toValue)
    }
    fun convertValue(value: Float): Float {
        val fromType: String = fromValue
        val toType: String = toValue

        var res: Float = value
        //convert to base
        res = convertValueToBase(res, fromType)
        //convert from base to result
        res = convertBaseToType(res, toType)
        return res
    }

    //the base values are: milliliter, grams, meters, and kelvin
    fun convertBaseToType(base: Float, type: String): Float{
        return when(type){
            "Cups" -> base / 236.6f
            "Tea Spoons" -> base / 4.9f
            "Table Spoons" -> base / 14.8f
            "Liters" -> base / 1000f
            "Kilos" -> base / 1000f
            "Pounds" -> base / 453.5f
            "Milligrams" -> base * 1000f
            "Ounces" -> base / 28.35f
            "Centimeters" -> base * 100f
            "Kilometers" -> base / 1000f
            "Feet" -> base * 3.281f
            "Miles" -> base / 1609f
            "Inches" -> base * 39.37f
            "Cº" -> base - 273.15f
            "ºF" -> (base - 273.15f) * 9/5f + 32
            else -> base
        }
    }

    //the base values are: milliliter, grams, meters, and kelvin
    fun convertValueToBase(value: Float, type: String): Float{
        return when(type){
            "Cups" -> value * 236.6f
            "Tea Spoons" -> value * 4.9f
            "Table Spoons" -> value * 14.8f
            "Liters" -> value * 1000f
            "Kilos" -> value * 1000f
            "Pounds" -> value * 453.5f
            "Milligrams" -> value / 1000f
            "Ounces" -> value * 28.35f
            "Centimeters" -> value / 100
            "Kilometers" -> value * 1000
            "Feet" -> value / 3.281f
            "Miles" -> value * 1609
            "Inches" -> value / 39.37f
            "Cº" -> value + 273.15f
            "ºF" -> (value - 32) * 5/9f + 273.15f
            else -> value
        }
    }
}