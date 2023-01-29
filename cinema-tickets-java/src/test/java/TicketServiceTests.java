import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import uk.gov.dwp.uc.pairtest.TicketServiceImpl;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest.Type;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

public class TicketServiceTests {
    
    //setup the implemetation to test
    private TicketServiceImpl ticketService= new TicketServiceImpl();

    @Rule
    public ExpectedException exception = ExpectedException.none();



    @Test
    public void purchaseTickets_ThrowsIllegalArgumentException_WhenNullIsPassedAsTicketRequestTypeArgument(){
        //arrange
        exception.expect(IllegalArgumentException.class);
        
        Long accountId = 20L;

        //act
        ticketService.purchaseTickets(accountId, null);

        //assert - via the @Rule
    }

    @Test
    public void purchaseTickets_DoesNotThrow_WhenTicketRequestTypeIsPassedAsArgument(){
        //arrange 
        TicketTypeRequest request1 = new TicketTypeRequest(Type.ADULT, 1);
        Long accountId = 20L;
        //act
        ticketService.purchaseTickets(accountId, request1);

        //assert - via the @Rule
    }

}
