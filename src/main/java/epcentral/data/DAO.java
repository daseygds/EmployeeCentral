package epcentral.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static epcentral.data.StatementConstants.*;

public class DAO {
    private static Logger logger = LogManager.getLogger(DAO.class);
    private Connection conn;
    private PreparedStatement preparedStatement;

    public DAO(String env){
        logger.info("Initializing DAO for environment: {}", env);
        this.conn = DBConnector.getInstance(env).getConnection();
        logger.info("Database connection established successfully.");
    }

    public void createTable(){
        try {
            logger.info("Creating employees table if it doesn't exist...");
            this.preparedStatement = this.conn.prepareStatement(CREATE_TABLE);
            this.preparedStatement.execute();
            logger.info("Table creation executed successfully.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertIntoTable(Integer employeeId, String employeeName, Integer employeeAge){
        try {
            logger.info("Preparing to insert record: ID={}, Name={}, Age={}", employeeId, employeeName, employeeAge);
            this.preparedStatement = this.conn.prepareStatement(INSERT_RECORDS);
            this.preparedStatement.setInt(1,employeeId);
            this.preparedStatement.setString(2,employeeName);
            this.preparedStatement.setInt(3,employeeAge);
            int rowsAffected = this.preparedStatement.executeUpdate();
            logger.info("Inserted record into employees table. Rows affected: {}", rowsAffected);
        } catch (SQLException e) {
            logger.error("Error inserting record into table: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public ResultSet getAllRecords() {
        try {
            logger.info("Starting method getAllRecords()");

            logger.info("Preparing SQL statement: {}", VIEW_ALL_RECORDS);
            this.preparedStatement = this.conn.prepareStatement(VIEW_ALL_RECORDS);

            logger.info("Executing query...");
            ResultSet resultSet = this.preparedStatement.executeQuery();

            logger.info("Query executed successfully, returning ResultSet");
            return resultSet;
        } catch (SQLException e) {
            logger.error("SQL Exception occurred while fetching all records: {}", e.getMessage(), e);
            throw new RuntimeException("Error executing getAllRecords()", e);
        }
    }
}
