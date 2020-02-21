package a.suman.dyne.User.Views

import a.suman.dyne.R
import a.suman.dyne.User.FirstLogin
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.transition.Fade
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ActionBarContainer
import androidx.core.app.ActivityOptionsCompat.makeSceneTransitionAnimation
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

lateinit var firebaseAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Animation
        window.decorView.systemUiVisibility=
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        val fade = Fade()
        val decor = window.decorView
        fade.excludeTarget(decor.findViewById<ActionBarContainer>(R.id.action_bar_container), true)
        fade.excludeTarget(
            decor.findViewById<ActionBarContainer>(android.R.id.navigationBarBackground),
            true
        )
        fade.excludeTarget(
            decor.findViewById<ActionBarContainer>(android.R.id.statusBarBackground),
            true
        )
        window.enterTransition=null
        window.exitTransition=null
        window.sharedElementEnterTransition.duration=1000
        Handler().postDelayed({
            textView.animate().alpha(1f).duration=1000
            textView.animate().translationX(0f).setDuration(1000).interpolator=DecelerateInterpolator()
            cardView.animate().alpha(1f).duration=800
            cardView2.animate().alpha(1f).duration=900
            cardView3.animate().alpha(1f).duration=1000
            cardView4.animate().alpha(1f).duration=1000
            cardView.animate().translationY(0f).duration=800
            cardView2.animate().translationY(0f).duration=800
            cardView3.animate().translationY(0f).duration=1000
            cardView4.animate().translationY(0f).duration=1000
            textView4.animate().alpha(1f).duration=1100
            textView4.animate().translationY(0f).setDuration(1100).withEndAction{
                view.animate().alpha(1f).duration=1000
                view.animate().translationX(0f).duration=1000
                textView5.animate().alpha(1f).duration=1000
                textView6.animate().alpha(1f).duration=1000
                textView5.animate().translationY(0f).duration=1000
                textView6.animate().translationY(0f).duration=1000
            }
        },1500)
        //Animation Ends

        firebaseAuth=FirebaseAuth.getInstance()


    textView2.setOnClickListener {

        progressBar.visibility=View.VISIBLE
        val imm =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)

        if(editText.text.isNotBlank()&& editText2.text.isNotBlank()) {
            firebaseAuth.signInWithEmailAndPassword(
                editText.text.toString(),
                editText2.text.toString()
            ).addOnCompleteListener{
                progressBar.visibility=View.GONE
                if(!it.isSuccessful){
                 if(it.exception is FirebaseAuthInvalidCredentialsException){
                     Toast.makeText(this@Login, "Invalid Credentials", Toast.LENGTH_LONG).show()
                 }else{
                     Toast.makeText(this@Login, "Something Went Wrong!", Toast.LENGTH_LONG).show()
                 }
                }else{
                    val intent=Intent(this@Login, Home::class.java)
                    intent.putExtra("UserID", firebaseAuth.currentUser!!.uid)
                    startActivity(intent)
                    finish()
                }}
            .addOnFailureListener {  }
        }else{
            Toast.makeText(this@Login, "Enter Complete Information", Toast.LENGTH_LONG).show()
        }
    }
        textView3.setOnClickListener {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(this@Login.resources.getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            val googleSignInClient:GoogleSignInClient= GoogleSignIn.getClient(this, gso)

            val signInGoogle:Intent= googleSignInClient.signInIntent
            startActivityForResult(signInGoogle, 100)
        }
    }


    public override fun onActivityResult(requestCode:Int, resultCode:Int, data:Intent? ){
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
            val account:GoogleSignInAccount? = task.getResult(ApiException::class.java)
            firebaseAuthWithGoogle(account!!)
            }
            catch (e: Exception){
                Toast.makeText(this, "$e Something went wrong! Try Again", Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {



        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    if(task.result!!.additionalUserInfo!!.isNewUser){

                        val intent=Intent(this@Login, FirstLogin::class.java)
                        intent.putExtra("Name", task.result!!.user!!.displayName)
                        intent.putExtra("Email", task.result!!.user!!.email)
                        intent.putExtra("Phone", task.result!!.user!!.phoneNumber)
                        startActivity(intent)
                        finish()
                        return@addOnCompleteListener
                    }
                    val intent=Intent(this@Login, Home::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Authentication Failed.", Toast.LENGTH_SHORT).show()

                }
            }
    }

}
