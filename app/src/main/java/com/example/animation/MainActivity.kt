package com.example.animation
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.animation.R
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.animation.ui.theme.AnimationTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationTheme {


                ClickToAnimateImage()
                AnimatedVisibilityExample()
            }
        }
    }
}


@Composable
fun AnimatedVisibilityExample() {
    val visible = remember { mutableStateOf(true) }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        Button(onClick = { visible.value = !visible.value }) {
            Text("Toggle Visibility")
        }

        AnimatedVisibility(
            visible = visible.value,
            enter = fadeIn(),
            exit = fadeOut ()
        ) {
            Text("Hello Im visible!", modifier = Modifier.padding(top = 16.dp))
        }
    }
}

@Composable
fun ClickToAnimateImage() {
    var clicked by remember { mutableStateOf(false) }
    val alpha = animateFloatAsState(
        targetValue = if (clicked) 1f else 0f,
        animationSpec = tween(durationMillis = 1000)
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.snak),
            contentDescription = null,
            modifier = Modifier
                .clickable { clicked = !clicked }
                .alpha(alpha.value)
        )
    }
}

@Preview
@Composable
fun PreviewClickToAnimateImage() {
    MaterialTheme {
        ClickToAnimateImage()
    }
}
