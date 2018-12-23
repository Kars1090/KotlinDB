package model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class TestPerson {
    private lateinit var person: Person
    private val firstName = "FirstName"
    private val lastName = "LastName"
    private val email = "test@1234.com"

    @Before
    fun setupTestPerson() {
        person = Person(firstName, lastName, email)
    }

    @Test
    fun testInitialisePerson() {
        assertEquals(firstName, person.firstName)
        assertEquals(lastName, person.lastName)
        assertEquals(email, person.email)
        assertNull(person.phoneNumber)
        assertNull(person.address)
    }

    @Test
    fun testChangeEmail() {
        assertEquals(email, person.email)
        val newEmail = "newemail@test.com"
        person.email = newEmail
        assertEquals(newEmail, person.email)
    }

    @Test
    fun testAddPhoneNumber() {
        assertNull(person.phoneNumber)
        val newPhoneNumber = "0612345678"
        person.phoneNumber = newPhoneNumber
        assertEquals(newPhoneNumber, person.phoneNumber)
    }

    @Test
    fun TestAddAdress() {
        assertNull(person.address)
        val mockAdress = Mockito.mock(Address::class.java)
        person.address = mockAdress
        assertEquals(mockAdress, person.address)
    }

    @Test
    fun testGetFullNameString() {
        assertEquals(firstName + " " + lastName, person.getFullNameString())
    }

    @Test
    fun testInteractWith() {
        val p2 = Person("New","Person","new@email.com")
        val expected = person.getFullNameString() + " interacted with " + p2.getFullNameString()
        assertEquals(expected, person.interactWith(p2))
    }
}