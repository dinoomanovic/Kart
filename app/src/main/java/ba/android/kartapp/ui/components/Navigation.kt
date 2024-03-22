package ba.android.kartapp.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ba.android.kartapp.R

@Composable
fun Navigation(
    title: String,
    topIcons: List<NavigationIcons>,
    content: @Composable () -> Unit,
    onBack: (() -> Unit)? = null,
    bottomIcons: List<NavigationIcons>? = null,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        NavigationHeader(onBack, title, topIcons)
        Box(Modifier.weight(1f).background(Color.White)) {
            content()
        }
        if (bottomIcons?.isNotEmpty() == true)
            BottomNavigation(bottomIcons)
    }
}

data class NavigationIcons(
    @DrawableRes val image: Int,
    val onClick: () -> Unit,
    val text: String = ""
)

@Composable
fun NavigationHeader(
    onBack: (() -> Unit)?,
    title: String,
    icons: List<NavigationIcons>
) {
    Surface(
        color = Color.Black,
        contentColor = Color.White,
    ) {
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(20.dp))
            if (onBack != null) {
                IconButton(
                    image = R.drawable.ic_back, onClick = onBack,
                )
                Spacer(modifier = Modifier.width(20.dp))
            }
            Text(
                text = title,
                color = Color.White
            )
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                for (icon in icons) {
                    Spacer(modifier = Modifier.width(20.dp))
                    IconButton(
                        image = icon.image,
                        onClick = icon.onClick,
                        color = Color.Transparent
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
            }
        }
    }
}

@Composable
fun BottomNavigation(icons: List<NavigationIcons>) {
    Surface(
        color = Color.Black,
        contentColor = Color.White,
    ) {
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (icon in icons) {
                IconButton(
                    modifier = Modifier.weight(1f),
                    image = icon.image,
                    text = icon.text,
                    onClick = icon.onClick,
                    color = Color.White
                )
            }
        }
    }
}
