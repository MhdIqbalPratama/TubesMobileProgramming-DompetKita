package id.pixis.dompetkita.ui.bill

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import dagger.hilt.android.lifecycle.HiltViewModel
import id.pixis.dompetkita.data.entity.Bill
import id.pixis.dompetkita.data.entity.Transactions
import id.pixis.dompetkita.data.model.SumAmount
import id.pixis.dompetkita.repository.Repository
import javax.inject.Inject

@HiltViewModel
class BillViewModel @Inject constructor(
        private val repository: Repository
) : ViewModel() {

    val data : MutableLiveData<PagedList<Bill>> by lazy {
        MutableLiveData()
    }

    val delete : MutableLiveData<Boolean> by lazy {
        MutableLiveData()
    }

    val totalBill : MutableLiveData<SumAmount> by lazy {
        MutableLiveData()
    }

    fun getData(owner: LifecycleOwner){
        repository.getAllBill(owner, data)
    }

    fun getTotalBill(){
        repository.getTotalBill(totalBill)
    }

    fun deleteData(data : Bill){
        repository.deleteBill(data, delete)
    }

}