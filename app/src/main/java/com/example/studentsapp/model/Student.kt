package com.example.studentsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(

    val avatarUrl: String = "",
    val name: String = "",
    @PrimaryKey val id: String = "",
    val phone: String = "",
    val address: String = "",
    var isChecked: Boolean = false,
    val BirthDate: String = "",
    val BirthTime: String = ""

) {

    val json : Map<String, Any>
        get() {
            return hashMapOf(
                AVATAR_URL_KEY to avatarUrl,
                ID_KEY to id,
                NAME_KEY to name,
                ADDRESS_KEY to address,
                PHONE_KEY to phone,
                CHECKED_KEY to isChecked,
                BIRTHDATE_KEY to BirthDate,
                BIRTHTIME_KEY to BirthTime
            )
        }

    companion object{

        private const val AVATAR_URL_KEY = "avatarUrl"
        private const val ID_KEY = "id"
        private const val NAME_KEY = "name"
        private const val PHONE_KEY = "phone"
        private const val ADDRESS_KEY = "address"
        private const val CHECKED_KEY = "isChecked"
        private const val BIRTHDATE_KEY = "BirthDate"
        private const val BIRTHTIME_KEY = "BirthTime"

        fun fromJson(json: Map<String, Any>) : Student {
            val avatarUrl = json[AVATAR_URL_KEY] as? String ?: ""
            val id = json[ID_KEY] as? String ?: ""
            val name = json[NAME_KEY] as? String ?: ""
            val address = json[ADDRESS_KEY] as? String ?: ""
            val phone = json[PHONE_KEY] as? String ?: ""
            val isChecked = json[CHECKED_KEY] as? Boolean ?: false
            val BirthDate = json[BIRTHDATE_KEY] as? String ?: ""
            val BirthTime = json[BIRTHTIME_KEY] as? String ?: ""

            return Student(
                avatarUrl=avatarUrl,
                name=name,
                id=id,
                address=address,
                phone=phone,
                isChecked=isChecked,
                BirthDate=BirthDate,
                BirthTime=BirthTime
            )
        }
    }

}