package a.suman.dyne.User.Fragments.Chat

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import a.suman.dyne.R
import a.suman.dyne.User.Adapter.ChatRecyclerViewAdapter
import a.suman.dyne.User.Adapter.onClickListener
import a.suman.dyne.User.ChatView
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_chatlist.*

class chatlist : Fragment(), onClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chatlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val chatRecyclerViewAdapter=ChatRecyclerViewAdapter(this)
        recyclerViewChat.layoutManager=LinearLayoutManager(context)
        var chatviewmodel=ViewModelProviders.of(this).get(chatviewmodel::class.java)
        chatviewmodel.getDataforView()
        recyclerViewChat.adapter=chatRecyclerViewAdapter
        chatviewmodel.chats.observe(viewLifecycleOwner, Observer {
            chatRecyclerViewAdapter.setData(it)
        })
    }

    override fun onClick(chatid:String, trainerN:String) {
        val intent= Intent(context, ChatView::class.java)
        intent.putExtra("ChatID",chatid)
        intent.putExtra("TrainerN",trainerN)
        startActivity(intent)
    }
}
