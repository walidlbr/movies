package com.example.yassirtest

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.yassirtest.components.MyLoader
import com.example.yassirtest.components.MyObject
import com.example.yassirtest.models.Movie
import com.example.yassirtest.viewModel.HttpCalls
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.internal.LinkedTreeMap
import com.google.gson.reflect.TypeToken
import org.json.JSONObject


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            myColumn(this@MainActivity)
        }



    }
}

@Composable
fun myColumn(context : Context){

    var items by remember {
        mutableStateOf(listOf(Movie()))
    }


    var isLoading by remember {
        mutableStateOf(true)
    }

    HttpCalls.fetchMovies{ data ->
        var resultAsList = Gson().fromJson(data!!["results"].toString(), Array<Movie>::class.java)
        items = resultAsList.toList()

        isLoading = false

    }

    if (!isLoading){
        LazyColumn {
            items(items.size) { index ->
                var item = items.get(index)
                MyObject(item, context)
            }
        }
    }else{
        MyLoader()
    }
}

