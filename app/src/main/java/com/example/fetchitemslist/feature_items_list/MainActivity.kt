package com.example.fetchitemslist.feature_items_list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.fetchitemslist.R
import com.example.fetchitemslist.feature_items_list.intent.FetchItemsIntent
import com.example.fetchitemslist.feature_items_list.model.network.FetchApiClient
import com.example.fetchitemslist.feature_items_list.model.repo.FetchRepository
import com.example.fetchitemslist.feature_items_list.view.SearchView
import com.example.fetchitemslist.feature_items_list.viewmodel.ViewModelFactory
import com.example.fetchitemslist.feature_items_list.viewmodel.FetchViewModel
import com.example.fetchitemslist.ui.theme.FetchItemsListTheme

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: FetchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val repo = FetchRepository(FetchApiClient.apiService)
        val vmFactory = ViewModelFactory(repo)
        viewModel = ViewModelProvider(this, vmFactory)[FetchViewModel::class.java]

        viewModel.handleIntent(FetchItemsIntent.FetchData)
        setContent {
            FetchItemsListTheme {
                Scaffold(modifier = Modifier.padding(8.dp, 36.dp, 8.dp, 4.dp)) { innerPadding ->
                    Text(text = stringResource(R.string.items_list), style = TextStyle(fontSize = 24.sp))
                    SearchView(
                        viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchViewPreview() {
    // Mock FetchViewModel for preview purposes
    val mockViewModel = FetchViewModel(FetchRepository(FetchApiClient.apiService))

    FetchItemsListTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            SearchView(
                fetchViewModel = mockViewModel,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}