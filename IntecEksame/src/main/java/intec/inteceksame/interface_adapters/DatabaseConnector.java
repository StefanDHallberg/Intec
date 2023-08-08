package intec.inteceksame.interface_adapters;

import java.sql.Connection;

public interface DatabaseConnector {
    Connection getDKConnection();
    Connection getUSConnection();
    void closeConnection(Connection connection);
}
