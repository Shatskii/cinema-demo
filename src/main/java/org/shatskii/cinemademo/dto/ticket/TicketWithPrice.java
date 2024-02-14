package org.shatskii.cinemademo.dto.ticket;

public class TicketWithPrice {
    private int row;
    private int seatNumber;
    private int price;

    public TicketWithPrice(int row, int seatNumber, int price) {
        this.row = row;
        this.seatNumber = seatNumber;
        this.price = price;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
