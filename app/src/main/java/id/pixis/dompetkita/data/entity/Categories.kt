package id.pixis.dompetkita.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity
data class Categories(
    @PrimaryKey (autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val icon: String,
    val type: Int
) : Parcelable
