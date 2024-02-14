package org.shatskii.cinemademo.entity;


import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "seats")
public class SeatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;

    @Column(name = "row")
    private int row;

    @Column(name = "number")
    private int seatNumber;

    @Column(name = "is_free")
    String isFree;

    @Column(name = "price")
    private int price;



    public SeatEntity(int row, int seatNumber, String isFree, int price) {
        this.row = row;
        this.seatNumber = seatNumber;
        this.isFree = isFree;
        this.price = price;
    }

    public SeatEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getIsFree() {
        return isFree;
    }

    public void setIsFree(String status) {
        this.isFree = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeatEntity that = (SeatEntity) o;
        return id == that.id && row == that.row && seatNumber == that.seatNumber && price == that.price && isFree.equals(that.isFree);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, row, seatNumber, price, isFree);
    }
}
