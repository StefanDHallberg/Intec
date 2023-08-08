package intec.inteceksame.framework_drivers;

import intec.inteceksame.interface_adapters.DatabaseConnector;
import intec.inteceksame.exception.DatabaseConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DatabaseAdapter implements DatabaseConnector {
    private final JdbcTemplate dkJdbcTemplate;
    private final JdbcTemplate usJdbcTemplate;

    public DatabaseAdapter(@Qualifier("dkJdbcTemplate") JdbcTemplate dkJdbcTemplate,
                           @Qualifier("usJdbcTemplate") JdbcTemplate usJdbcTemplate)
    {
        this.dkJdbcTemplate = dkJdbcTemplate;
        this.usJdbcTemplate = usJdbcTemplate;
    }

    //establishing connection with spring.datasource.dk
    @Override
    public Connection getDKConnection() {
        try {
            return dkJdbcTemplate.getDataSource().getConnection();
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error getting connection to DK database", e);
        }
    }

    @Override
    public Connection getUSConnection() {
        try {
            return usJdbcTemplate.getDataSource().getConnection();
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error getting connection to US database", e);
        }
    }

    @Override
    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DatabaseConnectionException("Error closing database connection", e);
            }
        }
    }
}
