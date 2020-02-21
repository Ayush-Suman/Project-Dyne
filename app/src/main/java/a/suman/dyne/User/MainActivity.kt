package a.suman.dyne.User.Views

import a.suman.dyne.R
import android.app.ActivityOptions.makeSceneTransitionAnimation
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var firebaseAuth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.systemUiVisibility=
            View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.exitTransition=null
        firebaseAuth = FirebaseAuth.getInstance()
        imageView2.animate().alpha(1f).duration=1000

    }

    override fun onStart() {
        super.onStart()
        val intent:Intent
        if(firebaseAuth.currentUser != null) {
            intent= Intent(this@MainActivity, Home::class.java)
            intent.putExtra("UserID", firebaseAuth.currentUser!!.uid)

        }else {
        intent= Intent(this@MainActivity, Login::class.java)
        }
        Handler().postDelayed({
            startActivity(intent)
            finish()},1000)
    }
}
