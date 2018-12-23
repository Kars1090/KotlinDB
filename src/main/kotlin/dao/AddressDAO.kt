package dao

import model.Address
import model.factory.AddressFactory
import java.sql.SQLException
import java.sql.SQLIntegrityConstraintViolationException

class AddressDAO : AbstractDAO() {

    fun findAll(): List<Address> {
        val connection = openConnection()
        val list = mutableListOf<Address>()
        try {
            val sql = "SELECT * FROM Address"
            val result = connection!!.createStatement().executeQuery(sql)
            val factory = AddressFactory()
            while (result.next()) {
                val address = factory.createAdress(
                        result.getString("street"),
                        result.getInt("number"),
                        result.getString("postalCode"),
                        result.getString("city"))
                if (result.getString("addition") != null) address!!.addition = result.getString("addition")[0]
                list.add(address!!)
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            closeConnection()
            return list
        }
    }

    fun getAddress(postalCode: String, number: Int): Address? {
        val connection = openConnection()
        try {
            val sql = "SELECT * FROM Address WHERE postalCode = \"" + postalCode +
                    "\" AND number =" + number
            val result = connection!!.createStatement().executeQuery(sql)
            val factory = AddressFactory()
            while (result.next()) {
                val address = factory.createAdress(
                        result.getString("street"),
                        result.getInt("number"),
                        result.getString("postalCode"),
                        result.getString("city"))
                if (result.getString("addition") != null) address!!.addition = result.getString("addition")[0]
                return address
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            closeConnection()
        }
        return null
    }

    fun ifAddressExists(postalCode: String, number: Int): Boolean {
        val connection = openConnection()
        try {
            val sql = "SELECT * FROM Address WHERE postalCode = \"" + postalCode +
                    "\" AND number =" + number
            val result = connection!!.createStatement().executeQuery(sql)
            closeConnection()
            while (result.next()) {
                return true
            }
            return false
        } catch (e: SQLException) {
            e.printStackTrace()
            return false
        } finally {
            closeConnection()
        }
    }

    fun ifAddressExists(address: Address): Boolean {
        val connection = openConnection()
        try {
            val sql = "SELECT * FROM Address WHERE postalCode = \"" + address.postalCode +
                    "\" AND number =" + address.number
            val result = connection!!.createStatement().executeQuery(sql)
            closeConnection()
            while (result.next()) {
                return true
            }
            return false
        } catch (e: SQLException) {
            e.printStackTrace()
            return false
        } finally {
            closeConnection()
        }
    }

    fun insertAddress(address: Address): Boolean {
        val connection = openConnection()
        try {
            var sql = "INSERT INTO Address (street, number, postalCode, addition, city) VALUES (" +
                    "\"" + address.street +
                    "\"," + address.number +
                    ",\"" + address.postalCode + "\","
            if (address.addition != null) sql +=
                    "\'" + address.addition + "\',"
            else sql += "null,"
            sql += "\"" + address.city + "\")"
            connection!!.createStatement().execute(sql)
            return true
        } catch (e: SQLIntegrityConstraintViolationException) {
            return false
        } finally {
            closeConnection()
        }
    }
}