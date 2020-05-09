package com.example.recyclerdemo.adapter

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerdemo.R
import com.example.recyclerdemo.model.ItemList
import kotlinx.android.synthetic.main.custom_recycler.view.*
import kotlinx.android.synthetic.main.dialog_info.*
import kotlinx.android.synthetic.main.dialog_web_view.*
import java.time.format.DateTimeFormatter


class RecyclerAdapter(
    private var context: Context,
    private var dataItem: ArrayList<ItemList>,
    private var typeView: Int
) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    private val widthDialog = 900

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(item: ItemList) {
            val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            Glide.with(itemView.context)
                .load(item.url)
                .into(itemView.imageList)
            itemView.textList.text = item.title
            itemView.textDate.text = item.date.format(formatter)
            if (!item.isStar) {
                itemView.iconList.setImageResource(R.drawable.if000_ic_star_stroke)
            } else {
                itemView.iconList.setImageResource(R.drawable.if000_ic_star_fill_yellow)
            }
            if (item.isWebView) {
                itemView.iconWebView.visibility = View.VISIBLE
                itemView.iconWebView.setImageResource(R.drawable.if014_ic_direct_link)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        return if (viewType == 6) {
            val view = layoutInflater.inflate(R.layout.custom_recycler2, null)
            ViewHolder(view)
        } else {
            val view = layoutInflater.inflate(R.layout.custom_recycler, null)
            ViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return dataItem.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(dataItem[position])
        holder.itemView.iconList.setOnClickListener {
            if (dataItem[position].isStar) {
                holder.itemView.iconList.setImageResource(R.drawable.if000_ic_star_stroke)
                dataItem[position].isStar = false
            } else {
                holder.itemView.iconList.setImageResource(R.drawable.if000_ic_star_fill_yellow)
                dataItem[position].isStar = true
            }
        }
        holder.itemView.cardView.setOnClickListener {
            if (dataItem[position].isWebView) {
                displayDialogWebView(dataItem[position])
            } else {
                displayDialogInfo(dataItem[position])
            }

        }
    }

    override fun getItemViewType(position: Int): Int {
        return typeView
    }

    private fun displayDialogInfo(item: ItemList) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_info, null)
        val builder = AlertDialog.Builder(context).setView(dialogView)
        val dialog = builder.show()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window?.attributes)
        lp.width = widthDialog
        dialog.window?.attributes = lp
        Glide.with(dialog.context)
            .load(item.url)
            .into(dialog.imageDialog)
        dialog.titleDialog.text = item.title
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        dialog.dateDialog.text = item.date.format(formatter)
        dialog.buttonOK.setOnClickListener {
            dialog.dismiss()
        }
        dialog.buttonCancel.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun displayDialogWebView(item: ItemList) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_web_view, null)
        val builder = AlertDialog.Builder(context).setView(dialogView)
        val dialog = builder.show()
        dialog.webView.loadUrl(item.http)
        dialog.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }
    }

}

