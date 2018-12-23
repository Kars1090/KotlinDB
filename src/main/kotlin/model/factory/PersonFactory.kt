package model.factory

import model.Address
import model.Person

class PersonFactory {

    fun createPerson(firstName: String, lastName: String, email: String, phoneNumber: String? = null, address: Address? = null): Person {
        val p = Person(firstName, lastName, email)
        if (phoneNumber != null) p.phoneNumber = phoneNumber
        if (address != null) p.address = address
        return p
    }
}