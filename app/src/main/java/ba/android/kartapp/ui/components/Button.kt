package ba.android.kartapp.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ba.android.kartapp.ui.theme.ThemedPreview

@Composable
fun ActionButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    Button(modifier = modifier.height(48.dp), onClick = onClick) {
        TextButton(text = text)
    }
}

@Composable
fun TextButton(text: String) {
    Text(
        text = text,
        color = Color.Black
    )
}

@Composable
fun IconButton(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int,
    text: String = "",
    onClick: () -> Unit,
    color: Color? = null
) {
    Column(
        modifier = modifier.clickable { onClick() },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = modifier
                .padding(4.dp)
                .size(18.dp),
            painter = painterResource(id = image),
            contentDescription = null
        )
        if (text.isNotBlank()) Text(text = text)
    }
}

@Composable
fun ProvideScreen(text: String = "") = IconButton(text = text, image = ba.android.kartapp.R.drawable.ic_plus,
    color = Color.White, onClick = {})

@Preview
@Composable
fun IconButtonPreview() {
    ThemedPreview {
        ProvideScreen()
    }
}

@Preview
@Composable
fun IconButtonDarkPreview() {
    ThemedPreview(darkTheme = true) {
        ProvideScreen()
    }
}

@Preview
@Composable
fun IconTextButtonPreview() {
    ThemedPreview {
        ProvideScreen("Test")
    }
}

@Preview
@Composable
fun IconTextButtonDarkPreview() {
    ThemedPreview(darkTheme = true) {
        ProvideScreen("Test")
    }
}

@Preview
@Composable
fun MyButtonPreview() {
    ThemedPreview {
        ActionButton(text = "Click me") {}
    }
}

@Preview
@Composable
fun MyButtonPreviewDark() {
    ThemedPreview(darkTheme = true) {
        ActionButton(text = "Click me") {}
    }
}
