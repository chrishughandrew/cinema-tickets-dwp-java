import org.junit.Test;

import uk.gov.dwp.uc.pairtest.RequestCalculator;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest.Type;

import static org.junit.Assert.*;

public class RequestCalculatorTests {
    
    @Test
    public void sumOfRequestedTicketsByType_CalucatesCorrectlyForType(){
        //arrange
        TicketTypeRequest[] request = 
            {
                new TicketTypeRequest(Type.ADULT, 3),
                new TicketTypeRequest(Type.INFANT, 1),
                new TicketTypeRequest(Type.CHILD, 2),
                new TicketTypeRequest(Type.ADULT, 5)
            };

        int expectedAdults = 8;

        //act
        int result = RequestCalculator.sumOfRequestedTicketsByType(Type.ADULT, request);
        
        //assert
        assertNotNull(result);
        assertEquals(expectedAdults, result);
    }

    @Test
    public void sumOfRequestedTicketsByType_ReturnsZeroWhenNoneOfTypePresent(){
        //arrange
        TicketTypeRequest[] request = 
        {
            new TicketTypeRequest(Type.ADULT, 3),
            new TicketTypeRequest(Type.INFANT, 1),
            new TicketTypeRequest(Type.ADULT, 5)
        };

        int expectedChild = 0;

        //act
        int result = RequestCalculator.sumOfRequestedTicketsByType(Type.CHILD, request);
        //assert
        assertNotNull(result);
        assertEquals(expectedChild, result);
    }

    @Test
    public void sumOfRequiredSeatsFromRequest_CalculatesCorrectlyWithInfants(){
        //arrange
        TicketTypeRequest[] request = 
        {
            new TicketTypeRequest(Type.ADULT, 3),
            new TicketTypeRequest(Type.INFANT, 1),
            new TicketTypeRequest(Type.CHILD, 5),
            new TicketTypeRequest(Type.INFANT, 1)
        };

        int expectedTotal = 8;

        //act
        int result = RequestCalculator.sumOfRequiredSeatsFromRequest(request);
        //assert
        assertNotNull(result);
        assertEquals(expectedTotal, result);
    }

    @Test
    public void sumOfRequiredSeatsFromRequest_CalculatesCorrectlyWithNoInfants(){
        //arrange
        TicketTypeRequest[] request = 
        {
            new TicketTypeRequest(Type.ADULT, 3),
            new TicketTypeRequest(Type.CHILD, 5),

        };

        int expectedTotal = 8;

        //act
        int result = RequestCalculator.sumOfRequiredSeatsFromRequest(request);
        //assert
        assertNotNull(result);
        assertEquals(expectedTotal, result);
    }

    @Test
    public void sumOfRequiredSeatsFromRequest_ReturnsZeroWithOnlyInfants(){
        //arrange
        TicketTypeRequest[] request = 
        {
            new TicketTypeRequest(Type.INFANT, 3),
            new TicketTypeRequest(Type.INFANT, 1)

        };

        int expectedTotal = 0;

        //act
        int result = RequestCalculator.sumOfRequiredSeatsFromRequest(request);
        //assert
        assertNotNull(result);
        assertEquals(expectedTotal, result);
    }

    // I acknowledge I should really use parameterized testing for these final tests 
    //(and around the suite): if there was more time, I would learn
    // this technique for this language.
    @Test
    public void sumRequestCost_CacluatesCorrectly(){
        //arrange
        TicketTypeRequest[] request = 
        {
            new TicketTypeRequest(Type.ADULT, 3),
            new TicketTypeRequest(Type.INFANT, 3),
            new TicketTypeRequest(Type.CHILD, 3),
            new TicketTypeRequest(Type.INFANT, 1)

        };

        int expectedCost = 90;

        //act
        int result = RequestCalculator.sumRequestCost(request);
        //assert
        assertNotNull(result);
        assertEquals(expectedCost, result);
    }

    //TODO: would benefit more repetitions of this test (parameterised) and could probably do with edge cases.
}
