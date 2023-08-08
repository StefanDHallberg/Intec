package intec.inteceksame.interface_adapters;

import intec.inteceksame.entity.Guest;
import intec.inteceksame.exception.GuestCompaniesException;
import intec.inteceksame.exception.GuestSaveException;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GuestDataAccessor {
    private final DatabaseConnector databaseConnector;

    public GuestDataAccessor(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    public void saveGuestToDK(Guest guest) {
        try {
            saveGuest(guest, databaseConnector.getDKConnection());
        } catch (GuestSaveException e) {
            throw new GuestSaveException("Error saving the guest to DK database", e);
        }
    }

    public void saveGuestToUS(Guest guest) {
        try {
            saveGuest(guest, databaseConnector.getUSConnection());
        } catch (GuestSaveException e) {
            throw new GuestSaveException("Error saving the guest to US database", e);
        }
    }
    public List<String> getAllCompaniesInUS() {
        try {
            return getAllCompanies(databaseConnector.getUSConnection());
        } catch (GuestCompaniesException e) {
            throw new GuestCompaniesException("Error returning the companies from US database", e);
        } catch (Exception e) {
            // If an unexpected exception is caught, return an empty list
            return new ArrayList<>(); // return an empty list
        }
    }


    public List<String> getAllCompaniesInDK() {
        try {
            return getAllCompanies(databaseConnector.getDKConnection());
        } catch (GuestCompaniesException e) {
            throw new GuestCompaniesException("Error returning the companies from DK database", e);
        } catch (Exception e) {
            // If an unexpected exception is caught, return an empty list
            return new ArrayList<>(); // return an empty list
        }
    }


    private void saveGuest(Guest guest, Connection connection) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO guests (id, name, company, checkInTime) VALUES (?, ?, ?, ?)"
            );
            statement.setInt(1, guest.getId());
            statement.setString(2, guest.getName());
            statement.setString(3, guest.getCompany());
            statement.setString(4, guest.getCheckInTime());
            statement.executeUpdate();

            // Close the statement and connection
            statement.close();
            databaseConnector.closeConnection(connection);
        } catch (SQLException e) {
            throw new GuestSaveException("Error saving guest to database", e);
        }
    }
    private List<String> getAllCompanies(Connection connection) {
        List<String> companies = new ArrayList<>();

        try {
            //returns unique company names with distinct.
            PreparedStatement statement = connection.prepareStatement("SELECT DISTINCT company FROM guests");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                companies.add(resultSet.getString("company"));
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {

        } finally {
            if (connection != null) {
                databaseConnector.closeConnection(connection);
            }
        }

        return companies;
    }


}
