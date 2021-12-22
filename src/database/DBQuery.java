package database;

import java.sql.*;

public class DBQuery {

    private static PreparedStatement statement;

    /** This method sets the prepared statement
     * @param connection the database connection
     * @param sqlStatement the SQL Statement string
     * @throws SQLException if an sql exception occurs
     */
    public static void setPreparedStatement(Connection connection, String sqlStatement) throws SQLException {
        statement = connection.prepareStatement(sqlStatement);
    }

    /**
     * @return the prepared statement
     */
    public static PreparedStatement getPreparedStatement() {
        return statement;
    }
}