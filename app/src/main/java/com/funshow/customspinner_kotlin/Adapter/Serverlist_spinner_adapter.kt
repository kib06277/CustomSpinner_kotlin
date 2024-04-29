package com.funshow.customspinner_kotlin.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.funshow.customspinner_kotlin.Data.ServiceListData
import com.funshow.customspinner_kotlin.R

//伺服器下拉選單調適
class Serverlist_spinner_adapter (context: Context, var listItems: ArrayList<ServiceListData>) : BaseAdapter() {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    //獲得總數
    override fun getCount(): Int {
        return listItems.size
    }

    //獲得列表
    override fun getItem(position: Int): Any {
        return position
    }

    //獲得列表 id
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //獲得列表畫面
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val itemrowholder: ItemRowHolder
        if (convertView == null) {
            view = mInflater.inflate(R.layout.layout_server_spinner, parent, false)
            itemrowholder = ItemRowHolder(view)
            view?.tag = itemrowholder
        } else {
            view = convertView
            itemrowholder = view.tag as ItemRowHolder
        }

        itemrowholder.value?.text = listItems[position].name
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val itemrowholder: ItemRowHolder_DropDown
        if (convertView == null) {
            view = mInflater.inflate(R.layout.layout_server_dropdown_spinner, parent, false)
            itemrowholder = ItemRowHolder_DropDown(view)
            view?.tag = itemrowholder
        } else {
            view = convertView
            itemrowholder = view.tag as ItemRowHolder_DropDown
        }

        itemrowholder.value?.text = listItems[position].name
        return view
    }

    private class ItemRowHolder(row: View?) {
        val title: TextView? = row?.findViewById(R.id.title)
        val value: TextView? = row?.findViewById(R.id.value)
        val downicon: ImageView? = row?.findViewById(R.id.downicon)
    }

    private class ItemRowHolder_DropDown(row: View?) {
        val value: TextView? = row?.findViewById(R.id.value)
    }
}