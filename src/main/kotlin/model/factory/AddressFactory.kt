package model.factory

import model.Address

class AddressFactory {

    fun createAdress(street: String, number: Int, postalCode: String, city: String, addition: Char? = null): Address? {
        if (validStreet(street) && validNumber(number) && validPostalCode(postalCode) && validCity(city)) {
            val a = Address(street, number, postalCode, city)
            if (addition != null) a.addition = addition
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