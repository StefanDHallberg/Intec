package intec.inteceksame.usecase;

import intec.inteceksame.entity.Guest;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.List;

@Service
public class GuestServiceHandler {
    private final GuestRepository guestRepository;

    public GuestServiceHandler(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public String registerGuest(Guest guest) {
        ZoneId guestTimeZone = ZoneId.systemDefault();

        // Base TimeZone on systemDefault.
        if (guestTimeZone.equals(ZoneId.of("America/New_York"))) {
            guest.setSaveOption("us"); //UTC -5 Eastern Time US
        } else if (guestTimeZone.equals(ZoneId.of("Europe/Copenhagen"))) {
            guest.setSaveOption("dk"); //UTC+1 KBH
        } else {
            throw new IllegalArgumentException("Invalid time zone: " + guestTimeZone);
        }

        // Register the guest
        if ("dk".equalsIgnoreCase(guest.getSaveOption())) {
            guestRepository.saveGuestToDK(guest);
        } else if ("us".equalsIgnoreCase(guest.getSaveOption())) {
            guestRepository.saveGuestToUS(guest);
        } else {
            throw new IllegalArgumentException("Invalid save option: " + guest.getSaveOption());
        }
        return "success";
    }
        public List<String> getAllCompanies() {
            ZoneId guestTimeZone = ZoneId.systemDefault();

            // Base TimeZone on systemDefault.
            if (guestTimeZone.equals(ZoneId.of("America/New_York"))) {
                return guestRepository.getAllCompaniesInUS(); //UTC -5 Eastern Time US
            } else if (guestTimeZone.equals(ZoneId.of("Europe/Copenhagen"))) {
                return guestRepository.getAllCompaniesInDK(); //UTC+1 KBH
            } else {
                throw new IllegalArgumentException("Invalid time zone: " + guestTimeZone);
            }
    }
}


