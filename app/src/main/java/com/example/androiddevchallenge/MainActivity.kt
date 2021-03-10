/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ResistanceConfig
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.black
import com.example.androiddevchallenge.ui.theme.green400
import com.example.androiddevchallenge.ui.theme.grey200
import com.example.androiddevchallenge.ui.theme.grey400
import com.example.androiddevchallenge.ui.theme.myCircle
import com.example.androiddevchallenge.ui.theme.red400
import com.example.androiddevchallenge.ui.theme.shapes
import com.example.androiddevchallenge.ui.theme.teal200
import com.example.androiddevchallenge.ui.theme.timer
import com.example.androiddevchallenge.ui.theme.topRounded
import com.example.androiddevchallenge.ui.theme.typography
import com.example.androiddevchallenge.ui.theme.yellow400
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isSysInDarkTheme = isSystemInDarkTheme()
            val darkTheme = remember { mutableStateOf(isSysInDarkTheme) }
            MyTheme(darkTheme = darkTheme.value) {
                MyApp(darkTheme)
            }
        }
    }
}

// Start building your app here!
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyApp(darkTheme: MutableState<Boolean>) {
    val swipeableState = rememberSwipeableState(0)
    val anchors = mapOf(0f to 0, 200f to 1) // Maps anchor points (in px) to states
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                orientation = Orientation.Vertical,
                resistance = ResistanceConfig(200f, factorAtMin = 0f, factorAtMax = 2f)
            )
    ) {
        // --------------- Backdrop With Buttons --------------
        Row(
            modifier = Modifier.absolutePadding(top = 32.dp, bottom = 16.dp, right = 16.dp, left = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            SetStyleButton(text = "Colors", null, darkTheme)
            SetStyleButton(text = "Watches", null, darkTheme)
            SetStyleButton(text = "Sounds", null, darkTheme)
            SetStyleButton(text = null, iconId = R.drawable.ic_baseline_bedtime_24, darkTheme)
        }
        // --------------- Swipeable Screen With Watch --------------
        Surface(
            color = MaterialTheme.colors.background,
            shape = topRounded,
            elevation = 20.dp,
            modifier = Modifier
                .absolutePadding(top = 16.dp)
                .fillMaxHeight()
                .offset { IntOffset(0, swipeableState.offset.value.roundToInt()) }
        ) {
            Column {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .absolutePadding(top = 10.dp, bottom = 10.dp)
                ) {
                    Icon(imageVector = Icons.Default.ExpandMore, contentDescription = null)
                }
                Column(
                    modifier = Modifier.fillMaxHeight(),
                ) {
                    // TOP WATCH BAND
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    ) {
                        Canvas(modifier = Modifier.fillMaxSize()) {
                            val canvasSize = size
                            val canvasWidth = size.width
                            val canvasHeight = size.height
                            drawLine(
                                start = Offset(x = canvasWidth / 1.65f, y = 0f),
                                end = Offset(x = canvasWidth / 1.65f, y = canvasHeight),
                                color = teal200,
                                strokeWidth = 12.0f
                            )
                            drawLine(
                                start = Offset(x = canvasWidth / 2.5f, y = 0f),
                                end = Offset(x = canvasWidth / 2.5f, y = canvasHeight),
                                color = teal200,
                                strokeWidth = 12.0f
                            )
                            drawRect(
                                color = teal200,
                                topLeft = Offset(x = canvasWidth / 2.65f, y = canvasHeight / 1.1f),
                                size = canvasSize / 4.0f
                            )
                            drawCircle(
                                color = teal200,
                                radius = size.minDimension / 40.0f,
                                center = Offset(x = canvasWidth / 2.0f, y = canvasHeight / 4.4f)
                            )
                            drawCircle(
                                color = teal200,
                                radius = size.minDimension / 40.0f,
                                center = Offset(x = canvasWidth / 2.0f, y = canvasHeight / 2.4f)
                            )
                            drawCircle(
                                color = teal200,
                                radius = size.minDimension / 40.0f,
                                center = Offset(x = canvasWidth / 2.0f, y = canvasHeight / 1.65f)
                            )
                        }
                    }
                    // WATCH FACE
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircleWatch(darkTheme = darkTheme)
                    }
                    // BOTTOM WATCH BAND
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        Canvas(modifier = Modifier.fillMaxSize()) {
                            val canvasSize = size
                            val canvasWidth = size.width
                            val canvasHeight = size.height
                            drawLine(
                                start = Offset(x = canvasWidth / 1.7f, y = 0f),
                                end = Offset(x = canvasWidth / 1.7f, y = canvasHeight),
                                color = teal200,
                                strokeWidth = 12.0f
                            )
                            drawLine(
                                start = Offset(x = canvasWidth / 2.5f, y = 0f),
                                end = Offset(x = canvasWidth / 2.5f, y = canvasHeight),
                                color = teal200,
                                strokeWidth = 12.0f
                            )
                            drawRect(
                                color = teal200,
                                topLeft = Offset(x = canvasWidth / 2.6f, y = -2.0f),
                                size = canvasSize / 4.5f,
                            )
                        }
                    }
                }
            }
        }
    }
}

