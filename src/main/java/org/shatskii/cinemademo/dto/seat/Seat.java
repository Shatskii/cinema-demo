package org.shatskii.cinemademo.dto.seat;

public class Seat {
    private int seatNumber;
    private String isFree;
    private int price;

    public Seat(int seatNumber, String isFree, int price) {
        this.seatNumber = seatNumber;
        this.isFree = isFree;
        this.price = price;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getIsFree() {
        return isFree;
    }

    public void setIsFree(String isFree) {
        this.isFree = isFree;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
