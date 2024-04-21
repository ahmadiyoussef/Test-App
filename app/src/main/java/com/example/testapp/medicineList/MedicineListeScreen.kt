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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.testapp.R
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
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Greeting(username)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Medicine list with a card
        MedicineList(viewModel = viewModel, onMedicineClick = onMedicineClick)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Greeting(username: String) {
    val greeting = when (LocalTime.now().hour) {
        in 5..11 -> stringResource(R.string.good_morning)
        in 12..16 -> stringResource(R.string.good_afternoon)
        else -> stringResource(R.string.good_evening)
    }
    Text(
        text = "$greeting, $username!",
        style = MaterialTheme.typography.headlineMedium,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}


@Composable
fun MedicineList(viewModel: MedicineViewModel, onMedicineClick: (Medicine) -> Unit) {
    val medicines by viewModel.medicines.observeAsState(listOf())

    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(medicines) { medicine ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { onMedicineClick(medicine) },
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Name: ${medicine.name}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Dose: ${medicine.dose}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Strength: ${medicine.strength}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}



