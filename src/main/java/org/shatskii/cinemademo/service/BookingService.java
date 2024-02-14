package org.shatskii.cinemademo.service;

import org.shatskii.cinemademo.entity.BookingEntity;
import org.shatskii.cinemademo.entity.CartEntity;
import org.shatskii.cinemademo.entity.SeatEntity;
import org.shatskii.cinemademo.exception.NoSuchTicketException;
import org.shatskii.cinemademo.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {
    private final CartService cartService;
    private final SeatService seatService;
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(CartService cartService, SeatService seatService, BookingRepository bookingRepository) {
        this.cartService = cartService;
        this.seatService = seatService;
        this.bookingRepository = bookingRepository;
    }

    @Transactional
    public List<SeatEntity> getList(String session, List<Integer> seats) {

        CartEntity cartEntity = cartService.getCartBySession(session);
        if (cartEntity == null) {
            cartService.saveCart(new CartEntity(session));
            cartEntity = cartService.getCartBySession(session);
        }
        if (seatService.existsByIdInAndStatusIs(seats, "no")) {
            throw new NoSuchTicketException("Выбранные места заняты");
        }
        List<SeatEntity> allSeatEntityById = seatService.getAllSeatEntityById(seats);
        List<BookingEntity> bookingEntities = new ArrayList<>();
        for (SeatEntity seatEntity : allSeatEntityById) {
            bookingEntities.add(new BookingEntity(seatEntity.getId(), cartEntity.getId()));
            seatEntity.setIsFree("no");
        }
        bookingRepository.saveAll(bookingEntities);
        return allSeatEntityById;
    }

    public CartEntity getCartBySession(String session) {
        return cartService.getCartBySession(session);
    }

    public List<SeatEntity> getSeatEntitiesFromCart(int cartId) {
        return seatService.getSeatEntitiesFromCart(cartId);
    }
}
