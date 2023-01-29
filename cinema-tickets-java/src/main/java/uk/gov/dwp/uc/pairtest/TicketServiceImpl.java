package uk.gov.dwp.uc.pairtest;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

public class TicketServiceImpl implements TicketService {
    /**
     * Should only have private methods other than the one below.
     */

    @Override
    public void purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
    }
    //TODO: null check on ticketTypeRequests?


    @Override
    // TODO: should be private
    public void accountIsValid(Long accountId) throws InvalidPurchaseException {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    // TODO: should be private
    public void validateRequest(int maxAllowedTickets, TicketTypeRequest... ticketTypeRequests)
            throws InvalidPurchaseException {
        // TODO Auto-generated method stub
        
    }


}
