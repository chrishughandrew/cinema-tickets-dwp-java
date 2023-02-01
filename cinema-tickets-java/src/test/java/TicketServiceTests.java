import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import thirdparty.paymentgateway.TicketPaymentServiceImpl;
import thirdparty.seatbooking.SeatReservationServiceImpl;
import uk.gov.dwp.uc.pairtest.TicketServiceImpl;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest.Type;

public class TicketServiceTests {
    
    //setup the implemetation to test - surely there must be a better way of doing this? find out.
    private TicketServiceImpl ticketService= new TicketServiceImpl(new TicketPaymentServiceImpl(), new SeatReservationServiceImpl());

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void purchaseTickets_ThrowsIllegalArgumentException_WhenTicketTypeRequestArgumentIsNull(){
        //arrange
        exception.expect(IllegalArgumentException.class);
        
        Long accountId = 20L;

        //act
        ticketService.purchaseTickets(accountId, null);

        //assert - via the @Rule
    }

    @Test
    public void purchaseTickets_ThrowsIllegalArgumentException_WhenAccountIdArgumentIsNull(){
        //arrange
        exception.expect(IllegalArgumentException.class);
        
        TicketTypeRequest request = new TicketTypeRequest(Type.ADULT, 1);

        //act
        ticketService.purchaseTickets(null, request);

        //assert - via the @Rule
    }

}
