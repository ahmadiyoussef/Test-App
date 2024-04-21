package com.example.testapp.medicineList

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.data.models.Medicine
import com.example.testapp.repository.MedicineRepository
import com.example.testapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MedicineViewModel @Inject constructor(
    private val repository: MedicineRepository
) : ViewModel() {

    val medicines = MutableLiveData<List<Medicine>>()
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    init {
        viewModelScope.launch {
            val result = repository.getMedicineList()
            when (result) {
                is Resource.Success -> {
                    medicines.value = result.data!!.medicines!!
                }

                is Resource.Error -> {
                    loadError.value = result.message!!
                    isLoading.value = false
                }

                else -> {

                }
            }

        }
    }
}