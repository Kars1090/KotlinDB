package model

class Address(val street: String, val number: Int, val postalCode: String, val city: String) {
    var addition: Char? = null

    fun toFormattedString(): String {
        var formattedString = street + " " + number.toString()
        if (addition != null) formattedString += addition
        formattedString += "\n" + postalCode + " " + city
        return formattedString
    }
}