fun createCountDownTimer(
    millisInFuture: Long,
    countDownInterval: Long,
    hours: MutableState<Int>,
    minutes: MutableState<Int>,
    seconds: MutableState<Int>,
    headerMessage: MutableState<String>,
    startClicked: MutableState<Boolean>,
    isFinished: MutableState<Boolean>,
    isRunning: MutableState<Boolean>
): CountDownTimer {
    return object : CountDownTimer(millisInFuture, countDownInterval) {
        override fun onTick(millisUntilFinished: Long) {
            hours.value = ((millisUntilFinished / (1000 * 60 * 60)) % 24).toInt()
            minutes.value = ((millisUntilFinished / (1000 * 60)) % 60).toInt()
            seconds.value = ((millisUntilFinished / 1000) % 60).toInt()
        }

        override fun onFinish() {
            headerMessage.value = "Time's Up!"
            isFinished.value = true
            isRunning.value = false
            hours.value = 0
            minutes.value = 0
            seconds.value = 0
            startClicked.value = false
        }
    }
}

fun startCountDown(timer: CountDownTimer, startClicked: MutableState<Boolean>) {
    startClicked.value = false
    timer.start()
}

fun cancelCountdown(timer: CountDownTimer, startClicked: MutableState<Boolean>, cancelClicked: MutableState<Boolean>, isRunning: MutableState<Boolean>) {
    startClicked.value = false
    cancelClicked.value = false
    isRunning.value = false
    timer.cancel()
}

@Composable
fun CircleWatch(darkTheme: MutableState<Boolean>) {
    val headerMessage = remember { mutableStateOf("Set Time") }

    val hours = remember { mutableStateOf(0) }
    val minutes = remember { mutableStateOf(0) }
    val seconds = remember { mutableStateOf(0) }

    val startClicked = remember { mutableStateOf(false) }
    val pauseClicked = remember { mutableStateOf(false) }
    val cancelClicked = remember { mutableStateOf(false) }

    val isRunning = remember { mutableStateOf(false) }
    val isFinished = remember { mutableStateOf(false) }

    Card(
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier
            .height(275.dp)
            .width(275.dp),
        shape = myCircle,
        border = BorderStroke(4.dp, MaterialTheme.colors.primary),
        elevation = 15.dp
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .absolutePadding(top = 25.dp),
                horizontalArrangement = Arrangement.Center

            ) {
                if (headerMessage.value == "Time's Up!") {
                    isFinished.value = true
                }
                Text(if (isRunning.value) "Counting Down!" else headerMessage.value, color = MaterialTheme.colors.primary)
            }
            // Start / Stop / Pause / Cancel Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .absolutePadding(top = 5.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                TimeSlot(hours, isRunning, isFinished, darkTheme)
                TimeSlot(minutes, isRunning, isFinished, darkTheme)
                TimeSlot(seconds, isRunning, isFinished, darkTheme)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                val millisInFuture = getMillis(hours, minutes, seconds)
                val countDownTimer = createCountDownTimer(
                    millisInFuture = millisInFuture, countDownInterval = 1000,
                    hours = hours, minutes = minutes, seconds = seconds, headerMessage = headerMessage, startClicked, isFinished, isRunning
                )
                if (startClicked.value) {
                    startCountDown(countDownTimer, startClicked)
                    isRunning.value = true
                }

                if (cancelClicked.value) {
                    if (isRunning.value) cancelCountdown(countDownTimer, startClicked, cancelClicked, isRunning)
                    hours.value = 0
                    minutes.value = 0
                    seconds.value = 0
                    cancelClicked.value = false
                    isRunning.value = false
                }

                if (isRunning.value) {
                    WatchButton(state = WatchButtonState.CANCEL, cancelClicked)
                } else {
                    WatchButton(state = WatchButtonState.START, startClicked)
                    WatchButton(state = WatchButtonState.CANCEL, cancelClicked)
                }
            }
        }
    }
}

private fun getMillis(hours: MutableState<Int>, minutes: MutableState<Int>, seconds: MutableState<Int>): Long {
    var millis: Long = 0
    millis += (hours.value * 3600000)
    millis += (minutes.value * 60000)
    millis += (seconds.value * 1000)
    return millis
}

