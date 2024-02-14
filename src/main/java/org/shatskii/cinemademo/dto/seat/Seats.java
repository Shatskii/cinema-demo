package org.shatskii.cinemademo.dto.seat;

import java.util.List;


public class Seats {
    public int row;
    public List<Seat> seats;

    public Seats(int row, List<Seat> seats) {
        this.row = row;
        this.seats = seats;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
