package com.example.fetchitemslist.feature_items_list.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.fetchitemslist.R
import com.example.fetchitemslist.feature_items_list.states.FetchUiState
import com.example.fetchitemslist.feature_items_list.viewmodel.FetchViewModel

@Composable
fun SearchView(fetchViewModel: FetchViewModel, modifier: Modifier) {
    val fetch by fetchViewModel.item.collectAsState()

    when(fetch) {
        is FetchUiState.Loading -> {
            CircularProgressIndicator()
        }
        is FetchUiState.Success -> {
            val item = (fetch as FetchUiState.Success).data
            val groupedItems = item.sortedWith(compareBy({it.listId}, {it.name}))
                .groupBy { it.listId }

            LazyColumn(
                modifier = modifier.padding(8.dp)
            ) {
                groupedItems.forEach { (listId, itemsForListId) ->
                    item {
                        listId?.let { HeaderRow(it) }
                    }
                    itemsIndexed(itemsForListId) { _, item ->
                        if (!item.name.isNullOrEmpty()) {
                            Card (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(2.dp)
                            ) {
                                Text(text = "${item.listId?.let { stringResource(R.string.listid, it) }}", modifier = Modifier.padding(4.dp))
                                Text(text = stringResource(R.string.name, item.name ?: "Unknown"), modifier = Modifier.padding(4.dp))
                                Text(text = "${item.id?.let { stringResource(R.string.id, it) }}", modifier = Modifier.padding(4.dp))
                            }
                        }
                    }
                }
            }
        }
        is FetchUiState.Error -> {
            Text(text = "Error: Something happened")
        }
    }
}

@Composable
fun HeaderRow(listId: Int) {
    Text(
        text = "ListId: $listId",
        modifier = Modifier.padding(16.dp),
        style = androidx.compose.material3.MaterialTheme.typography.titleMedium
    )
}