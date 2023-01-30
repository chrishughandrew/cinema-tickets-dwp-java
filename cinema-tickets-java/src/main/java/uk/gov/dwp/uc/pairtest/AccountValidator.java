package uk.gov.dwp.uc.pairtest;

import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

public class AccountValidator {
    // treating as an equivalent of a public static class in c# 
    // should I have used inner/nested in TicketService with Java? 

    public static void validateAccountId(Long accountId) throws InvalidPurchaseException {
        if (accountId <= 0)
            throw new InvalidPurchaseException("The Account ID is not valid.");        
    }
}
