package com.azuka.booklistapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.azuka.booklistapp.R
import com.azuka.booklistapp.Remote.JSONDataItem
import com.squareup.picasso.Picasso

class BookRecylcerAdapter(private val context: Context,
                          private val dataSource: ArrayList<JSONDataItem>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val holder: ViewHolder

        // 1
        if (convertView == null) {
            // 2
            view = inflater.inflate(R.layout.book_list, parent, false)

            // 3
            holder = ViewHolder()
            holder.thumbnailImageView = view.findViewById(R.id.book_list_thumbnail) as ImageView

            // 4
            view.tag = holder
        } else {
            // 5
            view = convertView
            holder = convertView.tag as ViewHolder
        }
        // 6
        val thumbnailImageView = holder.thumbnailImageView
        val book = getItem(position) as JSONDataItem

        when (position) {
            0 -> {
                Picasso.with(context).load(book.imageUrl).placeholder(R.drawable.cover1).into(thumbnailImageView)
            }
            1 -> {
                Picasso.with(context).load(book.imageUrl).placeholder(R.drawable.cover2)
                    .into(thumbnailImageView)
            }
            2 -> {
                Picasso.with(context).load(book.imageUrl).placeholder(R.drawable.cover3)
                    .into(thumbnailImageView)
            }
            3 -> {
                Picasso.with(context).load(book.imageUrl).placeholder(R.drawable.cover4).into(thumbnailImageView)
            }
            4 -> {
                Picasso.with(context).load(book.imageUrl).placeholder(R.drawable.cover3).into(thumbnailImageView)
            }
            5 -> {
                Picasso.with(context).load(book.imageUrl).placeholder(R.drawable.cover4).into(thumbnailImageView)
            }
        }
        return view
    }

    private class ViewHolder {
        lateinit var thumbnailImageView: ImageView
    }
}