package org.shatskii.cinemademo.controller;

import org.shatskii.cinemademo.dto.seat.Seat;
import org.shatskii.cinemademo.dto.seat.Seats;
import org.shatskii.cinemademo.service.SeatService;
import org.shatskii.cinemademo.entity.SeatEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/seats")
public class SeatController {
    private SeatService seatService;

    @Autowired
    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }


    @GetMapping("/all")
    public List<Seats> getAllFreeSeat() {
        List<SeatEntity> allFreeSeat = seatService.getAllFreeSeat();
        return getSeats(allFreeSeat);
    }

    private List<Seats> getSeats(List<SeatEntity> allFreeSeat) {
        List<Seats> entityList = new ArrayList<>();
        Map<Integer, List<Seat>> map = new HashMap<>();
        for (SeatEntity seatEntity : allFreeSeat) {
            if (map.containsKey(seatEntity.getRow())) {
                map.get(seatEntity.getRow())
                        .add(new Seat(seatEntity.getSeatNumber(), seatEntity.getIsFree(), seatEntity.getPrice()));
            } else {
                map.put(seatEntity.getRow(),
                        new ArrayList<>(List.of(new Seat(seatEntity.getSeatNumber(), seatEntity.getIsFree(), seatEntity.getPrice()))));
            }
        }
        for (Map.Entry<Integer, List<Seat>> entry : map.entrySet()) {
            entityList.add(new Seats(entry.getKey(), entry.getValue()));
        }
        return entityList;
    }
}
