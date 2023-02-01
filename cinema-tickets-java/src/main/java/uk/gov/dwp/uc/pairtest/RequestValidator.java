package uk.gov.dwp.uc.pairtest;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest.Type;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

public class RequestValidator {
    // treating as an equivalent of a public static class in c# 
    // should I have used inner/nested in TicketService with Java? 

    private static final int MAX_TICKETS = 20;
    /** 
     * Validates the requirement that an Adult must be present for every request.
     * 
     * @param ticketTypeRequests the collection of type requests which constitutes the whole request
     * @exception InvalidPurchaseException invalidates the request and advises that adult is required 
     */
    public static void requestHasAdultTicket(TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {

        if (RequestCalculator.sumOfRequestedTicketsByType(Type.ADULT, ticketTypeRequests) > 0)
            return;
 
        throw new InvalidPurchaseException("Every request requires an Adult ticket");  
    }

     /** 
     * Validates the requirement that a maximum number of tickets can be requested 
     * (uses globally defined variable for MAX_TICKETS)
     * 
     * @param ticketTypeRequests the collection of type requests which constitutes the whole request
     * @exception InvalidPurchaseException indvalidates the request and advises maximum has been exceeded
     */
    public static void ticketQuantityWithinMaxBounds(TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {

        int totalTicketsRequested = 0;
        for (TicketTypeRequest request :ticketTypeRequests)
            totalTicketsRequested += request.getNoOfTickets();
                
        if (totalTicketsRequested > MAX_TICKETS) 
            throw new InvalidPurchaseException("The maximum number of tickets has been exceeded");
        
    }

     /** 
     * Validates the requirement that there should be an Adult's knee for every Infant to sit on. 
     * (Assumption: Surely one adult couldn't have 19 infants on their knee)
     * 
     * @param ticketTypeRequests the collection of type requests which constitutes the whole request
     * @exception InvalidPurchaseException indvalidates the request and advises maximum has been exceeded
     */
    public static void infantsDoNotExceedAdults(TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        int adultTickets = RequestCalculator.sumOfRequestedTicketsByType(Type.ADULT, ticketTypeRequests);
        int infantTickets = RequestCalculator.sumOfRequestedTicketsByType(Type.INFANT, ticketTypeRequests);
        
        if(infantTickets > adultTickets)
            throw new InvalidPurchaseException(
                "There should be an adult's knee for every infant present");
        
    }

}
