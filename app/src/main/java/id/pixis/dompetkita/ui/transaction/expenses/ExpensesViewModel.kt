package id.pixis.dompetkita.ui.transaction.expenses

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import dagger.hilt.android.lifecycle.HiltViewModel
import id.pixis.dompetkita.data.entity.Transactions
import id.pixis.dompetkita.data.model.SumAmount
import id.pixis.dompetkita.repository.Repository
import javax.inject.Inject

@HiltViewModel
class ExpensesViewModel @Inject constructor(
        private val repository: Repository
) : ViewModel() {

    val data : MutableLiveData<PagedList<Transactions>> by lazy {
        MutableLiveData()
    }

    val delete : MutableLiveData<Boolean> by lazy {
        MutableLiveData()
    }

    val totalExpenses: MutableLiveData<SumAmount> by lazy {
        MutableLiveData()
    }

    fun getExpenses(owner: LifecycleOwner){
        repository.getAllExpenses(owner, data)
    }

    fun getTotalExpenses(startDate: String, endDate: String){
        repository.getTotalExpensesMonth(startDate, endDate, totalExpenses)
    }

    fun deleteData(data : Transactions){
        repository.deleteTransaction(data, delete)
    }
}