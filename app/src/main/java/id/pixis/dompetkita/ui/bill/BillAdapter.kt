package id.pixis.dompetkita.ui.bill

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.pixis.dompetkita.data.entity.Bill
import id.pixis.dompetkita.data.entity.Transactions
import id.pixis.dompetkita.databinding.AdapterBillBinding
import id.pixis.dompetkita.utils.Converter
import id.pixis.dompetkita.utils.Converter.currencyIdr
import id.pixis.dompetkita.utils.Utils

class BillAdapter (
    private val showDetail: (Bill) -> Unit,
    private val deleteItem: (Bill) -> Unit
) : PagedListAdapter<Bill, BillAdapter.ViewHolder>(DIFF_CALLBACK) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.view){
            if(getItem(position) != null){
                tvTitle.text = getItem(position)?.name

                if(getItem(position)?.billStatus == false) {
                    tvBillStatus.text = "Belum Lunas"
                    tvBillStatus.setTextColor(Color.parseColor("#EF5350"))
                } else {
                    tvBillStatus.text = "Lunas"
                    tvBillStatus.setTextColor(Color.parseColor("#66BB6A"))
                }

                tvDueDate.text = getItem(position)?.dueDate?.let {
                    Converter.dateFormat(
                            it,
                            "yyyyMMdd",
                            "dd MMMM yyyy"
                    )
                }
                tvAmount.text = getItem(position)?.amount?.let {
                    currencyIdr(it)?.replace(",00","")
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
        AdapterBillBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    class ViewHolder(val view: AdapterBillBinding) : RecyclerView.ViewHolder(view.root)
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Bill>(){
            override fun areItemsTheSame(oldItem: Bill, newItem: Bill): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Bill, newItem: Bill): Boolean {
                return (
                    oldItem.id == newItem.id &&
                    oldItem.name == newItem.name
                )
            }
        }
    }
}