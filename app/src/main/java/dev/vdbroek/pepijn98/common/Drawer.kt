package dev.vdbroek.pepijn98.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.WithConstraints
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.vdbroek.pepijn98.ui.ThemeState
import dev.vdbroek.pepijn98.ui.blue200
import dev.vdbroek.pepijn98.ui.blue500
import dev.vdbroek.pepijn98.ui.blue700
import dev.vdbroek.pepijn98.R

@Composable
fun Drawer() {
    Column(modifier = Modifier.fillMaxHeight()) {
        WithConstraints(modifier = Modifier.fillMaxWidth().height(180.dp)) {
            Box(modifier = Modifier.fillMaxSize().background(
                LinearGradient(
                    0.0f to blue200,
                    0.5f to blue500,
                    1.0f to blue700,
                    startX = 0.0f,
                    startY = 0.0f,
                    endX = with(DensityAmbient.current) { constraints.maxWidth.dp.toPx() },
                    endY = with(DensityAmbient.current) { constraints.maxHeight.dp.toPx() }
                )
            )) {
                Box(modifier = Modifier.fillMaxSize().padding(8.dp)) {
                    Column(modifier = Modifier.align(Alignment.BottomStart)) {
                        Image(
                            asset = imageResource(id = R.drawable.profile),
                            modifier = Modifier.clip(CircleShape).preferredSize(100.dp)
                        )
                        Text(
                            text = "Pepijn van den Broek",
                            modifier = Modifier.padding(top = 8.dp, end = 8.dp, bottom = 8.dp),
                            fontWeight = FontWeight(900),
                            color = Color.White
                        )
                    }
                }
            }
        }
        DrawerRow(
            title = "Light Theme",
            selected = !ThemeState.isDark,
            onClick = {
                ThemeState.override = true
                ThemeState.isDark = false
            }
        )
        DrawerRow(
            title = "Dark Theme",
            selected = ThemeState.isDark,
            onClick = {
                ThemeState.override = true
                ThemeState.isDark = true
            }
        )
    }
}

@Composable
private fun DrawerRow(title: String, selected: Boolean, onClick: () -> Unit) {
    val background = if (selected) MaterialTheme.colors.primary.copy(alpha = 0.12f) else Color.Transparent
    val textColor = if (selected) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
    ListItem(modifier = Modifier.clickable(onClick = onClick).background(background)) {
        Text(color = textColor, text = title)
    }
}
