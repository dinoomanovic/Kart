package ba.android.kartapp.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

@Composable
fun MyIcon(@DrawableRes id: Int, color: Color? = null) {
    val tint = color ?: contentColorFor(Color.Gray)
    Icon(
        painter = painterResource(id = id),
        tint = tint,
        contentDescription = null
    )
}