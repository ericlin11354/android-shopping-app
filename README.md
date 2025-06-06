# Explanation
Eric's "Go Shopping" App is a simple Android app that allows the user to:
1. Select items they wish to add to their shopping card
2. Proceed to checkout with shopping card and give the option to apply coupon code (code is "ZACH")
3. Upon checkout, redirects to a confirmation page informing the user their checkout was successful

[Screen_recording_20250606_001614.webm](https://github.paypal.com/elin/android-bootcamp-capstone/assets/61741/c5fb0253-b5e9-4cc7-8b60-db79dfae2589)

# Rubric Criteria:
- App UI is written in Jetpack Compose and utilizes Compose navigation
- App has a custom icon
- `main` branch contains the latest version
- App has atleast three unique screens including ShoppingListScreen, CheckoutScreen, and SuccessScreen
  - App uses AsyncImage, styled text, as well as a loading animation
- App uses Retrofit to call API endpoint: https://fakestoreapi.com/products
  - No API key needed. API is also free to use
- App prompts the user with a RetryableError upon API network call failure
  - This ensures the user can always see the shopping item catalogue
- App uses PrefDataStore in order to save user screen orientation preference
- App uses Kotlin Coroutines to fetch shopping items
- App is built on minSdk = 24 and supports Android 7+
- All App screens work successfully with well-organized UI
  - App business logic prevents possible crashing
- App uses MVVM architecture
  - ViewModels feature one StateFlow for associated data representation
- Project source files are organized within their own packges
  - Reusable composables in their own files
- Code contains meaningful documentation
- ShoppingItemRepository features three test cases
