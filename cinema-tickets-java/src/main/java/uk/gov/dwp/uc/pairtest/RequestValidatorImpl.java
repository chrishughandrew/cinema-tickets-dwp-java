package uk.gov.dwp.uc.pairtest;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest.Type;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

public class RequestValidatorImpl implements RequestValidator {



    /** 
     * Validates the requirement that an Adult must be present for every request.
     * 
     * @param ticketTypeRequests the collection of type requests which constitutes the whole request
     * @exception InvalidPurchaseException indvalidates the request and advises that adult is required 
     */
    @Override
    public void requestHasAdultTicket(TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        for(TicketTypeRequest request :ticketTypeRequests){
            if (request.getTicketType() == Type.ADULT && request.getNoOfTickets() > 0){
                return;
            }
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
    @Override
    public void ticketQuantityWithinMaxBounds(int maxAllowedTickets, TicketTypeRequest... ticketTypeRequests)
            throws InvalidPurchaseException {
        int totalTicketsRequested = 0;
        for (TicketTypeRequest request :ticketTypeRequests){
            totalTicketsRequested += request.getNoOfTickets();
        }
        
        if (totalTicketsRequested > maxAllowedTickets) 
            throw new InvalidPurchaseException("The maximum number of tickets has been exceeded");
        
    }

     /** 
     * Validates the requirement that there should be an Adult's knee for every Infant to sit on.
     * 
     * @param ticketTypeRequests the collection of type requests which constitutes the whole request
     * @exception InvalidPurchaseException indvalidates the request and advises maximum has been exceeded
     */
    @Override
    public void infantsDoNotExceedAdults(TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        throw new UnsupportedOperationException(); //forces the unit test to fail intially.
        
    }
    
}
