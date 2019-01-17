package model.factory

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import model.Address

class AddressFactory {

    fun createAdress(street: String, number: Int, postalCode: String, city: String, addition: Char? = null): Address? {
        if (validStreet(street) && validNumber(number) && validPostalCode(postalCode) && validCity(city)) {
            val a = Address(SimpleStringProperty(street), SimpleIntegerProperty(number), SimpleStringProperty(postalCode), SimpleStringProperty(city))
            if (addition != null) a.addition.value = addition.toString()
            return a
        }
        return null
    }

    private fun validStreet(street: String): Boolean {
        if (street.length < 200 && street.length > 3) return true
        return false
    }

    private fun validNumber(number: Int): Boolean {
        if (number > 0 && number < 1000) return true
        return false
    }

    private fun validPostalCode(postalCode: String): Boolean {
        if (postalCode.length == 6) return true
        return false
    }

    private fun validCity(city: String): Boolean {
        if (city.length < 200 && city.length > 3) return true;
        return false
    }

}