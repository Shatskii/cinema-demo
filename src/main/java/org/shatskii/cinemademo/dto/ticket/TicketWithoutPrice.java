package org.shatskii.cinemademo.dto.ticket;

public class TicketWithoutPrice {
    private int row;
    private int seatNumber;


    public TicketWithoutPrice(int row, int seatNumber) {
        this.row = row;
        this.seatNumber = seatNumber;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

}
