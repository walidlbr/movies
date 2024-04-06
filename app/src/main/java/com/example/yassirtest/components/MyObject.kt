package com.example.yassirtest.components

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yassirtest.Screens.DetailScreen
import com.example.yassirtest.models.Movie
import com.example.yassirtest.utils.LoadImage


@Composable
fun MyObject(itemObj : Movie, context : Context){
    

    Row (
        Modifier
            .padding(10.dp)
            .clickable {
                val navigate = Intent(context, DetailScreen::class.java)
                navigate.putExtra("itemId", itemObj.id)
                context.startActivity(navigate)
            }){
        Box(modifier = Modifier
            .width(105.dp)
            .height(160.dp)
            .background(Color.Gray)
            ){
            LoadImage(imageUrl = "https://image.tmdb.org/t/p/w500${itemObj.poster_path}")
            
        };
        Box(modifier=Modifier.width(10.dp))
        Column{
            Text(
                text = itemObj.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Box(modifier = Modifier.height(10.dp))
            Text(
                text = itemObj.release_date,
                fontSize = 16.sp

            )

        }
    }
}