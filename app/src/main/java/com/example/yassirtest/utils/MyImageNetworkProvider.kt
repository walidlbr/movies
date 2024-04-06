package com.example.yassirtest.utils

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage



@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LoadImage(imageUrl : String){
    GlideImage(model = imageUrl, contentDescription = "loadingImage",Modifier.fillMaxSize())
}


