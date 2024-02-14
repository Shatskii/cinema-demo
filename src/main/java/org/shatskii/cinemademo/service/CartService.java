package org.shatskii.cinemademo.service;

import org.shatskii.cinemademo.entity.CartEntity;
import org.shatskii.cinemademo.repository.BookingRepository;
import org.shatskii.cinemademo.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CartService {
    private CartRepository cartRepository;


    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public CartEntity saveCart(CartEntity cartEntity) {
        return cartRepository.save(cartEntity);
    }


    public CartEntity getCartBySession(String session) {
        return cartRepository.findBySessionId(session);
    }

}
