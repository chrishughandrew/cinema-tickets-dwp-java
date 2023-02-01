package uk.gov.dwp.uc.pairtest;

import thirdparty.paymentgateway.TicketPaymentService;
import thirdparty.seatbooking.SeatReservationService;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

public class TicketServiceImpl implements TicketService {
    
    private TicketPaymentService ticketPaymentService;
    private SeatReservationService seatReservationService;

    public TicketServiceImpl(TicketPaymentService ticketPaymentService, SeatReservationService seatReservationService) {
        super();

        this.seatReservationService = seatReservationService;
        this.ticketPaymentService = ticketPaymentService;

    }

    /**
     * Should only have private methods other than the one below.
     */

    /** 
     * Takes an account and a booking request, validates the the request to meet busniess rule reqs,
     * then creates seat reservation and takes payment.
     *
     * @param accountId the account attempting to make the booking.
     * @param ticketTypeRequests the collection of type requests which constitutes the whole request
     * @exception InvalidPurchaseException indvalidates the request and advises which rule has been broken 
     */

    @Override
    public void purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        
        //null checks on arguments
        if (ticketTypeRequests == null) throw new IllegalArgumentException();
        if (accountId == null) throw new IllegalArgumentException();

        //validate account and requests
        AccountValidator.validateAccountId(accountId);
        validateRequest(ticketTypeRequests);

        //sum up the ticket costs and make payment
        int totalTicketCost = RequestCalculator.sumRequestCost(ticketTypeRequests);
        this.ticketPaymentService.makePayment(accountId, totalTicketCost);
        
        //sum up the required seats and reserve
        int totalSeatsRequired =  RequestCalculator.sumOfRequiredSeatsFromRequest(ticketTypeRequests);
        this.seatReservationService.reserveSeat(accountId, totalSeatsRequired);
    }


    /** 
     * Validates the range of buisiness rules related to the complete request, and throws an exception
     * if any rules have been violated. Continues with normal flow of execution if rules are met. 
     * 
     * @param ticketTypeRequests the collection of type requests which constitutes the whole request
     * @exception InvalidPurchaseException indvalidates the request and advises which rule has been broken 
     */
    private void validateRequest(TicketTypeRequest... ticketTypeRequests)
            throws InvalidPurchaseException {
        
        RequestValidator.requestHasAdultTicket(ticketTypeRequests);
        RequestValidator.ticketQuantityWithinMaxBounds(ticketTypeRequests);
        RequestValidator.infantsDoNotExceedAdults(ticketTypeRequests);
    }

}
