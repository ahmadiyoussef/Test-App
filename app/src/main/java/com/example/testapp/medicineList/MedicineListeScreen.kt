package com.example.testapp.medicineList

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testapp.data.models.Medicine
import java.time.LocalTime


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MedicineListScreen(
    username: String,
    viewModel: MedicineViewModel = hiltViewModel(),
    onMedicineClick: (Medicine) -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Greeting(username)
        Spacer(modifier = Modifier.height(16.dp))  // Add some spacing between components
        MedicineList(viewModel = viewModel, onMedicineClick = onMedicineClick)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Greeting(username: String) {
    val greeting = when (LocalTime.now().hour) {
        in 5..11 -> "Good Morning"
        in 12..16 -> "Good Afternoon"
        else -> "Good Evening"
    }
    Text(text = "$greeting, $username!")
}

@Composable
fun MedicineList(viewModel: MedicineViewModel, onMedicineClick: (Medicine) -> Unit) {
    val medicines by viewModel.medicines.observeAsState(listOf())

    LazyColumn {
        items(medicines) { medicine ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { onMedicineClick(medicine) }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Name: ${medicine.name}")
                    Text("Dose: ${medicine.dose}")
                    Text("Strength: ${medicine.strength}")
                }
            }
        }
    }
}