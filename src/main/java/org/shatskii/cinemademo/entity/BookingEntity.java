package org.shatskii.cinemademo.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bookings")
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "seat_id")
    private int seatId;

    @Column(name = "cart_id")
    private int cartId;


    public BookingEntity(int seatId, int cartId) {
        this.seatId = seatId;
        this.cartId = cartId;
    }

    public BookingEntity() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingEntity that = (BookingEntity) o;
        return id == that.id && seatId == that.seatId && cartId == that.cartId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, seatId, cartId);
    }
}
