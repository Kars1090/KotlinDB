package dao

import model.Person
import model.factory.PersonFactory
import java.sql.SQLException

class PersonDAO: AbstractDAO() {

    fun getAll(): List<Person> {
        openConnection()
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

    fun insertPerson(person: Person): Boolean {
        openConnection()
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
}