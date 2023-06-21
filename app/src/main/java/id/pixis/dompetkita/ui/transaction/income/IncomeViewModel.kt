package id.pixis.dompetkita.ui.transaction.income

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
class IncomeViewModel @Inject constructor(
        private val repository: Repository
) : ViewModel() {

    val data : MutableLiveData<PagedList<Transactions>> by lazy {
        MutableLiveData()
    }

    val delete : MutableLiveData<Boolean> by lazy {
        MutableLiveData()
    }

    val totalIncome : MutableLiveData<SumAmount> by lazy {
        MutableLiveData()
    }

    fun getIncome(owner: LifecycleOwner){
        repository.getAllIncome(owner, data)
    }

    fun getTotalIncome(startDate: String, endDate: String){
        repository.getTotalIncomeMonth(startDate, endDate, totalIncome)
    }

    fun deleteData(data : Transactions){
        repository.deleteTransaction(data, delete)
    }
}