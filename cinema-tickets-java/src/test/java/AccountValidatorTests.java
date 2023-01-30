import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import uk.gov.dwp.uc.pairtest.AccountValidator;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

public class AccountValidatorTests {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void validateAccountId_ThrowsInvalidPurchaseException_WhenIdLessThanZero(){
        //arrange
        exception.expect(InvalidPurchaseException.class);
        exception.expectMessage("The Account ID is not valid.");
        
        long testId = -1;
        
        //act
        AccountValidator.validateAccountId(testId);

        //assert - via the @Rule
    }

    @Test
    public void validateAccountId_ThrowsInvalidPurchaseException_WhenIdEqualsZero(){
        //arrange
        exception.expect(InvalidPurchaseException.class);
        exception.expectMessage("The Account ID is not valid.");
        
        long testId = 0;
        
        //act
        AccountValidator.validateAccountId(testId);

        //assert - via the @Rule
    }

    @Test
    public void validateAccountId_ThrowsInvalidPurchaseException_WhenIdGreaterThanZero(){
        //arrange
        long testId = 1;
        //act
        AccountValidator.validateAccountId(testId);

        //assert - via the @Rule
    }
}
