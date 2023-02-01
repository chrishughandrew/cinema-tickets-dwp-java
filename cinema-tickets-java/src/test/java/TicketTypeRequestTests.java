import static org.junit.Assert.assertEquals;

import org.junit.Test;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest.Type;

public class TicketTypeRequestTests {
    

    // again - should have paramterized these tests.
    @Test
    public void getTicketPrice_ReturnsCorrectAdultValue(){
        //arrange
        TicketTypeRequest request = new TicketTypeRequest(Type.ADULT, 1);

        int expectedCost = 20;
        //act
        int cost = request.getTicketPrice();

        //assert
        assertEquals(expectedCost, cost);
    }

    @Test
    public void getTicketPrice_ReturnsCorrectInfantValue(){
        //arrange
        TicketTypeRequest request = new TicketTypeRequest(Type.INFANT, 1);

        int expectedCost = 0;
        //act
        int cost = request.getTicketPrice();

        //assert
        assertEquals(expectedCost, cost);
    }

    @Test
    public void getTicketPrice_ReturnsCorrectChildValue(){
        //arrange
        TicketTypeRequest request = new TicketTypeRequest(Type.CHILD, 1);

        int expectedCost = 10;
        //act
        int cost = request.getTicketPrice();

        //assert
        assertEquals(expectedCost, cost);
    }

    // MISSING_TEST
    // TODO: should have a test to ensurethe Exception is thrown
    // if a new ticket type was implemented only partially. Would have
    // add a dummy Type to test for this, which goes outside of the scope
    // of the task. 
}
