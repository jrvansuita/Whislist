package com.vansuita.whislist

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vansuita.whislist.databinding.ListItemBinding

class ListAdapter :
	RecyclerView.Adapter<ListAdapter.ViewHolder>() {

	private val data: MutableList<Item> = mutableListOf()

	inner class ViewHolder(private val binding: ListItemBinding) :
		RecyclerView.ViewHolder(binding.root) {

		fun bind(item: Item) {
			binding.name.text = item.name
			binding.price.text = item.price.toString()
			binding.url.text = item.url
			binding.url.setOnClickListener {
				it.context.openUrl(item)
			}
			binding.root.setOnLongClickListener {
				deleteItem(item)
				true
			}
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		return ViewHolder(
			ListItemBinding.inflate(
				LayoutInflater.from(parent.context),
				parent,
				false
			)
		)
	}

	override fun getItemCount() = data.size

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.bind(data[position])
	}

	fun addItem(item: Item) {
		data.add(item)
		notifyItemChanged(data.size - 1)
	}

	private fun deleteItem(item: Item) {
		val position = data.indexOf(item)
		data.removeAt(position)
		notifyItemChanged(position)
	}

}
