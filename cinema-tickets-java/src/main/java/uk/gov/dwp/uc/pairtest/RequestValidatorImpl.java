package uk.gov.dwp.uc.pairtest;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest.Type;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

public class RequestValidatorImpl implements RequestValidator {



    @Override
    public void requestHasAdultTicket(TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        for(TicketTypeRequest request :ticketTypeRequests){
            if(request.getTicketType() == Type.ADULT && request.getNoOfTickets() > 0){
                return;
            }
        }
        
        throw new InvalidPurchaseException("Every request requires an Adult ticket");  
    }

    @Override
    public void ticketQuantityWithinMaxBounds(int maxAllowedTickets, TicketTypeRequest... ticketTypeRequests)
            throws InvalidPurchaseException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void infantsDoNotExceedAdults(TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        // TODO Auto-generated method stub
        
    }
    
}
