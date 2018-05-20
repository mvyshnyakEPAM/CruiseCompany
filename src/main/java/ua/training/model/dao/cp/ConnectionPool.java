package ua.training.model.dao.cp;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.constants.Configs;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Максим
 * 06.05.2018
 */
public class ConnectionPool {
    private final static Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static volatile DataSource dataSource;

    private ConnectionPool() {
    }

    private static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPool.class) {
                if (dataSource == null) {
                    try {
                        Properties properties = new Properties();
                        properties.load(ConnectionPool.class.getClassLoader()
                                .getResourceAsStream(Configs.DB_PROPERTY_FILE));
                        BasicDataSource ds = new BasicDataSource();
                        ds.setUrl(properties.getProperty(Configs.DB_URL));
                        ds.setUsername(properties.getProperty(Configs.DB_USER_NAME));
                        ds.setPassword(properties.getProperty(Configs.DB_PASSWORD));
                        ds.setDriverClassName(Configs.DB_DRIVER_NAME);
                        ds.setMinIdle(Integer.valueOf(properties.getProperty(Configs.DB_MIN_IDLE)));
                        ds.setMaxIdle(Integer.valueOf(properties.getProperty(Configs.DB_MAX_IDLE)));
                        ds.setMaxOpenPreparedStatements(
                                Integer.valueOf(properties.getProperty(Configs.DB_MAX_OPEN_PS)));
                        dataSource = ds;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return dataSource;
    }

    public static Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException();
        }
    }
}
