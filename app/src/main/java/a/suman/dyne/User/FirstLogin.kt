package a.suman.dyne.User

import a.suman.dyne.R
import a.suman.dyne.User.Views.Home
import a.suman.dyne.Users
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_first_login.*
import java.util.*
import kotlin.time.measureTimedValue

class FirstLogin : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var name:String?=null
    var email:String?=null
    var gender:String?=null
    var number:String?=null
    var number2:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_login)
        val firebaseAuth=FirebaseAuth.getInstance()
        val firebaseFirestore=FirebaseFirestore.getInstance()
        val arrayAdapter= ArrayAdapter.createFromResource(this, R.array.gender_spinner, R.layout.gender_spinner)
        arrayAdapter.setDropDownViewResource(R.layout.gender_spinner_dropdown)
        spinner.adapter=arrayAdapter
        spinner.onItemSelectedListener=this

        val bundle=intent.extras
        name=bundle!!.getString("Name")
        email=bundle.getString("Email")
        number= bundle.getString("Phone")
        number2= editText6.text.toString()
        textView39.text="Welcome $name"
        textView2.setOnClickListener {
            var weight=editText4.text.toString()
            var height=editText5.text.toString()
            if(!(weight.isBlank())&&!(height.isBlank())){
                progressBar.visibility=View.VISIBLE
                    firebaseFirestore.collection("Users").document(firebaseAuth.currentUser!!.uid).set(
                        Users(name, email, number,number2, weight+"kg", height+"cm", gender)
                    ).addOnCompleteListener {
                        progressBar.visibility=View.GONE
                        if(it.isSuccessful){
                            val intent = Intent(this@FirstLogin, Home::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
            }else{
                Toast.makeText(this@FirstLogin, "Enter the details first", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        gender= arrayListOf("Male", "Female", "Others")[position]
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        gender="Male"
    }
}
