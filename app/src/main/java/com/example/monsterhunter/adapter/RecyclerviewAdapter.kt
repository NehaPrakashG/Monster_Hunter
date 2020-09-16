package com.example.monsterhunter.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.monsterhunter.Interface.IOnstateChanged
import com.example.monsterhunter.Interface.IRecyclerV_ItemClick
import com.example.monsterhunter.Model.ArmorDataResponse
import com.example.monsterhunter.R
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class RecyclerviewAdapter(
    var context: Context,
    Listdata: ArrayList<ArmorDataResponse>,
    SearchedData: ArrayList<ArmorDataResponse>,
    val ItemClickListener: IRecyclerV_ItemClick
) : RecyclerView.Adapter<RecyclerviewAdapter.DataHolder>(), Filterable {

    var Armdata: ArrayList<ArmorDataResponse> = Listdata
    var dataSearchList: ArrayList<ArmorDataResponse> = SearchedData
    var hashMap: HashMap<String, Int> = HashMap<String, Int>()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerviewAdapter.DataHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recy_itemlayout, parent, false)
        hashMap.put("chest", R.drawable.ic_chest);
        hashMap.put("gloves", R.drawable.ic_gloves);
        hashMap.put("head", R.drawable.ic_head);
        hashMap.put("legs", R.drawable.ic_legs);
        hashMap.put("waist", R.drawable.ic_waist);
        /* Created a hashmap to map images to type attribute*/
        return DataHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSearchList.size
    }

    override fun onBindViewHolder(holder: RecyclerviewAdapter.DataHolder, position: Int) {
        holder.name_tv.setText(dataSearchList.get(position).name);
        holder.rank_tv.setText(
            dataSearchList.get(position).rank.toString().toUpperCase(Locale.ROOT)
        );
        if (dataSearchList.get(position).rank == "low") {  /*Seting color for high low rank*/
            holder.rank_tv.setTextColor(ContextCompat.getColor(context, R.color.red))
        } else {
            holder.rank_tv.setTextColor(ContextCompat.getColor(context, R.color.green))
        }
        if (hashMap.containsKey(dataSearchList.get(position).type)) {
            holder.imgView.setBackgroundResource(hashMap.get(dataSearchList.get(position).type)!!)
        }
        holder.typeIView.setBackgroundResource(R.drawable.ic_shield)
        holder.base_tv.setText(dataSearchList.get(position).defense?.base.toString() + " +")

        if (!dataSearchList.get(position).slots.isEmpty()) {    /*Created 5 slots to dynamically show and hide the rank */
            if (dataSearchList.get(position).slots.size == 1) {
                holder.slots_tv1.visibility = View.VISIBLE
                holder.slots_tv1.setText(dataSearchList.get(position).slots[0].rank.toString())

            }
            if (dataSearchList.get(position).slots.size == 2) {
                holder.slots_tv1.visibility = View.VISIBLE
                holder.slots_tv2.visibility = View.VISIBLE
                holder.slots_tv1.setText(dataSearchList.get(position).slots[0].rank.toString())
                holder.slots_tv2.setText(dataSearchList.get(position).slots[1].rank.toString())

            } else if (dataSearchList.get(position).slots.size == 3) {
                holder.slots_tv1.visibility = View.VISIBLE
                holder.slots_tv2.visibility = View.VISIBLE
                holder.slots_tv3.visibility = View.VISIBLE
                holder.slots_tv1.setText(dataSearchList.get(position).slots[0].rank.toString())
                holder.slots_tv2.setText(dataSearchList.get(position).slots[1].rank.toString())
                holder.slots_tv3.setText(dataSearchList.get(position).slots[2].rank.toString())

            } else if (dataSearchList.get(position).slots.size == 4) {
                holder.slots_tv1.visibility = View.VISIBLE
                holder.slots_tv2.visibility = View.VISIBLE
                holder.slots_tv3.visibility = View.VISIBLE
                holder.slots_tv4.visibility = View.VISIBLE
                holder.slots_tv1.setText(dataSearchList.get(position).slots[0].rank.toString())
                holder.slots_tv2.setText(dataSearchList.get(position).slots[1].rank.toString())
                holder.slots_tv3.setText(dataSearchList.get(position).slots[2].rank.toString())
                holder.slots_tv4.setText(dataSearchList.get(position).slots[3].rank.toString())

            }
        } else {  /*By default hiding all slots*/
            holder.slots_tv1.visibility = View.GONE
            holder.slots_tv2.visibility = View.GONE
            holder.slots_tv3.visibility = View.GONE
            holder.slots_tv4.visibility = View.GONE
            holder.slots_tv5.visibility = View.GONE

        }

        holder.itemView.setOnClickListener {
            ItemClickListener.onCellClickListener(dataSearchList.get(position).id)
        }
    }

    class DataHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        var name_tv: TextView = v.findViewById(R.id.name_tv)
        var rank_tv: TextView = v.findViewById(R.id.rank_tv)
        var base_tv: TextView = v.findViewById(R.id.base_tv)
        var slots_tv1: TextView = v.findViewById(R.id.slots_tv1)
        var slots_tv2: TextView = v.findViewById(R.id.slots_tv2)
        var slots_tv3: TextView = v.findViewById(R.id.slots_tv3)
        var slots_tv4: TextView = v.findViewById(R.id.slots_tv4)
        var slots_tv5: TextView = v.findViewById(R.id.slots_tv5)
        var imgView: ImageView = v.findViewById(R.id.imgView)
        var typeIView: ImageView = v.findViewById(R.id.typeIView)


    }

    override fun getFilter(): Filter {   /*filtering the searched names*/
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    dataSearchList = Armdata
                } else {
                    val resultList = ArrayList<ArmorDataResponse>()
                    for (row in Armdata) {
                        if (row.name.toString().toLowerCase(Locale.ROOT)
                                .contains(charSearch.toLowerCase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    dataSearchList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = dataSearchList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                dataSearchList = results?.values as ArrayList<ArmorDataResponse>
                notifyDataSetChanged()
            }

        }
    }

    fun registerActivityState() =
        object : IOnstateChanged {  /*to save and retrve state onpaused and resume*/
            override fun onResumed() {
                Log.d("RecyclerviewAdapter", "onResumed: ")
            }

            override fun onPaused() {
                Log.d("RecyclerviewAdapter", "onPaused: ")
            }
        }
}


