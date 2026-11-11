package com.example.beastmode_2.Presentation.AndroidSplashScreen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.beastmode_2.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun FeastModeSplashScreen(
    onFinished: () -> Unit
) {

    val orange = Color(0xFFFF641F)
    val lightOrange = Color(0xFFFF8A3D)

    // ----------------------------------------
    // ANIMATION STATES
    // ----------------------------------------

    val dotAlpha = remember {
        Animatable(0f)
    }

    val logoAlpha = remember {
        Animatable(0f)
    }

    val logoScale = remember {
        Animatable(0.55f)
    }

    val textAlpha = remember {
        Animatable(0f)
    }

    val foodAlpha = remember {
        Animatable(0f)
    }

    val foodOffset = remember {
        Animatable(40f)
    }

    val loadingProgress = remember {
        Animatable(0f)
    }

    val lineAlpha = remember {
        Animatable(0f)
    }

    // ----------------------------------------
    // INFINITE FLOATING ANIMATION
    // ----------------------------------------

    val infiniteTransition = rememberInfiniteTransition(
        label = "floating"
    )

    val floating = infiniteTransition.animateFloat(
        initialValue = -5f,
        targetValue = 5f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1800,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "floating"
    )

    // ----------------------------------------
    // MAIN ANIMATION
    // ----------------------------------------

    LaunchedEffect(Unit) {

        // ------------------------------------
        // 1. ORANGE DOT APPEARS
        // ------------------------------------

        dotAlpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(350)
        )

        delay(250)

        // ------------------------------------
        // 2. LOGO APPEARS
        // ------------------------------------

        launch {

            logoAlpha.animateTo(
                1f,
                animationSpec = tween(
                    durationMillis = 500
                )
            )
        }

        launch {

            logoScale.animateTo(
                1.08f,
                animationSpec = tween(
                    durationMillis = 450,
                    easing = FastOutSlowInEasing
                )
            )

            logoScale.animateTo(
                0.96f,
                animationSpec = tween(150)
            )

            logoScale.animateTo(
                1f,
                animationSpec = tween(180)
            )
        }

        delay(500)

        // ------------------------------------
        // 3. FOOD ELEMENTS ENTER
        // ------------------------------------

        launch {

            foodAlpha.animateTo(
                1f,
                animationSpec = tween(700)
            )
        }

        launch {

            foodOffset.animateTo(
                0f,
                animationSpec = tween(
                    durationMillis = 900,
                    easing = FastOutSlowInEasing
                )
            )
        }

        delay(300)

        // ------------------------------------
        // 4. BRAND TEXT
        // ------------------------------------

        textAlpha.animateTo(
            1f,
            animationSpec = tween(700)
        )

        delay(250)

        // ------------------------------------
        // 5. SMALL LINE
        // ------------------------------------

        lineAlpha.animateTo(
            1f,
            animationSpec = tween(400)
        )

        delay(300)

        // ------------------------------------
        // 6. LOADING BAR
        // ------------------------------------

        loadingProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1200,
                easing = LinearEasing
            )
        )

        delay(400)

        // ------------------------------------
        // 7. GO TO HOME
        // ------------------------------------

        onFinished()
    }

    // ----------------------------------------
    // SCREEN
    // ----------------------------------------

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.White,
                        Color(0xFFFFFBF7),
                        Color(0xFFFFF4E9)
                    )
                )
            )
    ) {

        // ------------------------------------
        // FOOD DECORATIONS
        // ------------------------------------

        FoodDecorations(
            alpha = foodAlpha.value,
            offsetY = foodOffset.value + floating.value,
            orange = orange
        )

        // ------------------------------------
        // MAIN CONTENT
        // ------------------------------------

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // --------------------------------
            // PULSE BEHIND LOGO
            // --------------------------------

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(230.dp)
            ) {

                Canvas(
                    modifier = Modifier.fillMaxSize()
                ) {

                    val center = Offset(
                        size.width / 2,
                        size.height / 2
                    )

                    // soft glow

                    drawCircle(
                        color = orange.copy(
                            alpha = 0.05f * dotAlpha.value
                        ),
                        radius = 110f,
                        center = center
                    )

                    drawCircle(
                        color = orange.copy(
                            alpha = 0.08f * dotAlpha.value
                        ),
                        radius = 85f,
                        center = center
                    )

                    drawCircle(
                        color = orange.copy(
                            alpha = 0.12f * dotAlpha.value
                        ),
                        radius = 60f,
                        center = center
                    )

                    // center dot

                    drawCircle(
                        color = orange.copy(
                            alpha = dotAlpha.value
                        ),
                        radius = 7f,
                        center = center
                    )
                }

                // --------------------------------
                // LOGO
                // --------------------------------

                Image(
                    painter = painterResource(
                        id = R.drawable.splash_icon
                    ),
                    contentDescription = "FeastMode",
                    modifier = Modifier
                        .size(145.dp)
                        .scale(logoScale.value)
                        .alpha(logoAlpha.value)
                )
            }

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            // --------------------------------
            // FEASTMODE TEXT
            // --------------------------------

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.alpha(textAlpha.value)
            ) {

                Text(
                    text = "FeastMode",
                    fontSize = 38.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = orange
                )

                Spacer(
                    modifier = Modifier.height(5.dp)
                )

                Text(
                    text = "Cook. Create. Enjoy.",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray
                )
            }

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            // --------------------------------
            // SMALL ORANGE LINE
            // --------------------------------

            Canvas(
                modifier = Modifier
                    .width(40.dp)
                    .height(6.dp)
                    .alpha(lineAlpha.value)
            ) {

                drawRoundRect(
                    color = lightOrange,
                    cornerRadius = CornerRadius(10f),
                    size = Size(
                        size.width,
                        size.height
                    )
                )
            }

            Spacer(
                modifier = Modifier.height(35.dp)
            )

            // --------------------------------
            // LOADING BAR
            // --------------------------------

            Box(
                modifier = Modifier
                    .width(210.dp)
                    .height(6.dp)
            ) {

                // Background

                Canvas(
                    modifier = Modifier.fillMaxSize()
                ) {

                    drawRoundRect(
                        color = orange.copy(
                            alpha = 0.18f
                        ),
                        cornerRadius = CornerRadius(20f)
                    )

                    drawRoundRect(
                        color = orange,
                        size = Size(
                            width = size.width *
                                    loadingProgress.value,
                            height = size.height
                        ),
                        cornerRadius = CornerRadius(20f)
                    )
                }
            }
        }
    }
}
@Composable
private fun FoodDecorations(
    alpha: Float,
    offsetY: Float,
    orange: Color
) {

    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {

        // ==========================================
        // CHEF HAT - TOP LEFT
        // ==========================================

        val hatX = size.width * 0.12f
        val hatY = size.height * 0.20f + offsetY

        drawCircle(
            color = orange.copy(alpha = 0.14f * alpha),
            radius = 17f,
            center = Offset(hatX, hatY)
        )

        drawCircle(
            color = orange.copy(alpha = 0.14f * alpha),
            radius = 20f,
            center = Offset(
                hatX + 25f,
                hatY - 7f
            )
        )

        drawCircle(
            color = orange.copy(alpha = 0.14f * alpha),
            radius = 17f,
            center = Offset(
                hatX + 50f,
                hatY
            )
        )

        drawRoundRect(
            color = orange.copy(alpha = 0.14f * alpha),
            topLeft = Offset(
                hatX - 5f,
                hatY + 5f
            ),
            size = Size(65f, 22f),
            cornerRadius = CornerRadius(8f)
        )


        // ==========================================
        // SPARKLE - TOP RIGHT
        // ==========================================

        val sparkleX = size.width * 0.86f
        val sparkleY = size.height * 0.21f + offsetY

        drawLine(
            color = orange.copy(alpha = 0.35f * alpha),
            start = Offset(
                sparkleX,
                sparkleY - 15f
            ),
            end = Offset(
                sparkleX,
                sparkleY + 15f
            ),
            strokeWidth = 3f
        )

        drawLine(
            color = orange.copy(alpha = 0.35f * alpha),
            start = Offset(
                sparkleX - 15f,
                sparkleY
            ),
            end = Offset(
                sparkleX + 15f,
                sparkleY
            ),
            strokeWidth = 3f
        )


        // ==========================================
        // FORK - LEFT
        // ==========================================

        val forkX = size.width * 0.12f
        val forkY = size.height * 0.39f + offsetY

        drawLine(
            color = orange.copy(alpha = 0.17f * alpha),
            start = Offset(
                forkX,
                forkY
            ),
            end = Offset(
                forkX,
                forkY + 75f
            ),
            strokeWidth = 5f
        )

        for (i in 0..2) {

            drawLine(
                color = orange.copy(alpha = 0.17f * alpha),
                start = Offset(
                    forkX - 13f + i * 13f,
                    forkY - 3f
                ),
                end = Offset(
                    forkX - 13f + i * 13f,
                    forkY + 30f
                ),
                strokeWidth = 3f
            )
        }


        // ==========================================
        // SPOON - RIGHT
        // ==========================================

        val spoonX = size.width * 0.86f
        val spoonY = size.height * 0.40f + offsetY

        drawOval(
            color = orange.copy(alpha = 0.17f * alpha),
            topLeft = Offset(
                spoonX - 13f,
                spoonY - 10f
            ),
            size = Size(27f, 38f)
        )

        drawLine(
            color = orange.copy(alpha = 0.17f * alpha),
            start = Offset(
                spoonX,
                spoonY + 20f
            ),
            end = Offset(
                spoonX - 5f,
                spoonY + 75f
            ),
            strokeWidth = 5f
        )


        // ==========================================
        // LEAF - LEFT MIDDLE
        // ==========================================

        drawOval(
            color = orange.copy(alpha = 0.15f * alpha),
            topLeft = Offset(
                size.width * 0.08f,
                size.height * 0.58f + offsetY
            ),
            size = Size(25f, 45f)
        )

        drawLine(
            color = orange.copy(alpha = 0.18f * alpha),
            start = Offset(
                size.width * 0.09f,
                size.height * 0.62f + offsetY
            ),
            end = Offset(
                size.width * 0.14f,
                size.height * 0.58f + offsetY
            ),
            strokeWidth = 2f
        )


        // ==========================================
        // LEAF - RIGHT MIDDLE
        // ==========================================

        drawOval(
            color = orange.copy(alpha = 0.15f * alpha),
            topLeft = Offset(
                size.width * 0.87f,
                size.height * 0.58f + offsetY
            ),
            size = Size(25f, 45f)
        )


        // ==========================================
        // COOKING POT - BOTTOM LEFT
        // ==========================================

        val potX = size.width * 0.19f
        val potY = size.height * 0.77f + offsetY

        drawRoundRect(
            color = orange.copy(alpha = 0.15f * alpha),
            topLeft = Offset(
                potX,
                potY
            ),
            size = Size(75f, 40f),
            cornerRadius = CornerRadius(10f)
        )

        drawOval(
            color = orange.copy(alpha = 0.15f * alpha),
            topLeft = Offset(
                potX - 3f,
                potY - 10f
            ),
            size = Size(81f, 20f)
        )

        // steam

        drawLine(
            color = orange.copy(alpha = 0.14f * alpha),
            start = Offset(
                potX + 25f,
                potY - 15f
            ),
            end = Offset(
                potX + 25f,
                potY - 40f
            ),
            strokeWidth = 3f
        )

        drawLine(
            color = orange.copy(alpha = 0.14f * alpha),
            start = Offset(
                potX + 50f,
                potY - 15f
            ),
            end = Offset(
                potX + 50f,
                potY - 38f
            ),
            strokeWidth = 3f
        )


        // ==========================================
        // PAN - BOTTOM RIGHT
        // ==========================================

        val panX = size.width * 0.61f
        val panY = size.height * 0.78f + offsetY

        drawOval(
            color = orange.copy(alpha = 0.15f * alpha),
            topLeft = Offset(
                panX,
                panY
            ),
            size = Size(90f, 38f)
        )

        drawLine(
            color = orange.copy(alpha = 0.15f * alpha),
            start = Offset(
                panX + 85f,
                panY + 15f
            ),
            end = Offset(
                panX + 135f,
                panY - 8f
            ),
            strokeWidth = 7f
        )


        // ==========================================
        // FLOATING DOTS
        // ==========================================

        drawCircle(
            color = orange.copy(alpha = 0.22f * alpha),
            radius = 8f,
            center = Offset(
                size.width * 0.09f,
                size.height * 0.31f + offsetY
            )
        )

        drawCircle(
            color = orange.copy(alpha = 0.17f * alpha),
            radius = 5f,
            center = Offset(
                size.width * 0.91f,
                size.height * 0.32f + offsetY
            )
        )

        drawCircle(
            color = orange.copy(alpha = 0.18f * alpha),
            radius = 7f,
            center = Offset(
                size.width * 0.10f,
                size.height * 0.70f + offsetY
            )
        )

        drawCircle(
            color = orange.copy(alpha = 0.16f * alpha),
            radius = 8f,
            center = Offset(
                size.width * 0.90f,
                size.height * 0.70f + offsetY
            )
        )


        // ==========================================
        // SMALL HEARTS
        // ==========================================

        drawCircle(
            color = orange.copy(alpha = 0.13f * alpha),
            radius = 7f,
            center = Offset(
                size.width * 0.25f,
                size.height * 0.55f + offsetY
            )
        )

        drawCircle(
            color = orange.copy(alpha = 0.13f * alpha),
            radius = 7f,
            center = Offset(
                size.width * 0.75f,
                size.height * 0.55f + offsetY
            )
        )
    }
}