package a.suman.dyne.User.Adapter

import a.suman.dyne.R
import a.suman.dyne.User.Fragments.Diet.Data.DietDataClass
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DietRecyclerViewAdapter:RecyclerView.Adapter<DietViewHolder>(){
   var dietList:List<DietDataClass> = emptyList()
    var parent:ViewGroup? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DietViewHolder {
    this.parent= parent
        return DietViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.diet_recycler, parent, false))
    }

    override fun getItemCount(): Int {
        return dietList.size
    }

    override fun onBindViewHolder(holder: DietViewHolder, position: Int) {
        holder.textView13.text=dietList[position].Name
        holder.textView15.text=dietList[position].Time


        

    }

    fun setData(dietList:List<DietDataClass>){
        this.dietList=dietList
        notifyDataSetChanged()
    }


}

class DietViewHolder(view: View): RecyclerView.ViewHolder(view){
    val textView13=view.findViewById<TextView>(R.id.textView13)
    val textView15=view.findViewById<TextView>(R.id.textView15)
    val recyclerViewDiet =view.findViewById<RecyclerView>(R.id.recyclerViewDiet)
}