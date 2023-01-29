import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import uk.gov.dwp.uc.pairtest.RequestValidator;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest.Type;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

public class RequestValidatorTests {    

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void requestHasAdultTicket_ThrowsInvalidPurchaseException_WhenNoAdultTicketPresent(){
        //arrange
        exception.expect(InvalidPurchaseException.class);
        exception.expectMessage("Every request requires an Adult ticket");
        
        TicketTypeRequest request = new TicketTypeRequest(Type.INFANT, 1);
        
        //act
        RequestValidator.requestHasAdultTicket(request);

        //assert - via the @Rule
    }

    @Test
    public void requestHasAdultTicket_DoesNotThrow_WhenAdultTicketPresent(){
        //arrange
        TicketTypeRequest request1 = new TicketTypeRequest(Type.INFANT, 1);
        TicketTypeRequest request2 = new TicketTypeRequest(Type.ADULT, 1);
        
        //act
        RequestValidator.requestHasAdultTicket(request1, request2);

        //assert - via the @Rule
    }

    @Test
    public void ticketQuantityWithinMaxBounds_ThrowsInvalidPurchaseException_WhenRequestedQtyExceedsMax(){
        //arrange
        exception.expect(InvalidPurchaseException.class);
        exception.expectMessage("The maximum number of tickets has been exceeded");
        
        TicketTypeRequest request1 = new TicketTypeRequest(Type.INFANT, 2);
        TicketTypeRequest request2 = new TicketTypeRequest(Type.ADULT, 19);
        
        //act
        RequestValidator.ticketQuantityWithinMaxBounds(request1, request2);

        //assert - via the @Rule
    }

    @Test
    public void ticketQuantityWithinMaxBounds_DoesNotThrow_WhenRequestedQtyWithinMax(){
        //arrange 
        TicketTypeRequest request1 = new TicketTypeRequest(Type.INFANT, 1);
        TicketTypeRequest request2 = new TicketTypeRequest(Type.ADULT, 19);

        //act
        RequestValidator.ticketQuantityWithinMaxBounds(request1, request2);

        //assert - via the @Rule
    }

    @Test
    public void infantsDoNotExceedAdults_ThrowsInvalidPurchaseException_WhenRequestedInfantsExceedsAdults(){
        //arrange
        exception.expect(InvalidPurchaseException.class);
        exception.expectMessage("There should be an adult's knee for every infant present");
        
        TicketTypeRequest request1 = new TicketTypeRequest(Type.INFANT, 19);
        TicketTypeRequest request2 = new TicketTypeRequest(Type.ADULT, 1);
        
        //act
        RequestValidator.infantsDoNotExceedAdults(request1, request2);

        //assert - via the @Rule
    }

    @Test
    public void infantsDoNotExceedAdults_DoesNotThrow_WhenRequestedAdultsExceedInfants(){
        //arrange 
        TicketTypeRequest request1 = new TicketTypeRequest(Type.INFANT, 1);
        TicketTypeRequest request2 = new TicketTypeRequest(Type.ADULT, 19);
        //act
        RequestValidator.infantsDoNotExceedAdults(request1, request2);

        //assert - via the @Rule
    }

    @Test
    public void infantsDoNotExceedAdults_DoesNotThrow_WhenRequestedAdultsMatchInfants(){
        //arrange 
        TicketTypeRequest request1 = new TicketTypeRequest(Type.INFANT, 1);
        TicketTypeRequest request2 = new TicketTypeRequest(Type.INFANT, 2);
        TicketTypeRequest request3 = new TicketTypeRequest(Type.ADULT, 10);
        //act
        RequestValidator.infantsDoNotExceedAdults(request1, request2, request3);

        //assert - via the @Rule
    }
    }    