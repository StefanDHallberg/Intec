package intec.inteceksame.interface_adapters;

import intec.inteceksame.entity.Guest;
import intec.inteceksame.usecase.GuestRepository;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class GuestRepositoryAdapter implements GuestRepository {
    private final GuestDataAccessor guestDataAccessor;

    public GuestRepositoryAdapter(GuestDataAccessor guestDataAccessor) {
        this.guestDataAccessor = guestDataAccessor;
    }

    @Override
    public void saveGuestToDK(Guest guest) {
        guestDataAccessor.saveGuestToDK(guest);
    }

    @Override
    public void saveGuestToUS(Guest guest) {
        guestDataAccessor.saveGuestToUS(guest);
    }

    @Override
    public List<String> getAllCompaniesInDK(){
        return guestDataAccessor.getAllCompaniesInDK();
    }
    @Override
    public List<String> getAllCompaniesInUS(){
        return guestDataAccessor.getAllCompaniesInUS();
    }
}
