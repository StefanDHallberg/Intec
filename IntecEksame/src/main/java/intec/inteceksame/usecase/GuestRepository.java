package intec.inteceksame.usecase;

import intec.inteceksame.entity.Guest;

import java.util.List;

public interface GuestRepository {
    void saveGuestToDK(Guest guest);
    void saveGuestToUS(Guest guest);
    List<String> getAllCompaniesInDK();
    List<String> getAllCompaniesInUS();

}
