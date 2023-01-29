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
    public void purchaseTickets_ThrowsInvalidPurchaseException_WhenNoAdultTicketPresent(){
        //arrange
        exception.expect(InvalidPurchaseException.class);
        exception.expectMessage("Every request requires an Adult ticket");
        
        Long accountId = 20L;
        TicketTypeRequest request = new TicketTypeRequest(Type.INFANT, 1);
        
        //act
        ticketService.purchaseTickets(accountId, request);

        //assert - via the @Rule
    }

    @Test
    public void purchaseTickets_DoesNotThrow_WhenAdultTicketPresent(){
        //arrange
        TicketTypeRequest request1 = new TicketTypeRequest(Type.INFANT, 1);
        TicketTypeRequest request2 = new TicketTypeRequest(Type.ADULT, 1);
        Long accountId = 20L;
        
        //act
        ticketService.purchaseTickets(accountId, request1, request2);

        //assert - via the @Rule
    }

    @Test
    public void purchaseTickets_ThrowsInvalidPurchaseException_WhenRequestedQtyExceedsMax(){
        //arrange
        exception.expect(InvalidPurchaseException.class);
        exception.expectMessage("The maximum number of tickets has been exceeded");
        
        Long accountId = 20L;
        TicketTypeRequest request1 = new TicketTypeRequest(Type.INFANT, 2);
        TicketTypeRequest request2 = new TicketTypeRequest(Type.ADULT, 19);
        
        //act
        ticketService.purchaseTickets(accountId, request1, request2);

        //assert - via the @Rule
    }

    @Test
    public void purchaseTickets_DoesNotThrow_WhenRequestedQtyWithinMax(){
        //arrange 
        TicketTypeRequest request1 = new TicketTypeRequest(Type.INFANT, 1);
        TicketTypeRequest request2 = new TicketTypeRequest(Type.ADULT, 19);
        Long accountId = 20L;
        //act
        ticketService.purchaseTickets(accountId, request1, request2);

        //assert - via the @Rule
    }

    @Test
    public void purchaseTickets_ThrowsInvalidPurchaseException_WhenRequestedInfantsExceedsAdults(){
        //arrange
        exception.expect(InvalidPurchaseException.class);
        exception.expectMessage("There should be an adult's knee for every infant present");
        
        Long accountId = 20L;
        TicketTypeRequest request1 = new TicketTypeRequest(Type.INFANT, 19);
        TicketTypeRequest request2 = new TicketTypeRequest(Type.ADULT, 1);
        
        //act
        ticketService.purchaseTickets(accountId, request1, request2);

        //assert - via the @Rule
    }

    @Test
    public void purchaseTickets_DoesNotThrow_WhenRequestedAdultsExceedInfants(){
        //arrange 
        TicketTypeRequest request1 = new TicketTypeRequest(Type.INFANT, 1);
        TicketTypeRequest request2 = new TicketTypeRequest(Type.ADULT, 19);
        Long accountId = 20L;
        //act
        ticketService.purchaseTickets(accountId, request1, request2);

        //assert - via the @Rule
    }

    @Test
    public void purchaseTickets_DoesNotThrow_WhenRequestedAdultsMatchInfants(){
        //arrange 
        TicketTypeRequest request1 = new TicketTypeRequest(Type.INFANT, 1);
        TicketTypeRequest request2 = new TicketTypeRequest(Type.INFANT, 2);
        TicketTypeRequest request3 = new TicketTypeRequest(Type.ADULT, 10);
        Long accountId = 20L;
        //act
        ticketService.purchaseTickets(accountId, request1, request2, request3);

        //assert - via the @Rule
    }
}
