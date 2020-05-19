package com.example.recyclerdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.recyclerdemo.MyFragment
import com.example.recyclerdemo.R
import com.example.recyclerdemo.model.DropDownList
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_main.view.dropdown_list
import kotlinx.android.synthetic.main.activity_main.view.imageViewList
import kotlinx.android.synthetic.main.custom_dropdown.view.*

class DropDownListAdapter(
    var context: Context,
    var list: ArrayList<DropDownList>
) : BaseAdapter() {

    class ViewHolder(v: View) {
        val iconOptionList: ImageView = v.findViewById(R.id.iconOptionList)
        val textOptionList: TextView = v.findViewById(R.id.textOptionList)
        val divider: View = v.findViewById(R.id.divider)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View?
        val viewHolder: ViewHolder
        if (convertView == null) {
            val layoutInflater: LayoutInflater = LayoutInflater.from(context)
            view = layoutInflater.inflate(R.layout.custom_dropdown, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = convertView.tag as ViewHolder
        }
        viewHolder.divider.visibility = View.GONE
        if (position == 5) {
            viewHolder.divider.visibility = View.VISIBLE
        }
        val dropDownList: DropDownList = getItem(position) as DropDownList
        viewHolder.iconOptionList.setImageResource(dropDownList.icon)
        viewHolder.textOptionList.text = dropDownList.title
        return view as View
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }
}