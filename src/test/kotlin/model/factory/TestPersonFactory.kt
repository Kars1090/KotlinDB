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
        assertEquals(firstName, person.firstName)
        assertEquals(lastName, person.lastName)
        assertEquals(email, person.email)
        assertNull(person.phoneNumber)
        assertNull(person.address)
    }

    @Test
    fun testCreatePersonPhoneNumber() {
        val person = factory.createPerson(firstName,lastName,email,phoneNumber)
        assertEquals(firstName, person.firstName)
        assertEquals(lastName, person.lastName)
        assertEquals(email, person.email)
        assertEquals(phoneNumber,person.phoneNumber)
        assertNull(person.address)
    }

    @Test
    fun testCreatePersonPhoneAndAdress() {
        val address = Mockito.mock(Address::class.java)
        val person = factory.createPerson(firstName,lastName,email,phoneNumber,address)
        assertEquals(firstName, person.firstName)
        assertEquals(lastName, person.lastName)
        assertEquals(email, person.email)
        assertEquals(phoneNumber,person.phoneNumber)
        assertEquals(address,person.address)
    }
}