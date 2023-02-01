package uk.gov.dwp.uc.pairtest;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest.Type;

public class RequestCalculator {
    // treating as an equivalent of a public static class in c# 
    // should I have used inner/nested in TicketService with Java? 
    
    /** 
     * Adds together the quantity of all tickets, given a particular type, from the overall 
     * collection of requests, returning the total.
     * 
     * @param type  the type of ticket request made: Adult, Child, or Infant
     * @param ticketTypeRequests the collection of type requests which constitutes the whole request
     * @return the total number of tickets for that Type
     */
    public static int sumOfRequestedTicketsByType(Type type, TicketTypeRequest... ticketTypeRequests){
        
        int totalTicketsForType = 0;
        for (TicketTypeRequest request : ticketTypeRequests){
            if (request.getTicketType() == type) 
                totalTicketsForType += request.getNoOfTickets();
        }

        return totalTicketsForType;
    }

    /** 
     * Adds together the number of seats required for a reservation. Infants do not require a seat
     * 
     * @param ticketTypeRequests the collection of type requests which constitutes the whole request
     * @return the total number of seats required
     */
    public static int sumOfRequiredSeatsFromRequest(TicketTypeRequest... ticketTypeRequests){
        int seatTotal =
            sumOfRequestedTicketsByType(Type.ADULT, ticketTypeRequests) + 
            sumOfRequestedTicketsByType(Type.CHILD, ticketTypeRequests);
        
        return seatTotal;
    }
}
