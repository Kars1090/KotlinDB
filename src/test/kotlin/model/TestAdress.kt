package model

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class TestAddress {
    private lateinit var adress: Address
    private val street = "Warnsveldseweg"
    private val number = 60
    private val postalCode = "7200JA"
    private val city = "Zutphen"

    @Before
    fun setupTestAddress() {
        adress = Address(SimpleStringProperty(street), SimpleIntegerProperty(number), SimpleStringProperty(postalCode), SimpleStringProperty(city))
    }

    @Test()
    fun testInitialiseAddress() {
        assertEquals(street, adress.street.value)
        assertEquals(number, adress.number.value)
        assertEquals(postalCode, adress.postalCode.value)
        assertEquals(city, adress.city.value)
        assertNull(adress.addition.value)
    }

    @Test
    fun testAddAddition() {
        val newAddition = 'a'
        assertNull(adress.addition.value)
        adress.addition.value = newAddition.toString()
        assertEquals(newAddition, adress.addition.value[0])
    }

    @Test
    fun testToFormattedString() {
        assertNull(adress.addition.value)
        val formattedString = street + " " + number.toString() + "\n" + postalCode + " " + city
        assertEquals(formattedString, adress.toFormattedString())
        val newAddition = 'a'
        adress.addition.value = newAddition.toString()
        val formattedStringWithAddition = street + " " + number.toString() + newAddition + "\n" + postalCode + " " + city
        assertEquals(formattedStringWithAddition, adress.toFormattedString())
    }
}