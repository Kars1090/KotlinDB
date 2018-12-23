package dao

import model.Address
import java.sql.SQLIntegrityConstraintViolationException

class AddressDAO : AbstractDAO() {

    init {
        getConnection()
    }

    fun getAll(): List<Address> {
        val sql = "SELECT * FROM Address"
        val result = connection!!.createStatement().executeQuery(sql)
        val list = mutableListOf<Address>()
        while (result.next()) {
            val address = Address(
                    result.getString("street"),
                    result.getInt("number"),
                    result.getString("postalCode"),
                    result.getString("city"))
            if (result.getString("addition") != null) address.addition = result.getString("addition")[0]
            list.add(address)
        }
        return list
    }

    fun getAddress(postalCode: String, number: Int): Address? {
        val sql = "SELECT * FROM Address WHERE postalCode = \"" + postalCode +
                    "\" AND number =" + number
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
        return null
    }

    fun ifAddressExists(postalCode: String, number: Int): Boolean {
        val sql = "SELECT * FROM Address WHERE postalCode = \"" + postalCode +
                "\" AND number =" + number
        val result = connection!!.createStatement().executeQuery(sql)
        while (result.next()) {
            return true
        }
        return false
    }

    fun ifAddressExists(address: Address): Boolean {
        val sql = "SELECT * FROM Address WHERE postalCode = \"" + address.postalCode +
                "\" AND number =" + address.number
        val result = connection!!.createStatement().executeQuery(sql)
        while (result.next()) {
            return true
        }
        return false
    }

    fun insertAddress(address: Address): Boolean {
        var sql = "INSERT INTO Address (street, number, postalCode, addition, city) VALUES (" +
                "\"" + address.street + "\","+ address.number + ",\"" + address.postalCode + "\","
        if (address.addition != null) sql += "\'" + address.addition + "\',"
        else sql += "null,"
        sql += "\"" + address.city + "\")"
        try {
            connection!!.createStatement().execute(sql)
        } catch (e:SQLIntegrityConstraintViolationException) {
            return false
        }
        return true
    }
}