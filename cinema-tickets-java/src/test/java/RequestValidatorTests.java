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

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void requestHasAdultTicket_ThrowsInvalidPurchaseException_WhenNoAdultTicketPresent(){
        //arrange
        exception.expect(InvalidPurchaseException.class);
        exception.expectMessage("Every request requires an Adult ticket");
        //var request = new TicketTypeRequest(Type.INFANT, 1);
        
        //act
        requestValidator.requestHasAdultTicket(new TicketTypeRequest(Type.INFANT, 1));

        //assert - via the @Rule
    }

    @Test
    public void requestHasAdultTicket_DoesNotThrow_WhenAdultTicketPresent(){
        //arrange
        var request1 = new TicketTypeRequest(Type.INFANT, 1);
        var request2 = new TicketTypeRequest(Type.ADULT, 1);
        
        //act
        requestValidator.requestHasAdultTicket(request1, request2);

        //assert - via the @Rule
    }

}
