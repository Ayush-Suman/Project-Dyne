package a.suman.dyne.User


import a.suman.dyne.R
import a.suman.dyne.User.Adapter.GymViewPagerAdapter
import a.suman.dyne.User.Adapter.MembershipRecyclerViewAdapter
import android.view.View
import a.suman.dyne.User.Adapter.onClickListenerGym
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_gym_payment_interface.*


class GymPaymentInterface : AppCompatActivity(), onClickListenerGym {
    var listplans: MutableList<Plans> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gym_payment_interface)
        val id = intent.extras!!.getString("id").toString()
        recyclerviewpay.layoutManager = LinearLayoutManager(this)
        val adapter = MembershipRecyclerViewAdapter(this)
        recyclerviewpay.adapter = adapter
        viewPager.adapter = GymViewPagerAdapter(this)

        val firebaseFirestore = FirebaseFirestore.getInstance()
        firebaseFirestore.collection("Gym").document(id).collection("Plans")
            .orderBy("plan", Query.Direction.ASCENDING).get().addOnCompleteListener {
            d("Data Called", "${it.result!!.documents}")
            if (it.isSuccessful) {
                for (i in 0 until it.result!!.documents.size) {
                    listplans.add(
                        Plans(
                            it.result!!.documents[i]["plan"].toString(),
                            it.result!!.documents[i]["price"].toString()
                        )
                    )
                }
                adapter.setData(listplans)
            }
        }
    }


    override fun onClick(position: Int) {

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}
