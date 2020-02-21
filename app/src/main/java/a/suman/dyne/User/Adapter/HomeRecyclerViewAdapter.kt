package a.suman.dyne.Adapter

import a.suman.dyne.R
import a.suman.dyne.User.Fragments.Fragment_home.Data.HomeDataClass
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.home_recycler.view.*

class HomeRecyclerViewAdapter(val onClickListener: onClickListenerHome) : RecyclerView.Adapter<HomeViewHolder>(){

    var listHome:List<HomeDataClass> = emptyList()

    override fun getItemCount(): Int {
        return listHome.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.Description.text=listHome[position].desc
        holder.Name.text=listHome[position].name
        holder.Location.text=listHome[position].town+", " +listHome[position].state
        holder.ratingBar.rating=listHome[position].rating
        holder.cardView2.setOnClickListener{onClickListener.onClick(position)}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {

        return HomeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.home_recycler, parent, false))
    }

    fun setData(data:List<HomeDataClass>){
        listHome=data
        notifyDataSetChanged()
    }

}

class  HomeViewHolder(view: View):RecyclerView.ViewHolder(view){
    val Name: TextView = view.findViewById(R.id.textView10)
    val Description: TextView = view.findViewById(R.id.textView8)
    val Location: TextView = view.findViewById(R.id.textView9)
    val ratingBar:RatingBar =view.findViewById(R.id.ratingBar)
    val cardView2:CardView =view.findViewById(R.id.cardView2)
    val imageView: ImageView = view.findViewById(R.id.imageView3)
}

interface onClickListenerHome{
    fun onClick(position: Int)
}