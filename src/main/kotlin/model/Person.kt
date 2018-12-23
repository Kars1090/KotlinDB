package model

class Person(val firstName: String, val lastName: String, var email: String) {
    var phoneNumber: String? = null
    var address: Address? = null

    fun getFullNameString(): String {
        return firstName + " " + lastName
    }

    fun interactWith(p: Person): String {
        return getFullNameString() + " interacted with " + p.getFullNameString()
    }

    fun getInfoString():String {
        var infoString = getFullNameString()
        infoString += "\n" + email
        if (phoneNumber != null) infoString += "\n" + phoneNumber
        if (address != null) infoString += "\n" + address!!.toFormattedString()
        return infoString
    }
}