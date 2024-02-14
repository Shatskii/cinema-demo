package org.shatskii.cinemademo.repository;

import org.shatskii.cinemademo.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartEntity, Integer> {
     CartEntity findBySessionId(String session);
}
