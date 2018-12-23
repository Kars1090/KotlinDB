package dao

import model.Address
import java.sql.SQLException
import java.sql.SQLIntegrityConstraintViolationException

class AddressDAO : AbstractDAO() {

    fun getAll(): List<Address> {
        openConnection()
        val list = mutableListOf<Address>()
        try {
            val sql = "SELECT * FROM Address"
            val result = connection!!.createStatement().executeQuery(sql)
            while (result.next()) {
                val address = Address(
                        result.getString("street"),
                        result.getInt("number"),
                        result.getString("postalCode"),
                        result.getString("city"))
                if (result.getString("addition") != null) address.addition = result.getString("addition")[0]
                list.add(address)
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            closeConnection()
            return list
        }
    }

    fun getAddress(postalCode: String, number: Int): Address? {
        openConnection()
        val sql = "SELECT * FROM Address WHERE postalCode = \"" + postalCode +
                "\" AND number =" + number
        try {
            val result = connection!!.createStatement().executeQuery(sql)
            while (result.next()) {
                val address = Address(
                        result.getString("street"),
                        result.getInt("number"),
                        result.getString("postalCode"),
                        result.getString("city"))
                if (result.getString("addition") != null) address.addition = result.getString("addition")[0]
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
        openConnection()
        val sql = "SELECT * FROM Address WHERE postalCode = \"" + postalCode +
                "\" AND number =" + number
        try {
            val result = connection!!.createStatement().executeQuery(sql)
            closeConnection()
            while (result.next()) {
                return true
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            closeConnection()
        }
        return false
    }

    fun ifAddressExists(address: Address): Boolean {
        openConnection()
        val sql = "SELECT * FROM Address WHERE postalCode = \"" + address.postalCode +
                "\" AND number =" + address.number
        try {
            val result = connection!!.createStatement().executeQuery(sql)
            closeConnection()
            while (result.next()) {
                return true
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            closeConnection()
        }
        return false
    }

    fun insertAddress(address: Address): Boolean {
        openConnection()
        var sql = "INSERT INTO Address (street, number, postalCode, addition, city) VALUES (" +
                "\"" + address.street + "\"," + address.number + ",\"" + address.postalCode + "\","
        if (address.addition != null) sql += "\'" + address.addition + "\',"
        else sql += "null,"
        sql += "\"" + address.city + "\")"
        try {
            connection!!.createStatement().execute(sql)
        } catch (e: SQLIntegrityConstraintViolationException) {
            return false
        } finally {
            closeConnection()
        }
        return true
    }
}