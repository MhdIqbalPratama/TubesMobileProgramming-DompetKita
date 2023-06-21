package id.pixis.dompetkita.ui.transaction.expenses


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.pixis.dompetkita.data.entity.Transactions
import id.pixis.dompetkita.databinding.AdapterExpensesBinding
import id.pixis.dompetkita.utils.Converter
import id.pixis.dompetkita.utils.Converter.currencyIdr
import id.pixis.dompetkita.utils.Utils

class ExpensesAdapter (
    private val showDetail: (Transactions) -> Unit,
    private val deleteItem: (Transactions) -> Unit
) : PagedListAdapter<Transactions, ExpensesAdapter.ViewHolder>(DIFF_CALLBACK) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.view){
            if(getItem(position) != null){
                tvTitle.text = getItem(position)?.name
                tvCategory.text = getItem(position)?.category
                tvAmount.text = getItem(position)?.amount?.let {
                    currencyIdr(it)?.replace(",00","")
                }
                tvDate.text = getItem(position)?.date?.let {
                    Converter.dateFormat(
                        it,
                        "yyyyMMdd",
                        "dd MMMM yyyy"
                    )
                }
                getItem(position)?.icon?.let {
                    Utils.getDrawableIdFromFileName(
                        imgThumbnail.context,
                        it
                    )
                }?.let { imgThumbnail.setImageResource(it) }
                root.setOnClickListener {
                    showDetail.invoke(getItem(position)!!)
                    layoutDelete.visibility = View.GONE
                }
                root.setOnLongClickListener {
                    layoutDelete.visibility = View.VISIBLE
                    true
                }

                layoutDelete.setOnClickListener {
                    deleteItem.invoke(getItem(position)!!)
                    layoutDelete.visibility = View.GONE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
            AdapterExpensesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    class ViewHolder(val view: AdapterExpensesBinding) : RecyclerView.ViewHolder(view.root)
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Transactions>(){
            override fun areItemsTheSame(oldItem: Transactions, newItem: Transactions): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Transactions, newItem: Transactions): Boolean {
                return (
                    oldItem.id == newItem.id &&
                    oldItem.category == newItem.category
                )
            }
        }
    }
}