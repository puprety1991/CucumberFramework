package utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class databaseReader {
    private static ResultSet resultSet;
    public static ResultSetMetaData metaData;
    public static Connection connection;
    public static Statement statement;

    /*
     * this method create connection to the database, execute query and return object
     * @param sqlQuery
     * @return ResulSet
     */
    public static ResultSet getResultSet(String sqlQuery) {
        connection = null;
        statement = null;
        try {
            connection = DriverManager.getConnection(
                    ConfigReader.getPropertyValue("dbUrl"),
                    ConfigReader.getPropertyValue("dbUsername"),
                    ConfigReader.getPropertyValue("dbPassword"));
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
    /*}finally {
            try {
                if(connection != null){
                    connection.close();
                }
                if(statement != null){

                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }*/
        }
        return resultSet;

    }

    /*
     * this method return an object of resultSetMetaData
     * @param query
     * @return ResultSetMetaData
     * */
    public static ResultSetMetaData getResultSetMetaData(String query) {
        getResultSet(query);
        try {
            metaData = resultSet.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return metaData;
    }

    /*
     * This method extract data from ResultSet and stores into list of maps
     * */
    public static List<Map<String, String>> getListOfMapsFromResultSet(String query) {
        metaData = getResultSetMetaData(query);
        List<Map<String, String>> listFromResultSet = new ArrayList<>();
        Map<String, String> mapData;
        try {
            while (resultSet.next()) {
                mapData = new LinkedHashMap<>();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    String key = metaData.getColumnName(i);
                    String value = resultSet.getString(key);
                    mapData.put(key, value);
                }
                listFromResultSet.add(mapData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {

                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return listFromResultSet;
        }

    }
}



