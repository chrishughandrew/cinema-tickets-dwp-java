package uk.gov.dwp.uc.pairtest;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest.Type;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

public class TicketServiceImpl implements TicketService {
    private static final int MAX_TICKETS = 20;
    
    /**
     * Should only have private methods other than the one below.
     */

    @Override
    public void purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        
        //TODO: if (ticketTypeRequests == null) throw new IllegalArgumentException();

        //TODO: validateAccount(accountId);
        validateRequest(ticketTypeRequests);
    }


    /** 
     * Validates the range of buisiness rules related to the complete request and throws an exception
     * if any rules have been violated. Continues with normal flow of execution  if rules are met. 
     * 
     * @param ticketTypeRequests the collection of type requests which constitutes the whole request
     * @exception InvalidPurchaseException indvalidates the request and advises which rule has been broken 
     */
    private void validateRequest(TicketTypeRequest... ticketTypeRequests)
            throws InvalidPurchaseException {
        
        requestHasAdultTicket(ticketTypeRequests);
        ticketQuantityWithinMaxBounds(ticketTypeRequests);
        infantsDoNotExceedAdults(ticketTypeRequests);
    }


    private void validateAccount(Long accountId) throws InvalidPurchaseException {
        throw new UnsupportedOperationException();
        
    }
    


     /** 
     * Validates the requirement that an Adult must be present for every request.
     * 
     * @param ticketTypeRequests the collection of type requests which constitutes the whole request
     * @exception InvalidPurchaseException invalidates the request and advises that adult is required 
     */
    private void requestHasAdultTicket(TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {

        if (sumOfRequestedTicketsByType(Type.ADULT, ticketTypeRequests) > 0)
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
    private void ticketQuantityWithinMaxBounds(TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {

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
    private void infantsDoNotExceedAdults(TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        int adultTickets = sumOfRequestedTicketsByType(Type.ADULT, ticketTypeRequests);
        int infantTickets = sumOfRequestedTicketsByType(Type.INFANT, ticketTypeRequests);
        
        if(infantTickets > adultTickets)
            throw new InvalidPurchaseException(
                "There should be an adult's knee for every infant present");
        
    }

    /** 
     * Adds together the quantity of all tickets, given a particular type, from the overall 
     * collection of requests and returns the total.
     * 
     * @param type  the type of ticket request made: Adult, Child, or Infant
     * @param ticketTypeRequests the collection of type requests which constitutes the whole request
     * @return the total number of tickets for that Type
     */
    private int sumOfRequestedTicketsByType(Type type, TicketTypeRequest... ticketTypeRequests){
        
        int totalTicketsForType = 0;
        for (TicketTypeRequest request : ticketTypeRequests){
            if (request.getTicketType() == type) 
                totalTicketsForType += request.getNoOfTickets();
        }

        return totalTicketsForType;
    }

    // private int sumOfRequiredSeatsFromRequest(TicketTypeRequest ticketTypeRequest){
        //throw new UnsupportedOperationException();
    // }

}
