package uk.gov.dwp.uc.pairtest;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

public interface RequestValidator {
    void validateRequest(Long accountId, int maxAllowedTickets, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException;
    
    void accountIsValid(Long accountId) throws InvalidPurchaseException;
    
    void requestIsNotNull(TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException;
    
    void requestHasAdultTicket(TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException;
    
    void ticketQuantityWithinMaxBounds(int maxAllowedTickets,TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException;
    
    void infantsDoNotExceedAdults(TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException;
}

