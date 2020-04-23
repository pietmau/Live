# Live

## Assumptions
- When showing the detail from **Content Detail Endpoint** I update only the `body`, I assume 
for simplicity that the other data that we received in the **Content List Endpoint** are not changed.
- I assume the bottom sheet fragment is a reasonable solution to show the detail (instead of a full screen Activity/Fragment).
- I assume the **Mockk.io** and **AssertJ** libraries are acceptable for testing.
- I assume the **Google Material Components for Android** library is acceptable.
- I assume **JetBrains Kotlin Coroutines** are acceptable.

## Approach
- **MVVM**: ViewStates + Commands + TransientEvents (for navigation) 
- `ViewModel` + `LiveData`
- Coroutines + `Flow`
- Mockk.io for mocking + AssertJ
- Dagger, Retrofit 
- **Unit tests** and **UI (Espresso) tests** in the respective directories
- `RecyclerView` with `DiffUtils` (`ListAdapter`)

## TODO
- Detail fragment needs improvement
- Use Clean Architecture + modularization
- Use Retrofit interceptors to better handle the response (to skip the redundant outer container of the responses)
- Main Activity should be a Fragment
- Use `onSaveInstanceState()` (`SavedStateHandle`) to handle process death
- Offline behaviour?
- More tests