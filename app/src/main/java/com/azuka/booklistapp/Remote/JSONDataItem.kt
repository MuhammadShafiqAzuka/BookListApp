package com.azuka.booklistapp.Remote

import android.content.Context
import android.content.res.Resources
import android.graphics.BitmapFactory
import org.json.JSONException
import org.json.JSONObject


class JSONDataItem(
    val title: String,
    val imageUrl: String,
    val instructionUrl: String
){

    companion object {

        fun getRecipesFromFile(filename: String, context: Context): ArrayList<JSONDataItem> {
            val recipeList = ArrayList<JSONDataItem>()
            val resources: Resources = context.resources

            try {
                // Load data
                val jsonString = loadJsonFromAsset("data.json", context)
                val json = JSONObject(jsonString)
                val book = json.getJSONArray("books")
                val resourceId =
                resources.getIdentifier(book.toString(), "drawable", context.packageName)
                //val drawable = resources.getDrawable(resourceId)
                val bitmap = BitmapFactory.decodeResource(context.resources, resourceId)

                // Get Recipe objects from data
                (0 until book.length()).mapTo(recipeList) {
                    JSONDataItem(
                        book.getJSONObject(it).getString("title"),
                        book.getJSONObject(it).getString("image"),
                        book.getJSONObject(it).getString("url")
                    )
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            return recipeList
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