import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import uk.gov.dwp.uc.pairtest.RequestValidatorImpl;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest.Type;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

public class RequestValidatorTests {
    
    //setup the implemetation to test
    private RequestValidatorImpl requestValidator = new RequestValidatorImpl();
    static final int MAX_TICKETS = 20;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void requestHasAdultTicket_ThrowsInvalidPurchaseException_WhenNoAdultTicketPresent(){
        //arrange
        exception.expect(InvalidPurchaseException.class);
        exception.expectMessage("Every request requires an Adult ticket");
        var request = new TicketTypeRequest(Type.INFANT, 1);
        
        //act
        requestValidator.requestHasAdultTicket(request);

        //assert - via the @Rule
    }

    @Test
    public void requestHasAdultTicket_DoesNotThrow_WhenAdultTicketPresent(){
        //arrange
        TicketTypeRequest request1 = new TicketTypeRequest(Type.INFANT, 1);
        TicketTypeRequest request2 = new TicketTypeRequest(Type.ADULT, 1);
        
        //act
        requestValidator.requestHasAdultTicket(request1, request2);

        //assert - via the @Rule
    }

    @Test
    public void ticketQuantityWithinMaxBound_ThrowsInvalidPurchaseException_WhenRequestedQtyExceedsMax(){
        //arrange
        exception.expect(InvalidPurchaseException.class);
        exception.expectMessage("The maximum number of tickets has been exceeded");

        TicketTypeRequest request1 = new TicketTypeRequest(Type.INFANT, 2);
        TicketTypeRequest request2 = new TicketTypeRequest(Type.ADULT, 19);
        
        //act
        requestValidator.ticketQuantityWithinMaxBounds(MAX_TICKETS, request1, request2);

        //assert - via the @Rule
    }

    @Test
    public void ticketQuantityWithinMaxBound_DoesNotThrow_WhenRequestedQtyWithinMax(){
        //arrange 
        TicketTypeRequest request1 = new TicketTypeRequest(Type.INFANT, 1);
        TicketTypeRequest request2 = new TicketTypeRequest(Type.ADULT, 19);
        
        //act
        requestValidator.ticketQuantityWithinMaxBounds(MAX_TICKETS, request1, request2);

        //assert - via the @Rule
    }
}
