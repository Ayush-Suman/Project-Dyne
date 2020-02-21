package a.suman.dyne.User.Views.Fragments.Fragment_home

import a.suman.dyne.Adapter.HomeRecyclerViewAdapter
import a.suman.dyne.Adapter.onClickListenerHome
import a.suman.dyne.R
import a.suman.dyne.User.GymPaymentInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_fragment_home.*


class fragment_home : Fragment(),onClickListenerHome {



    val homeRecyclerViewAdapter=HomeRecyclerViewAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager=LinearLayoutManager(activity)
        recyclerView.adapter=homeRecyclerViewAdapter
        val h_viewmodel=ViewModelProviders.of(this).get(h_viewmodel::class.java)
        h_viewmodel.getDataforView()
        h_viewmodel.Gym.observe(viewLifecycleOwner, Observer{words->words.let{
            homeRecyclerViewAdapter.setData(it)
        }})

    }

    override fun onClick(position: Int) {
        val intent= Intent(context, GymPaymentInterface::class.java)
        intent.putExtra("id", homeRecyclerViewAdapter.listHome[position].id)
        startActivity(intent)
    }



}
