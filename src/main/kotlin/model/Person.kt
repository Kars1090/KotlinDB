package model

import javafx.beans.property.SimpleStringProperty

class Person(val firstName: SimpleStringProperty, val lastName: SimpleStringProperty, val email: SimpleStringProperty) {
    val phoneNumber = SimpleStringProperty()
    var address: Address? = null

    fun getFullNameString(): String {
        return firstName.value + " " + lastName.value
    }

    fun interactWith(p: Person): String {
        return getFullNameString() + " interacted with " + p.getFullNameString()
    }

    fun getInfoString():String {
        var infoString = getFullNameString()
        infoString += "\n" + email.value
        if (phoneNumber.value != null) infoString += "\n" + phoneNumber.value
        if (address != null) infoString += "\n" + address!!.toFormattedString()
        return infoString
    }
}