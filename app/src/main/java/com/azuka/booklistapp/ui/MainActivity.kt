package com.azuka.booklistapp.ui

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.azuka.booklistapp.R
import com.azuka.booklistapp.Remote.JSONDataItem
import com.azuka.booklistapp.adapter.BookRecylcerAdapter
import com.shreyaspatil.MaterialDialog.MaterialDialog
import com.squareup.picasso.Picasso


class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.book_list_view)

        val recipeList = JSONDataItem.getRecipesFromFile("data.json", this)
        val adapter = BookRecylcerAdapter(this, recipeList)

        listView.adapter = adapter
        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedBook = recipeList[position]
            showDialog(selectedBook, position)
        }
    }

    private fun showDialog(selectedBook: JSONDataItem, position: Int) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.custome_dialog)

        val body = dialog.findViewById(R.id.tvTitle) as TextView
        val readBtn = dialog.findViewById(R.id.buttonRead) as Button
        val deleteBtn = dialog.findViewById(R.id.buttonDelete) as Button
        val shareBtn = dialog.findViewById(R.id.buttonShare) as Button
        val image = dialog.findViewById(R.id.ivImage) as ImageView

        body.text = selectedBook.title
        when (position) {
            0 -> {
                Picasso.with(this).load(selectedBook.imageUrl).placeholder(R.drawable.cover1).into(image)
            }
            1 -> {
                Picasso.with(this).load(selectedBook.imageUrl).placeholder(R.drawable.cover2).into(image)
            }
            2 -> {
                Picasso.with(this).load(selectedBook.imageUrl).placeholder(R.drawable.cover3).into(image)
            }
            3 -> {
                Picasso.with(this).load(selectedBook.imageUrl).placeholder(R.drawable.cover4).into(image)
            }
            4 -> {
                Picasso.with(this).load(selectedBook.imageUrl).placeholder(R.drawable.cover3).into(image)
            }
            5 -> {
                Picasso.with(this).load(selectedBook.imageUrl).placeholder(R.drawable.cover4).into(image)
            }
        }
        readBtn.setOnClickListener {
            val detailIntent = BookDetails.newIntent(this, selectedBook)
            startActivity(detailIntent)
        }
        shareBtn.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Hey, checkout my books ${selectedBook.title}")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

        deleteBtn.setOnClickListener {
            val mDialog: MaterialDialog = MaterialDialog.Builder(this)
                .setTitle("Delete?")
                .setMessage("Are you sure want to delete ${selectedBook.title}")
                .setCancelable(false)
                .setPositiveButton("Delete", R.drawable.ic_delete
                ) { _, _ ->
                    Toast.makeText(this, "You delete it...for now", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Cancel", R.drawable.ic_close
                ) { dialogInterface, _ -> dialogInterface.dismiss()
                    dialog.show()}
                .build()

            //material dialog show
            mDialog.show()
        }
        //custom dialog show
        dialog.show()
    }
}
