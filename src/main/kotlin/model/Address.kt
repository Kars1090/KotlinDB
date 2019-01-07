package model

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty

class Address(val street: SimpleStringProperty, val number: SimpleIntegerProperty, val postalCode: SimpleStringProperty, val city: SimpleStringProperty) {
    val addition = SimpleStringProperty()

    fun toFormattedString(): String {
        var formattedString = street.value + " " + number.value.toString()
        if (addition.value != null) formattedString += addition.value
        formattedString += "\n" + postalCode.value + " " + city.value
        return formattedString
    }
}