@Composable
fun TimeSlot(
    time: MutableState<Int>,
    isRunning: MutableState<Boolean>,
    isFinished: MutableState<Boolean>,
    darkTheme: MutableState<Boolean>
) {
    Column {
        if (!isRunning.value) {
            IncreaseButton(time = time)
        } else {
            Spacer(
                modifier = Modifier
                    .width(65.dp)
                    .height(40.dp)
            )
        }
        Card(
            backgroundColor = if (darkTheme.value) black else grey200,
            modifier = Modifier
                .height(65.dp)
                .width(65.dp)
                .absolutePadding(left = 5.dp, right = 5.dp),
            shape = shapes.small,
            border = borderColor(isRunning, isFinished),
            elevation = 15.dp
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxHeight()
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(formatTime(time.value.toString()), style = timer)
                }
            }
        }
        if (!isRunning.value) {
            DecreaseButton(time = time)
        }
    }
}

@Composable
private fun borderColor(isRunning: MutableState<Boolean>, isFinished: MutableState<Boolean>): BorderStroke {
    return if (isRunning.value) {
        BorderStroke(2.dp, green400)
    } else if (isFinished.value) {
        BorderStroke(2.dp, red400)
    } else {
        BorderStroke(1.dp, grey400)
    }
}

private fun formatTime(time: String): String {
    var reformatted = time
    if (time.length == 1) {
        reformatted = "0$time"
    }
    return reformatted
}

@Composable
fun DecreaseButton(time: MutableState<Int>) {
    Button(
        onClick = {
            if (time.value > 0) {
                time.value = time.value - 1
            }
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.background,
        ),
        modifier = Modifier
            .width(65.dp)
            .height(40.dp)
            .padding(5.dp)
            .clip(shape = shapes.medium),
        border = BorderStroke(1.dp, grey400)
    ) {
        Icon(imageVector = Icons.Default.ExpandMore, contentDescription = null)
    }
}

@Composable
fun IncreaseButton(time: MutableState<Int>) {
    Button(
        onClick = { time.value = time.value + 1 },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.background,
        ),
        modifier = Modifier
            .width(65.dp)
            .height(40.dp)
            .padding(5.dp)
            .clip(shape = shapes.medium),
        border = BorderStroke(1.dp, grey400)
    ) {
        Icon(imageVector = Icons.Default.ExpandLess, contentDescription = null)
    }
}

@Composable
@Preview
fun PreviewIncreaseButton() {
    DecreaseButton(time = mutableStateOf(0))
}

@Composable
@Preview
fun PreviewTimeSlot() {
    TimeSlot(
        time = mutableStateOf(0),
        isRunning = mutableStateOf(false), isFinished = mutableStateOf(false), darkTheme = mutableStateOf(true)
    )
}

@Composable
fun WatchButton(state: WatchButtonState, isClicked: MutableState<Boolean>) {
    Button(
        onClick = { isClicked.value = !isClicked.value },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = selectButtonColor(state),
            contentColor = MaterialTheme.colors.primary
        ),
        modifier = Modifier
            .padding(10.dp)
            .clip(CircleShape),
    ) {
        Text(selectButtonText(state), style = typography.caption)
    }
}

fun selectButtonText(state: WatchButtonState): String {
    return when (state) {
        WatchButtonState.START -> "Start"
        WatchButtonState.PAUSE -> "Pause"
        WatchButtonState.CANCEL -> "Cancel"
    }
}

fun selectButtonColor(state: WatchButtonState): Color {
    return when (state) {
        WatchButtonState.START -> teal200
        WatchButtonState.PAUSE -> yellow400
        WatchButtonState.CANCEL -> grey400
    }
}

enum class WatchButtonState {
    START, PAUSE, CANCEL
}

@Composable
@Preview
fun WatchButtonPreview() {
    WatchButton(WatchButtonState.START, isClicked = mutableStateOf(false))
}

@Preview
@Composable
fun PreviewCircleWatch() {
    CircleWatch(darkTheme = mutableStateOf(true))
}

@Composable
fun SetStyleButton(text: String?, iconId: Int?, darkTheme: MutableState<Boolean>) {
    Button(
        onClick = { if (iconId != null) darkTheme.value = !darkTheme.value },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.background,
        ),
        modifier = Modifier
            .padding(5.dp)
            .height(36.dp)
    ) {
        text?.let {
            Text(text)
        }

        iconId?.let {
            Icon(painter = painterResource(id = iconId), contentDescription = null)
        }
    }
}

@Preview
@Composable
fun SetStyleButtonPreview() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        SetStyleButton(text = "Colors", null, darkTheme = mutableStateOf(false))
        SetStyleButton(text = "Watches", null, darkTheme = mutableStateOf(false))
        SetStyleButton(text = "Sounds", null, darkTheme = mutableStateOf(false))
        SetStyleButton(text = null, iconId = R.drawable.ic_baseline_bedtime_24, darkTheme = mutableStateOf(false))
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp(darkTheme = mutableStateOf(false))
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp(darkTheme = mutableStateOf(true))
    }
}
