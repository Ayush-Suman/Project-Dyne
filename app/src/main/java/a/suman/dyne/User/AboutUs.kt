package a.suman.dyne.User

import a.suman.dyne.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_about_us.*

class AboutUs : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)
    imageView20.setOnClickListener { onBackPressed() }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
