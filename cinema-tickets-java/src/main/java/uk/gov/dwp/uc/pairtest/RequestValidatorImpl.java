package uk.gov.dwp.uc.pairtest;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

public class RequestValidatorImpl implements RequestValidator {

    @Override
    public void validateRequest(Long accountId, int maxAllowedTickets, TicketTypeRequest... ticketTypeRequests)
            throws InvalidPurchaseException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void accountIsValid(Long accountId) throws InvalidPurchaseException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void requestIsNotNull(TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void requestHasAdultTicket(TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        // TODO Auto-generated method stub
        
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
