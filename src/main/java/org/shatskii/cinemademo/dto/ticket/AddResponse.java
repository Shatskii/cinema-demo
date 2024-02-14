package org.shatskii.cinemademo.dto.ticket;

import java.util.List;

public class AddResponse {

    private List<TicketWithoutPrice> tickets;

    public AddResponse(List<TicketWithoutPrice> tickets) {
        this.tickets = tickets;
    }

    public List<TicketWithoutPrice> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketWithoutPrice> tickets) {
        this.tickets = tickets;
    }
}
