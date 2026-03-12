package dam_a51728.systeminfoapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("StringFormatMatches")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val textView: TextView = findViewById(R.id.systemInfoText)

        textView.text = getString(R.string.system_info_msg,
            android.os.Build.MANUFACTURER, android.os.Build.MODEL, android.os.Build.BRAND,
            android.os.Build.TYPE, android.os.Build.USER, android.os.Build.VERSION_CODES.BASE,
            android.os.Build.VERSION.INCREMENTAL, android.os.Build.VERSION.SDK_INT, android.os.Build.VERSION_CODES.R,
            android.os.Build.DISPLAY)
    }
}