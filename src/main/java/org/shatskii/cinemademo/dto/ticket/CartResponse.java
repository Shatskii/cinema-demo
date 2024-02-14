package org.shatskii.cinemademo.dto.ticket;

import java.util.List;

public class CartResponse {
    private int totalPrice;
    private List<TicketWithPrice> tickets;

    public CartResponse(List<TicketWithPrice> tickets) {
        this.tickets = tickets;
        totalPrice = tickets.stream().mapToInt(TicketWithPrice::getPrice).sum();
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<TicketWithPrice> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketWithPrice> tickets) {
        this.tickets = tickets;
    }
}
