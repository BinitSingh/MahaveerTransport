package  com.mobile.mahaveertransport.domain.datamodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id : String?,
    val title : String?,
    val year : String?,
    val image : String?,
    val crew : String?,
    val imDbRating : String?,
    val imDbRatingCount : String?,
) : Parcelable
