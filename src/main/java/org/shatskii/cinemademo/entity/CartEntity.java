package org.shatskii.cinemademo.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "carts")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "session_id")
    private String sessionId;

    public CartEntity() {
    }

    public CartEntity(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartEntity cartEntity = (CartEntity) o;
        return id == cartEntity.id && sessionId.equals(cartEntity.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sessionId);
    }
}
