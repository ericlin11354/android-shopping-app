package com.example.goshopping.data.repositories

import app.cash.turbine.test
import com.example.goshopping.data.network.ShoppingItemService
import com.example.goshopping.domain.ShoppingItem
import com.example.goshopping.rules.TestCoroutineRule
import com.example.goshopping.shoppingList
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import java.io.IOException

class ShoppingItemRepositoryImplTests {
    @get:Rule
    val coroutineRule = TestCoroutineRule()

    @Test
    fun `init starts with empty list of shopping items`() = runTest {
        // Arrange
        val expectedNetworkCountryList = shoppingList
        val mockShoppingItemService = mockk<ShoppingItemService> {
            coEvery { getAllShoppingItems() } returns Response.success(expectedNetworkCountryList)
        }

        // Act
        val sut = ShoppingItemRepositoryImpl(mockShoppingItemService)

        // Assert
        sut.shoppingListResultStream.test {
            assertEquals(Result.success<List<ShoppingItem>>(emptyList()), expectMostRecentItem())
            ensureAllEventsConsumed()
        }
    }

    @Test
    fun `fetchCountries updates list of shopping items`() = runTest {
        // Arrange
        val expectedNetworkCountryList = shoppingList
        val mockShoppingItemService = mockk<ShoppingItemService> {
            coEvery { getAllShoppingItems() } returns Response.success(expectedNetworkCountryList)
        }
        val sut = ShoppingItemRepositoryImpl(mockShoppingItemService)

        // Act
        sut.fetchShoppingItems()

        // Assert
        sut.shoppingListResultStream.test {
            assertEquals(Result.success(expectedNetworkCountryList), awaitItem())
            ensureAllEventsConsumed()
        }
    }

    @Test
    fun `fetchShoppingItems emits failure when network call fails`() = runTest {
        // Arrange
        val expectedException = IOException("Network error")
        val mockShoppingItemService = mockk<ShoppingItemService> {
            coEvery { getAllShoppingItems() } throws expectedException
        }
        val sut = ShoppingItemRepositoryImpl(mockShoppingItemService)

        // Act
        sut.fetchShoppingItems()

        // Assert
        sut.shoppingListResultStream.test {
            val result = awaitItem()
            Assert.assertTrue(result.isFailure)
            assertEquals(expectedException, result.exceptionOrNull())
            ensureAllEventsConsumed()
        }
    }
}
