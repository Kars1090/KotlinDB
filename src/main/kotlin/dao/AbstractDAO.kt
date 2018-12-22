package dao

import java.sql.*
import java.util.*

abstract class DAO {

    protected var connection: Connection? = null
    protected val username = "root"
    protected val password = "killerfist"

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
                            "",
                    connectionProps)
        } catch (ex: SQLException) {
            ex.printStackTrace()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
//
//    fun executeMySQLQuery() {
//        var stmt: Statement? = null
//        var resultset: ResultSet? = null
//        try {
//            stmt = connection!!.createStatement()
//            resultset = stmt!!.executeQuery("SHOW DATABASES;")
//            if (stmt.execute("SHOW DATABASES;")) {
//                resultset = stmt.resultSet
//            }
//            while (resultset!!.next()) {
//                println(resultset.getString("Database"))
//            }
//        } catch (ex: SQLException) {
//            // handle any errors
//            ex.printStackTrace()
//        } finally {
//            // release resources
//            if (resultset != null) {
//                try {
//                    resultset.close()
//                } catch (sqlEx: SQLException) {
//                }
//                resultset = null
//            }
//            if (stmt != null) {
//                try {
//                    stmt.close()
//                } catch (sqlEx: SQLException) {
//                }
//                stmt = null
//            }
//            if (connection != null) {
//                try {
//                    connection!!.close()
//                } catch (sqlEx: SQLException) {
//                }
//                connection = null
//            }
//        }
//    }
}