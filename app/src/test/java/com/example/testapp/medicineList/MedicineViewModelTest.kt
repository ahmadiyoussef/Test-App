package com.example.testapp.medicineList

import com.example.testapp.data.models.Medicine
import com.example.testapp.repository.MedicineRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.*
import org.mockito.Mockito.*
import org.mockito.*


class MedicineViewModelTest {
    @get:Rule
    val coroutineTestRule = TestCoroutineRule()

    @Mock
    lateinit var repository: MedicineRepository

    private lateinit var viewModel: MedicineViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = MedicineViewModel(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test data retrieval on initialization`() = coroutineTestRule.runBlockingTest {
        val testMedicines = listOf(
            Medicine("Aspirin", "500mg", "2 Tablets"),
        )

        `when`(repository.getMedicineList()).thenReturn(testMedicines)

        val result = repository.getMedicineList()
        Assert.assertEquals(testMedicines, result)
    }

}
