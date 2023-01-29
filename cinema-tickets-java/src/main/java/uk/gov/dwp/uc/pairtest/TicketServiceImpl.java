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
        
        if (ticketTypeRequests == null) throw new IllegalArgumentException();

        validateRequest(ticketTypeRequests);
    }



    private void validateRequest(TicketTypeRequest... ticketTypeRequests)
            throws InvalidPurchaseException {
        
        requestHasAdultTicket(ticketTypeRequests);
        
    }


    private void validateAccount(Long accountId) throws InvalidPurchaseException {
        throw new UnsupportedOperationException();
        
    }
    


     /** 
     * Validates the requirement that an Adult must be present for every request.
     * 
     * @param ticketTypeRequests the collection of type requests which constitutes the whole request
     * @exception InvalidPurchaseException indvalidates the request and advises that adult is required 
     */
    private void requestHasAdultTicket(TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        if (sumOfRequestedTicketsByType(Type.ADULT, ticketTypeRequests) > 0){
                return;
        }
            
        throw new InvalidPurchaseException("Every request requires an Adult ticket");  
    }

     /** 
     * Validates the requirement that a maximum number of tickets can be requested
     * 
     * @param maxAllowedTickets  the upper limit of tickets a user can request
     * @param ticketTypeRequests the collection of type requests which constitutes the whole request
     * @exception InvalidPurchaseException indvalidates the request and advises maximum has been exceeded
     */

    private void ticketQuantityWithinMaxBounds(TicketTypeRequest... ticketTypeRequests)
            throws InvalidPurchaseException {
        int totalTicketsRequested = 0;
        for (TicketTypeRequest request :ticketTypeRequests){
            totalTicketsRequested += request.getNoOfTickets();
        }
        
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
        throw new UnsupportedOperationException(); //forces the unit test to fail before it's been implenented.
        
    }

    private int sumOfRequestedTicketsByType(Type type, TicketTypeRequest... ticketTypeRequests){
        int totalTicketsForType = 0;
        for (TicketTypeRequest request : ticketTypeRequests){
            if (request.getTicketType() == type) 
                totalTicketsForType += request.getNoOfTickets();
        }

        return totalTicketsForType;
    }

    // private int sumOfRequiredSeats(TicketTypeRequest ticketTypeRequest){
        //throw new UnsupportedOperationException();
    // }

}
