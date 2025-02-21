package com.example.fetchitemslist.feature_items_list.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.fetchitemslist.feature_items_list.model.data.FetchItemsResponse
import com.example.fetchitemslist.feature_items_list.model.repo.FetchRepository
import com.example.fetchitemslist.feature_items_list.states.FetchUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.amshove.kluent.shouldBeEqualTo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import kotlin.test.assertIs

@ExperimentalCoroutinesApi
class FetchViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private val mockRepository: FetchRepository = mock()
    private val viewModel = FetchViewModel(mockRepository)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `verify view model emits loading and success`() = runTest {
        // Given
        val mockItems = listOf(
            FetchItemsResponse(1, 2, "A"),
            FetchItemsResponse(3, 4, "B")
        )

        // When
        `when`(mockRepository.getFetch()).thenReturn(
            flow {
                emit(FetchUiState.Loading)
                emit(FetchUiState.Success(mockItems))
            }
        )

        val collectedStates = mutableListOf<FetchUiState>()

        val job = launch {
            viewModel.item.collect { state ->
                collectedStates.add(state)
            }
        }

        viewModel.fetchItems()

        // Allow time for fetchItems coroutine to complete
        advanceUntilIdle()

        // Assert
        2 shouldBeEqualTo collectedStates.size
        FetchUiState.Loading shouldBeEqualTo collectedStates[0]
        assertIs<FetchUiState.Success>(collectedStates[1])
        mockItems shouldBeEqualTo (collectedStates[1] as FetchUiState.Success).data

        job.cancel()
    }
}