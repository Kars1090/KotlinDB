package model

import javafx.beans.property.SimpleStringProperty
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
        person = Person(SimpleStringProperty(firstName), SimpleStringProperty(lastName), SimpleStringProperty(email))
    }

    @Test
    fun testInitialisePerson() {
        assertEquals(firstName, person.firstName.value)
        assertEquals(lastName, person.lastName.value)
        assertEquals(email, person.email.value)
        assertNull(person.phoneNumber.value)
        assertNull(person.address)
    }

    @Test
    fun testChangeEmail() {
        assertEquals(email, person.email.value)
        val newEmail = "newemail@test.com"
        person.email.value = newEmail
        assertEquals(newEmail, person.email.value)
    }

    @Test
    fun testAddPhoneNumber() {
        assertNull(person.phoneNumber.value)
        val newPhoneNumber = "0612345678"
        person.phoneNumber.value = newPhoneNumber
        assertEquals(newPhoneNumber, person.phoneNumber.value)
    }

    @Test
    fun TestAddAddress() {
        assertNull(person.address)
        val mockAddress = Mockito.mock(Address::class.java)
        person.address = mockAddress
        assertEquals(mockAddress, person.address)
    }

    @Test
    fun testGetFullNameString() {
        assertEquals(firstName + " " + lastName, person.getFullNameString())
    }

    @Test
    fun testInteractWith() {
        val p2 = Person(SimpleStringProperty("New"),SimpleStringProperty("Person"),SimpleStringProperty("new@email.com"))
        val expected = person.getFullNameString() + " interacted with " + p2.getFullNameString()
        assertEquals(expected, person.interactWith(p2))
    }
}