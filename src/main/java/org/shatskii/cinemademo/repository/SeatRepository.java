package org.shatskii.cinemademo.repository;

import org.shatskii.cinemademo.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<SeatEntity, Integer> {
     List<SeatEntity> findAllByIsFree(String isFree);

    @Query("""
            SELECT s
            from SeatEntity s
            inner join BookingEntity b on s.id = b.seatId
            inner join CartEntity c on b.cartId = :cartId
            """)
     List<SeatEntity> getSeatEntitiesFromCart(int cartId);

     boolean existsByIdInAndIsFreeIs(List<Integer> list, String isFree);

}
