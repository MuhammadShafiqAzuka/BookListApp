package com.azuka.booklistapp.Remote

import android.content.Context
import org.json.JSONException
import org.json.JSONObject


class JSONDataItem(
    val title: String,
    val imageUrl: String,
    val instructionUrl: String
){

    companion object {

        fun getBookFromFile(context: Context): ArrayList<JSONDataItem> {
            val bookList = ArrayList<JSONDataItem>()

            try {
                // Load data
                val filename = loadJsonFromAsset("data.json", context)
                val json = JSONObject(filename!!)
                val book = json.getJSONArray("books")
                // Get Recipe objects from data
                (0 until book.length()).mapTo(bookList) {
                    JSONDataItem(
                        book.getJSONObject(it).getString("title"),
                        book.getJSONObject(it).getString("image"),
                        book.getJSONObject(it).getString("url")
                    )
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            return bookList
        }

        private fun loadJsonFromAsset(filename: String, context: Context): String? {
            val json: String?
            try {
                val inputStream = context.assets.open(filename)
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                json = String(buffer, Charsets.UTF_8)
            } catch (ex: java.io.IOException) {
                ex.printStackTrace()
                return null
            }
            return json
        }
    }
}