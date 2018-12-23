package model.factory

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class TestAddressFactory {
    private val factory = AddressFactory()
    private val street = "Warnsveldseweg"
    private val number = 60
    private val postalCode = "7200JA"
    private val city = "Zutphen"

    @Test
    fun testCreateAddress() {
        val address = factory.createAdress(street, number, postalCode, city)
        assertEquals(street, address?.street)
        assertEquals(number, address?.number)
        assertEquals(postalCode, address?.postalCode)
        assertEquals(city, address?.city)
        assertNull(address?.addition)
    }

    @Test
    fun testCreateAddressAddition() {
        val addition = 'a'
        val address = factory.createAdress(street, number, postalCode, city, addition)
        assertEquals(street, address?.street)
        assertEquals(number, address?.number)
        assertEquals(postalCode, address?.postalCode)
        assertEquals(city, address?.city)
        assertEquals(addition, address?.addition)
    }

    @Test
    fun testCreateAddressInvalidStreet() {
        val invalidStreet = "gfdtdghfdghfdhgfdhgfdhgfdhgfdhfdhgfdhgdfhgfdhgfdhfvdjhfdvhfdhfdhgdhgfdvhgdfhgdhgdgfdhgfd" +
                "hgdvhgdhvfdhfdhgfhgfdvhgdvhgfdhgfdvhgdvhgfvdhgdhdhdhfhgfdhgfdhgfdhgfdhgfdhgfdvfhgvdhvgdvhgfdvfdvhgdv" +
                "gdghdjhdjdgjdgfjhfgdjfdjfdjfdjfdjgfdjfdjfgdjfdjdf"
        val address = factory.createAdress(invalidStreet, number, postalCode, city)
        assertNull(address)
    }

    @Test
    fun testCreateAddressInvalidNumber() {
        val address = factory.createAdress(street, 0, postalCode, city)
        assertNull(address)
        val address2 = factory.createAdress(street, 9000, postalCode, city)
        assertNull(address2)
    }

    @Test
    fun testCreateAddressInvalidPostalCode() {
        val address = factory.createAdress(street, number, "", city)
        assertNull(address)
        val address2 = factory.createAdress(street, number, "7272AAA", city)
        assertNull(address2)
    }

    @Test
    fun testCreateAddressInvalidCity() {
        val invalidCity = "gfdtdghfdghfdhgfdhgfdhgfdhgfdhfdhgfdhgdfhgfdhgfdhfvdjhfdvhfdhfdhgdhgfdvhgdfhgdhgdgfdhgfd" +
                "hgdvhgdhvfdhfdhgfhgfdvhgdvhgfdhgfdvhgdvhgfvdhgdhdhdhfhgfdhgfdhgfdhgfdhgfdhgfdvfhgvdhvgdvhgfdvfdvhgdv" +
                "gdghdjhdjdgjdgfjhfgdjfdjfdjfdjfdjgfdjfdjfgdjfdjdf"
        val address = factory.createAdress(street, number, postalCode, invalidCity)
        assertNull(address)
    }
}