package org.shatskii.cinemademo.service;

import org.shatskii.cinemademo.entity.SeatEntity;
import org.shatskii.cinemademo.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {
    private SeatRepository seatRepository;

    @Autowired
    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public List<SeatEntity> getAllFreeSeat() {
        return seatRepository.findAllByIsFree("yes");
    }


    public List<SeatEntity> getSeatEntitiesFromCart(int cartId) {
        return seatRepository.getSeatEntitiesFromCart(cartId);
    }

    public boolean existsByIdInAndStatusIs(List<Integer> list, String status) {
        return seatRepository.existsByIdInAndIsFreeIs(list, status);
    }

    public List<SeatEntity> getAllSeatEntityById(List<Integer> list) {
        return seatRepository.findAllById(list);
    }
}
