package dao

import java.sql.*
import java.util.*

abstract class AbstractDAO {

    private var connection: Connection? = null
    private val username = "root"
    private val password = "killerfist"
    private val database = "KotlinDB"

    protected fun openConnection(): Connection? {
        val connectionProps = Properties()
        connectionProps.put("user", username)
        connectionProps.put("password", password)
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance()
            connection = DriverManager.getConnection(
                    "jdbc:" + "mysql" + "://" +
                            "127.0.0.1" +
                            ":" + "3306" + "/" +
                            database,
                    connectionProps)
            return connection
        } catch (ex: SQLException) {
            ex.printStackTrace()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return null
    }

    protected fun closeConnection() {
        if (connection != null) connection!!.close()
    }

}