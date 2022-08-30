package com.selftutor.myarchitecture.view.changecolor

import android.location.GnssAntennaInfo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.selftutor.myarchitecture.databinding.ActivityMainBinding.inflate
import com.selftutor.myarchitecture.databinding.ItemColorBinding
import com.selftutor.myarchitecture.model.colors.NamedColor

class ColorsAdapter(
	private val listener: Listener
): RecyclerView.Adapter<ColorsAdapter.ViewHolder>(), View.OnClickListener {

	var items : List<NamedColorListItem> = emptyList()
		set(value){
			field = value
			notifyDataSetChanged()
		}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		val binding = ItemColorBinding.inflate(inflater)

		binding.root.setOnClickListener(this)
		return ViewHolder(binding)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val namedColor= items[position].namedColor
		val selected = items[position].selected

		with(holder.binding){
			root.tag = namedColor
			colorNameTextView.text = namedColor.name
			colorView.setBackgroundColor(namedColor.value)
			ivSelected.visibility = if(selected) View.VISIBLE else View.GONE
		}
	}

	override fun getItemCount(): Int = items.size

	override fun onClick(v: View) {
		val item = v.tag as NamedColor
		listener.onColorChosen(item)
	}

	class ViewHolder(
		val binding: ItemColorBinding
	): RecyclerView.ViewHolder(binding.root)

	interface Listener{
		/**
		 * Called when user chooses the specified color
		 * @param namedColor color chosen by the user
		 */
		fun onColorChosen(namedColor: NamedColor)
	}

}