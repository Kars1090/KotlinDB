package dao

import model.Address
import model.Person
import model.factory.PersonFactory
import java.sql.SQLException

class PersonDAO: AbstractDAO() {

    fun findAll(): List<Person> {
        val connection = openConnection()
        val list = mutableListOf<Person>()
        try {
            val sql = "SELECT * FROM Person"
            val result = connection!!.createStatement().executeQuery(sql)
            val factory = PersonFactory()
            while (result.next()) {
                list.add(factory.createPerson(
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("email")))
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            closeConnection()
            return list
        }
    }

    fun findAllWithAddress(): List<Person> {
        val connection = openConnection()
        val list = mutableListOf<Person>()
        try {
            val sql = "SELECT * FROM Person"
            val result = connection!!.createStatement().executeQuery(sql)
            val personFactory = PersonFactory()
            val addressDAO = AddressDAO()
            while (result.next()) {
                var address: Address? = null
                if (result.getString("postalCode") != null && result.getInt("number") != null) {
                    address = addressDAO.getAddress(
                            result.getString("postalCode"),
                            result.getInt("number"))
                }
                list.add(personFactory.createPerson(
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("email"),
                        null,
                        address))
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            closeConnection()
            return list
        }
    }

    fun insertPerson(person: Person): Boolean {
        val connection = openConnection()
        try {
            var sql = "INSERT INTO Person (firstName, lastName, email, postalCode, number, addition) VALUES (" +
                    "\"" + person.firstName + "\"," +
                    "\"" + person.lastName + "\"," +
                    "\"" + person.email + "\","
            if (person.address != null) {
                sql += "\"" + person.address!!.postalCode + "\"," +
                        "" + person.address!!.number + ","
                if (person.address?.addition != null) sql += "\'" + person.address!!.addition + "\'"
                else sql += "null"
            } else sql += "null, null, null"
            sql += ")"
            connection!!.createStatement().execute(sql)
            return true
        } catch (e: SQLException) {
            e.printStackTrace()
            return false
        } finally {
            closeConnection()
        }
    }

    fun findPeople(firstName: String, lastName: String, email: String): List<Person> {
        val connection = openConnection()
        val list = mutableListOf<Person>()
        try {
            val sql = "SELECT * FROM Person WHERE " +
                    "firstName like \"%" + firstName + "%\" AND " +
                    "lastName like \"%" + lastName + "%\" AND " +
                    "email like \"%" + email + "%\""
            val result = connection!!.createStatement().executeQuery(sql)
            val factory = PersonFactory()
            while (result.next()) {
                list.add(factory.createPerson(
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("email")))
            }
            return list
        } catch (e: SQLException) {
            e.printStackTrace()
            return list
        } finally {
            closeConnection()
        }
    }
}