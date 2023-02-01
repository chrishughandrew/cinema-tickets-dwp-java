package uk.gov.dwp.uc.pairtest.domain;

/**
 * Immutable Object
 */

public class TicketTypeRequest {

    private final int noOfTickets;
    private final Type type;

    public TicketTypeRequest(Type type, int noOfTickets) {
        this.type = type;
        this.noOfTickets = noOfTickets;
    }

    public int getNoOfTickets() {
        return noOfTickets;
    }

    public Type getTicketType() {
        return type;
    }

    public enum Type {
        ADULT, CHILD , INFANT
    }

    public int getTicketPrice() {
        int price;
        
        switch (type){
            case ADULT -> price = 20;
            case CHILD -> price = 10;
            case INFANT-> price = 0;
            default -> throw new IllegalStateException("Ticket type price not implemented.");
        }
        
        return price;
    }

}
