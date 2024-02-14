package org.shatskii.cinemademo.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.shatskii.cinemademo.dto.ticket.AddResponse;
import org.shatskii.cinemademo.dto.ticket.TicketWithoutPrice;
import org.shatskii.cinemademo.entity.SeatEntity;
import org.shatskii.cinemademo.dto.ticket.TicketWithPrice;
import org.shatskii.cinemademo.dto.ticket.CartResponse;
import org.shatskii.cinemademo.exception.NoSuchCartException;
import org.shatskii.cinemademo.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    private final BookingService bookingService;

    @Autowired
    public CartController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/add")
    public AddResponse addToCart(@RequestBody List<Integer> seats, HttpServletRequest request, HttpServletResponse response) {
        List<SeatEntity> allSeatEntityById;
        if (checkSession(request)) {
            allSeatEntityById = bookingService.getList(getSessionValue(request), seats);
        } else {
            String uuid = UUID.randomUUID().toString();
            response.addCookie(new Cookie("session", uuid));
            allSeatEntityById = bookingService.getList(uuid, seats);
        }
        return new AddResponse(allSeatEntityById.stream()
                .map(entity -> new TicketWithoutPrice(entity.getRow(), entity.getSeatNumber())).collect(Collectors.toList()));
    }


    @GetMapping
    public CartResponse getCart(HttpServletRequest request) {
        String sessionValue = getSessionValue(request);
        if (sessionValue == null) {
            throw new NoSuchCartException("Вы ничего не добавили в корзину!");
        }
        int cartId = bookingService.getCartBySession(sessionValue).getId();
        List<SeatEntity> seatEntities = bookingService.getSeatEntitiesFromCart(cartId);
        return new CartResponse(seatEntities.stream().
                map(entity -> new TicketWithPrice(entity.getRow(), entity.getSeatNumber(), entity.getPrice())).collect(Collectors.toList()));
    }

    private boolean checkSession(HttpServletRequest request) {
        boolean check = false;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("session")) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }

    private String getSessionValue(HttpServletRequest request) {
        String sessionValue = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("session")) {
                    sessionValue = cookie.getValue();
                    break;
                }
            }
        }
        return sessionValue;
    }

}
