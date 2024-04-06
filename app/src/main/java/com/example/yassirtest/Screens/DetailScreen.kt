package com.example.yassirtest.Screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yassirtest.utils.LoadImage
import com.example.yassirtest.components.MyLoader
import com.example.yassirtest.models.Movie
import com.example.yassirtest.viewModel.HttpCalls
import com.google.gson.Gson


class DetailScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        val receivedObject = intent.getIntExtra("itemId",0)
        setContent {
            details(receivedObject)
        }
    }
}

@Composable
fun details(movieId : Int){
    var item by remember {
        mutableStateOf(Movie())
    }
    var isLoading by remember {
        mutableStateOf(true)
    }
    HttpCalls.fetchSingleMovies(movieId) { data ->
        var result = Gson().fromJson(data!!.toString(), Movie::class.java) as Movie

        item = result

        isLoading = false
    }
    if(!isLoading){
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(Modifier.height(20.dp))
            Box(
                Modifier
                    .width(200.dp)
                    .height(300.dp)
                    .background(Color.Gray)
            ){
                LoadImage(imageUrl = "https://image.tmdb.org/t/p/w500${item.poster_path}")
            }
            Box(Modifier.height(40.dp))

            Column(
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = item.title,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Box(modifier = Modifier.height(10.dp))
                Text(
                    text = item.release_date,
                    fontSize = 16.sp
                )
                Box(Modifier.height(20.dp))
                Text(
                    text = item.overview,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Box(Modifier.height(20.dp))
        }
    }else {
        MyLoader()
    }
}
