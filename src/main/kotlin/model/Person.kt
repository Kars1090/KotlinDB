package model

class Person(val firstName: String, val lastName: String, var email: String) {
    var phoneNumber: String? = null
    var adress: Adress? = null

    fun getFullNameString(): String {
        return firstName + " " + lastName
    }

    fun interactWith(p: Person): String {
        return getFullNameString() + " interacted with " + p.getFullNameString()
    }
}