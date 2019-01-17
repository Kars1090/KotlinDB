package model.factory

import model.Address
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.mockito.Mockito

class TestPersonFactory {
    private val factory = PersonFactory()
    private val firstName = "First"
    private val lastName = "Last"
    private val email = "mail@mail.com"
    val phoneNumber = "1234567890"

    @Test
    fun testCreatePerson() {
        val person = factory.createPerson(firstName,lastName,email)
        assertEquals(firstName, person.firstName.value)
        assertEquals(lastName, person.lastName.value)
        assertEquals(email, person.email.value)
        assertNull(person.phoneNumber.value)
        assertNull(person.address)
    }

    @Test
    fun testCreatePersonPhoneNumber() {
        val person = factory.createPerson(firstName,lastName,email,phoneNumber)
        assertEquals(firstName, person.firstName.value)
        assertEquals(lastName, person.lastName.value)
        assertEquals(email, person.email.value)
        assertEquals(phoneNumber,person.phoneNumber.value)
        assertNull(person.address)
    }

    @Test
    fun testCreatePersonPhoneAndAdress() {
        val address = Mockito.mock(Address::class.java)
        val person = factory.createPerson(firstName,lastName,email,phoneNumber,address)
        assertEquals(firstName, person.firstName.value)
        assertEquals(lastName, person.lastName.value)
        assertEquals(email, person.email.value)
        assertEquals(phoneNumber,person.phoneNumber.value)
        assertEquals(address,person.address)
    }
}