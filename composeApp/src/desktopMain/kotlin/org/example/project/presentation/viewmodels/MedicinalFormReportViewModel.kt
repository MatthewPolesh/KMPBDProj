package org.example.project.presentation.viewmodels



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.project.domain.entities.MedicinalFormReport
import org.example.project.domain.repositories.MedicinalFormReportRepository

class MedicinalFormReportViewModel(
    private val repository: MedicinalFormReportRepository
) : ViewModel() {

    private val _items = MutableStateFlow<List<MedicinalFormReport>>(emptyList())
    val items: StateFlow<List<MedicinalFormReport>> = _items

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchItems() {
        viewModelScope.launch {
            repository.getAll()
                .onSuccess { _items.value = it }
                .onFailure { _error.value = it.message }
        }
    }

    fun addItem(item: MedicinalFormReport) {
        viewModelScope.launch {
            repository.add(item)
                .onSuccess { fetchItems() }
                .onFailure { _error.value = it.message }
        }
    }

    fun updateItem(item: MedicinalFormReport) {
        viewModelScope.launch {
            repository.update(item)
                .onSuccess { fetchItems() }
                .onFailure { _error.value = it.message }
        }
    }

    fun deleteItem(id: Int) {
        viewModelScope.launch {
            repository.delete(id)
                .onSuccess { fetchItems() }
                .onFailure { _error.value = it.message }
        }
    }
}
