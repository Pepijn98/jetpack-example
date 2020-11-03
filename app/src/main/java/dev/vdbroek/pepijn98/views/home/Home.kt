package dev.vdbroek.pepijn98.views.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.vdbroek.pepijn98.common.Carousel
import dev.vdbroek.pepijn98.openDialog
import dev.vdbroek.pepijn98.models.Nature
import dev.vdbroek.pepijn98.models.natureList1
import dev.vdbroek.pepijn98.models.natureList2

val padding = 16.dp
val corner = 10.dp
val elevation = 4.dp

interface Home {

    companion object {
        @Composable
        fun Content(
            scrollState: ScrollState,
            carouselState: LazyListState,
            onNatureClicked: (Nature) -> Unit
        ) {
            ScrollableColumn(
                modifier = Modifier.padding(0.dp),
                scrollState = scrollState
            ) {
                for (nature in natureList1) {
                    NatureRow(nature, onNatureClicked)
                }

                Carousel(
                    items = natureList1 + natureList2,
                    state = carouselState,
                    contentPadding = PaddingValues(top = padding, end = padding)
                ) { nature, _ ->
                    Card(
                        modifier = Modifier.height(220.dp - padding).width(220.dp).padding(start = padding),
                        shape = RoundedCornerShape(corner),
                        elevation = elevation
                    ) {
                        Image(
                            asset = imageResource(id = nature.image),
                            modifier = Modifier.size(220.dp)
                        )
                    }
                }

                for (nature in natureList2) {
                    NatureRow(nature, onNatureClicked)
                }
            }

            if (openDialog) {
                AlertDialog(
                    onDismissRequest = {
                        openDialog = false
                    },
                    title = {
                        Text(text = "New Nature")
                    },
                    text = {
                        Text(text = "Add new nature alert dialog!")
                    },
                    confirmButton = {
                        Button(onClick = {
                            openDialog = false
                        }) {
                            Text(text = "Confirm")
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun NatureRow(nature: Nature, onNatureClicked: (Nature) -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth().padding(
            start = padding,
            top = padding,
            end = padding,
            bottom = if (nature.id == 9) 74.dp else 0.dp
        )
    ) {
        Card(
            modifier = Modifier.fillMaxWidth().height(160.dp).clickable(onClick = { onNatureClicked(nature) }),
            shape = RoundedCornerShape(corner),
            elevation = elevation
        ) {
            Row(modifier = Modifier.fillMaxSize()) {
                Image(
                    modifier = Modifier.fillMaxHeight().width(160.dp),
                    asset = imageResource(id = nature.image),
                    alignment = Alignment.CenterStart
                )
                Text(
                    text = "Nature ${nature.id}",
                    modifier = Modifier.padding(
                        start = padding,
                        top = padding / 2
                    ),
                    fontWeight = FontWeight(900)
                )
            }
        }
    }
}