package com.bts.mufti.testbts.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bts.mufti.testbts.R
import com.bts.mufti.testbts.data.DataItem
import com.bts.mufti.testbts.databinding.ItemChecklistBinding

class CheckListAdapter (private val listCheck: List<DataItem>) : RecyclerView.Adapter<CheckListAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_checklist, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val check = listCheck[position]
        holder.bind(check)
    }

    override fun getItemCount(): Int = listCheck.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemChecklistBinding.bind(itemView)

        fun bind(check: DataItem) {
            with(binding) {
                itemId.text = check.id?.toString()
                itemName.text = check.name.toString()
                itemItem.text = check.items?.size.toString()
                itemStatus.text = check.checklistCompletionStatus.toString()
            }
        }
    }
}