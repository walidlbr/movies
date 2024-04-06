package com.example.yassirtest.viewModel

import android.annotation.SuppressLint
import android.os.AsyncTask
import com.example.yassirtest.models.Movie
import com.google.gson.Gson
import org.json.JSONObject
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class HttpCalls {
    companion object{
        fun fetchMovies(callback: (JSONObject?) -> Unit) {
            val task = object : AsyncTask<Void, Void, JSONObject>() {

                override fun doInBackground(vararg params: Void?) : JSONObject {
                    try {
                        val url = URL("https://api.themoviedb.org/3/discover/movie?api_key=c9856d0cb57c3f14bf75bdc6c063b8f3")
                        val connection = url.openConnection() as HttpURLConnection
                        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                            val json = JSONObject(InputStreamReader(connection.inputStream).readText())


                            return json
                        } else {
                            return JSONObject()
                        }
                    } catch (e: Exception) {
                        println("Error: ${e.message}")
                        return JSONObject()
                    }
                }

                override fun onPostExecute(result: JSONObject?) {
                    super.onPostExecute(result)
                    callback(result)
                }

            }
            task.execute()
        }

        fun fetchSingleMovies(movieId : Int,callback: (JSONObject?) -> Unit) {
            val task = object : AsyncTask<Void, Void, JSONObject>() {

                override fun doInBackground(vararg params: Void?) : JSONObject {
                    try {
                        val url = URL("https://api.themoviedb.org/3/movie/${movieId.toString()}?api_key=c9856d0cb57c3f14bf75bdc6c063b8f3")
                        val connection = url.openConnection() as HttpURLConnection
                        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                            val json = JSONObject(InputStreamReader(connection.inputStream).readText())

                            return json
                        } else {
                            return JSONObject()
                        }
                    } catch (e: Exception) {
                        println("Error: ${e.message}")
                        return JSONObject()
                    }
                }

                override fun onPostExecute(result: JSONObject?) {
                    super.onPostExecute(result)
                        callback(result)
                }

            }
            task.execute()
        }

    }

}