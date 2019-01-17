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
        assertEquals(street, address!!.street.value)
        assertEquals(number, address.number.value)
        assertEquals(postalCode, address.postalCode.value)
        assertEquals(city, address.city.value)
        assertNull(address.addition.value)
    }

    @Test
    fun testCreateAddressAddition() {
        val addition = 'a'
        val address = factory.createAdress(street, number, postalCode, city, addition)
        assertEquals(street, address!!.street.value)
        assertEquals(number, address.number.value)
        assertEquals(postalCode, address.postalCode.value)
        assertEquals(city, address.city.value)
        assertEquals(addition.toString(), address.addition.value)
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