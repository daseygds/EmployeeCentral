package epcentral.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static epcentral.Utility.ReadProperties.getConfigProperties;

public class DBConnector {
    private static final Logger logger = LogManager.getLogger(DBConnector.class);
    private static DBConnector instance;
    private Connection connection;

    private DBConnector(String env){
        try {
            logger.info("Attempting to connect to the database using the environment: {}", env);
            this.connection = DriverManager.getConnection(getConfigProperties(env,"url"));
            logger.info("Successfully connected to the database.");
        } catch (SQLException e) {
            logger.error("Error connecting to the database: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static DBConnector getInstance(String env){
        try {
            if (instance == null || instance.getConnection().isClosed()) {
                logger.info("Creating new database connection instance for environment: {}", env);
                instance = new DBConnector(env);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }

    public Connection getConnection() {
        logger.debug("Returning current database connection.");
        return connection;
    }

    public void closeConnection(){
        try {
            if (instance == null || !instance.getConnection().isClosed()){
                logger.info("Closing connection {}",this.connection);
                this.connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
