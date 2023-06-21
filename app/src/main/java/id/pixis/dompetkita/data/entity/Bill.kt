package id.pixis.dompetkita.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity
data class Bill(
    @PrimaryKey (autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val amount: Int,
    val dueDate: String,
    val notes: String,
    val billStatus: Boolean = false, //Belum lunas
    val category: String,
    val icon: String?,
) : Parcelable
