package com.example.initiativesorter
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView


class CharAdapter(private val mList: List<Character>) : RecyclerView.Adapter<CharAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val char = mList[position]
        if (position == 0){
            holder.itemMain.setBackgroundColor(
                ContextCompat.getColor(
                holder.itemView.context,
                R.color.purple_200
            ))
        }
        holder.charName.text = char.name
        holder.initValue.hint = char.init.toString()
        holder.hpValue.hint = char.hp.toString()

        holder.initRes.text =  if(char.lastRollSum > 0) char.lastRollSum.toString() else char.init.toString()
        holder.hpRes.text = char.lastRollHp.toString()
        if(char.lastRollSum != -1){
            holder.initRoll.hint = char.lastRollSum.toString()
            holder.initRes.text = (char.init + char.lastRollSum).toString()
        }
        holder.sumButton.setOnClickListener {
            var iRes: Int = holder.initRoll.text.toString().toIntOrNull() ?: 0
            var hpRes: Int = holder.hpRoll.text.toString().toIntOrNull() ?: 0
            char.lastRollSum = (char.init + iRes).toString().toInt()
            char.lastRollHp = if((char.lastRollHp - hpRes) >= 0) (char.lastRollHp - hpRes).toString().toInt() else 0
            holder.initRes.text =  (char.init + iRes).toString()
            holder.hpRes.text = char.lastRollHp.toString()
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var charName: TextView = itemView.findViewById(R.id.item_name)
        var initValue: EditText = itemView.findViewById(R.id.item_init_edit)
        var hpValue: EditText = itemView.findViewById(R.id.item_hp_edit)
        var initRoll: EditText = itemView.findViewById(R.id.item_init_roll)
        var hpRoll: EditText = itemView.findViewById(R.id.item_hp_roll)
        var initRes: TextView = itemView.findViewById(R.id.item_init_res)
        var hpRes: TextView = itemView.findViewById(R.id.item_hp_res)
        val sumButton: Button = itemView.findViewById<Button>(R.id.item_button)
        val itemMain: ConstraintLayout = itemView.findViewById(R.id.item_main)
    }
}