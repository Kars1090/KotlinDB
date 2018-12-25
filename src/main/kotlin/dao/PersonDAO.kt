package dao

import model.Address
import model.Person
import model.factory.AddressFactory
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

    fun findPeople(firstName: String, lastName: String, email: String, street: String, number: Int?,
                   addition: Char?, postalCode: String, city: String): List<Person> {
        val connection = openConnection()
        val list = mutableListOf<Person>()
        try {
            var sql = "SELECT p.firstName, p.lastName, p.email, a.street, a.number, a.addition, a.postalCode, a.city " +
                    "FROM Person p LEFT OUTER JOIN Address a ON " +
                    "p.postalCode = a.postalCode AND " +
                    "p.number = a.number AND " +
                    "(p.addition = a.addition or p.addition is null and a.addition is null) WHERE " +
                    "p.firstName like \"%" + firstName + "%\" AND " +
                    "p.lastName like \"%" + lastName + "%\" AND " +
                    "p.email like \"%" + email + "%\" "
            if (street != "") sql += "AND a.street like \"%" + street + "%\" "
            if (number != null) sql += "AND a.number = " + number + " "
            if (addition != null) sql += "AND a.addition = \'" + addition + "\' "
            if (postalCode != "") sql += "AND a.postalCode like \"%" + postalCode + "%\" "
            if (city != "") sql += "AND a.city like \"%" + city + "%\" "
            val result = connection!!.createStatement().executeQuery(sql)
            val personFactory = PersonFactory()
            val addressFactory = AddressFactory()
            while (result.next()) {
                var address: Address? = null
                if (result.getString("street") != null && result.getInt("number") != null
                    && result.getString("postalCode") != null && result.getString("city") != null) {
                    var additionChar: Char? = null
                    if (result.getString("addition") != null) additionChar = result.getString("addition")[0]
                    address = addressFactory.createAdress(result.getString("street"), result.getInt("number"),
                            result.getString("postalCode"), result.getString("city"), additionChar)
                }
                list.add(personFactory.createPerson(
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("email"),
                        null,
                        address))
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