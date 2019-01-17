package model.factory

import javafx.beans.property.SimpleStringProperty
import model.Address
import model.Person

class PersonFactory {

    fun createPerson(firstName: String, lastName: String, email: String, phoneNumber: String? = null, address: Address? = null): Person {
        val p = Person(SimpleStringProperty(firstName), SimpleStringProperty(lastName), SimpleStringProperty(email))
        if (phoneNumber != null) p.phoneNumber.value = phoneNumber
        if (address != null) p.address = address
        return p
    }
}