package ba.android.kartapp.ui.screen.splashscreen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ba.android.kartapp.R

@Composable
fun SplashScreen(navigateToHome: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1D206B),
                        Color(0xFF4E15C5),
                        Color(0xFF6610F2),
                    )
                )
            )
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter)
        ) {
            CartAnimation(
                navigateToHome,
                modifier = Modifier
            )

            Text(
                text = stringResource(R.string.kart),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 65.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight(1000),
                color = Color(0xFFF5F5F5)
            )

            Text(
                text = stringResource(R.string.shopping_with_style),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight(400),
                color = Color(0xFFF5F5F5)
            )
        }
    }
}

@Composable
fun CartAnimation(
    navigateToLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    var currentRotation by remember { mutableFloatStateOf(0f) }

    val rotation = remember { Animatable(currentRotation) }

    LaunchedEffect(key1 = 1) {
        rotation.animateTo(
            targetValue = currentRotation + 360f,
            animationSpec = repeatable(
                animation = tween(1000, easing = LinearEasing),
                iterations = 1
            )
        ) {
            currentRotation = value
        }

        navigateToLogin()
    }
    Cart(modifier = modifier.padding(24.dp), rotationDegrees = rotation.value)
}

@Composable
fun Cart(
    modifier: Modifier = Modifier,
    rotationDegrees: Float = 0f
) {
    Box(
        modifier = modifier
            .aspectRatio(1.0f)
            .clip(CircleShape)
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(0.3f)
                .align(Alignment.Center)
                .rotate(rotationDegrees)
                .aspectRatio(1.0f),
            painter = painterResource(R.drawable.ic_cart),
            contentDescription = stringResource(id = R.string.app_name)
        )
    }
}
