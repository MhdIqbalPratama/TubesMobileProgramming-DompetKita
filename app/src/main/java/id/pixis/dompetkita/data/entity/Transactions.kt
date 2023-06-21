package id.pixis.dompetkita.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity
data class Transactions(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,
    val name : String,
    val amount : Int,
    val date : String,
    val notes : String?,
    val type : Int,
    val category : String,
    val icon : String?,
) : Parcelable
