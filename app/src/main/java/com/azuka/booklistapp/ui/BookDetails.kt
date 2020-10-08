package com.azuka.booklistapp.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.azuka.booklistapp.R
import com.azuka.booklistapp.Remote.JSONDataItem

class BookDetails : AppCompatActivity() {

    private lateinit var webView: WebView

    companion object {
        const val EXTRA_TITLE = "title"
        const val EXTRA_URL = "url"

        fun newIntent(context: Context, recipe: JSONDataItem): Intent {
            val detailIntent = Intent(context, BookDetails::class.java)

            detailIntent.putExtra(EXTRA_TITLE, recipe.title)
            detailIntent.putExtra(EXTRA_URL, recipe.instructionUrl)

            return detailIntent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        val title = intent.extras?.getString(EXTRA_TITLE)
        val url = intent.extras?.getString(EXTRA_URL)

        setTitle(title)

        webView = findViewById(R.id.detail_web_view)

        if (url != null) {
            webView.loadUrl(url)
        }
    }
}
