package model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class TestAdress {
    private lateinit var adress: Adress
    private val street = "Weg Naar Voorst"
    private val number = 11
    private val postalCode = "7205CK"
    private val city = "Zutphen"

    @Before
    fun setupTestAdress() {
        adress = Adress(street, number, postalCode, city)
    }

    @Test()
    fun testInitialiseAdress() {
        assertEquals(street, adress.street)
        assertEquals(number, adress.number)
        assertEquals(postalCode, adress.postalCode)
        assertEquals(city, adress.city)
        assertNull(adress.addition)
    }

    @Test
    fun testAddAddition() {
        val newAddition = 'a'
        assertNull(adress.addition)
        adress.addition = newAddition
        assertEquals(newAddition, adress.addition)
    }

    @Test
    fun testToFormattedString() {
        assertNull(adress.addition)
        val formattedString = street + " " + number.toString() + "\n" + postalCode + " " + city
        assertEquals(formattedString, adress.toFormattedString())
        val newAddition = 'a'
        adress.addition = newAddition
        val formattedStringWithAddition = street + " " + number.toString() + newAddition + "\n" + postalCode + " " + city
        assertEquals(formattedStringWithAddition, adress.toFormattedString())
    }
}