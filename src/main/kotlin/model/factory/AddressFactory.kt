package model.factory

import model.Adress

class AdressFactory {

    fun createAdress(street: String, number: Int, postalCode: String, city: String, addition: Char? = null): Adress {
        val a = Adress(street, number, postalCode, city)
        if (addition != null) a.addition = addition
        return a
    }

}