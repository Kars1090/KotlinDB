package dao

import java.sql.*
import java.util.*

abstract class AbstractDAO {

    protected var connection: Connection? = null
    protected val username = "root"
    protected val password = "killerfist"
    protected val database = "KotlinDB"

    protected fun getConnection() {
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
        } catch (ex: SQLException) {
            ex.printStackTrace()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

}