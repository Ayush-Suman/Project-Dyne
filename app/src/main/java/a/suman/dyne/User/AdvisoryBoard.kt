package a.suman.dyne.User

import a.suman.dyne.R
import a.suman.dyne.User.Adapter.BoardRecyclerViewAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_advisory_board.*

class AdvisoryBoard : AppCompatActivity() {
var listBoard:MutableList<Board> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advisory_board)
    val firebaseFirestore=FirebaseFirestore.getInstance()
        recyclerViewBoard.layoutManager= LinearLayoutManager(this)
        val adapter=BoardRecyclerViewAdapter()
        recyclerViewBoard.adapter=adapter
        firebaseFirestore.collection("Board").get().addOnCompleteListener {
            if(it.isSuccessful){
                for (i in 0 until it.result!!.documents.size ){
                listBoard.add(
                    Board(
                        it.result!!.documents[i]["name"].toString(),
                        it.result!!.documents[i]["desc"].toString()
                    )
                )
                }
                adapter.setData(listBoard)
            }
        }
        imageView19.setOnClickListener {onBackPressed()}

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
