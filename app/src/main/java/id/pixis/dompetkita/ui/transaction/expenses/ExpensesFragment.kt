package id.pixis.dompetkita.ui.transaction.expenses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.pixis.dompetkita.data.entity.Transactions
import id.pixis.dompetkita.databinding.FragmentExpensesBinding
import id.pixis.dompetkita.utils.Converter
import id.pixis.dompetkita.utils.Utils

@AndroidEntryPoint
class ExpensesFragment : Fragment() {

    private val binding : FragmentExpensesBinding by lazy {
        FragmentExpensesBinding.inflate(layoutInflater)
    }

    private val adapter: ExpensesAdapter by lazy {
        ExpensesAdapter (
            showDetail = {item -> showDetail(item)},
            deleteItem = {item -> deleteData(item)}
                )
    }

    private val viewModel : ExpensesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupData()
        setupViewModel()
    }

    private fun setupAdapter(){
        with(binding){
            rvExpenses.also {
                it.adapter = adapter
                it.layoutManager = LinearLayoutManager(
                        requireContext(), LinearLayoutManager.VERTICAL, false
                )
                it.setHasFixedSize(true)
            }
        }
    }

    private fun setupData(){
        viewModel.getExpenses(viewLifecycleOwner)
        viewModel.getTotalExpenses(Utils.getFirstDate(), Utils.getLastDate())
    }

    private fun setupViewModel(){
        with(binding){
            viewModel.data.observe(viewLifecycleOwner, adapter::submitList)
            viewModel.totalExpenses.observe(viewLifecycleOwner, {
                val totalAmount = Converter.currencyIdr(it.total.toInt())
                tvTotal.text = totalAmount?.replace(",00", "")
            })
        }
    }

    private fun showDetail(item: Transactions) {

    }

    private fun deleteData(item: Transactions) {
        viewModel.deleteData(item)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getTotalExpenses(Utils.getFirstDate(), Utils.getLastDate())
        viewModel.getExpenses(viewLifecycleOwner)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

}