package model.factory

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class TestAdressFactory {
    private val factory = AdressFactory()
    private val street = "Warnsveldseweg"
    private val number = 60
    private val postalCode = "7200JA"
    private val city = "Zutphen"

    @Test
    fun testCreateAdress() {
        val adress = factory.createAdress(street, number, postalCode, city)
        assertEquals(street, adress.street)
        assertEquals(number, adress.number)
        assertEquals(postalCode, adress.postalCode)
        assertEquals(city, adress.city)
        assertNull(adress.addition)
    }

    @Test
    fun testCreateAdressAddition() {
        val addition = 'a'
        val adress = factory.createAdress(street, number, postalCode, city, addition)
        assertEquals(street, adress.street)
        assertEquals(number, adress.number)
        assertEquals(postalCode, adress.postalCode)
        assertEquals(city, adress.city)
        assertEquals(addition, adress.addition)
    }
}