package ba.android.kartapp.ui.drawer

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalComposeUiApi::class
)
@Composable
fun DrawerBody(
    modifier: Modifier = Modifier,
    viewModel: DrawerBodyViewModel = hiltViewModel(),
    menuItems: List<MenuItem>,
    drawerState: DrawerState,
    scope: CoroutineScope,
    selectedDrawerItem: String,
    onItemClick: (MenuItem) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val scrollState = rememberLazyListState()

    BackHandler {
        keyboardController?.hide()
        scope.launch {
            drawerState.close()
        }
    }
    if (drawerState.isClosed) {
            keyboardController?.hide()
        }

    Column(
        modifier = modifier
            .fillMaxHeight()
            .width(293.dp)
    ) {
        LazyColumn(
            state = scrollState,
            modifier = Modifier
                .padding(top = 18.dp)
                .weight(1f)
        ) {
            items(menuItems) { item ->
                DrawerItem(
                    menuItem = item,
                    selectedDrawerItem = selectedDrawerItem,
                    modifier = modifier
                ) {
                    keyboardController?.hide()
                    scope.launch {
                        drawerState.close()
                    }
                    onItemClick(item)
                }
            }
        }
    }
}