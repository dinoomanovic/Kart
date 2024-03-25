package ba.android.kartapp.ui.drawer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ba.android.kartapp.R
import ba.android.kartapp.ui.components.debouncedClickable

/**
 * @author Dino Omanovic <dino.omanovic@propeller.ba>
 * Created on Mar, 25 2024
 */
@Composable
fun DrawerItem(
    menuItem: MenuItem,
    selectedDrawerItem: String,
    modifier: Modifier = Modifier,
    onItemClick: (MenuItem) -> Unit
) {
    Column(
        modifier = Modifier.debouncedClickable {
            onItemClick(menuItem)
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = menuItem.id,
                color = Color.White,
                modifier = modifier.padding(
                    start = if (selectedDrawerItem == menuItem.id) 32.dp else 27.dp
                )
            )
        }
        Divider(color = Color.Transparent)
    }
}