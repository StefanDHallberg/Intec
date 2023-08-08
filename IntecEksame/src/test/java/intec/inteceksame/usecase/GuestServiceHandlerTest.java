package intec.inteceksame.usecase;

import intec.inteceksame.entity.Guest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@SpringBootTest
public class GuestServiceHandlerTest {

    @Autowired
    private GuestServiceHandler guestServiceHandler;

    @MockBean
    private GuestRepository guestRepository;

    @Test
    public void testRegisterGuest() {
        // Arrange
        Guest guest = new Guest();
        guest.setSaveOption("dk");

        // Act
        guestServiceHandler.registerGuest(guest);

        // Assert
        verify(guestRepository, times(1)).saveGuestToDK(guest);
    }
}
