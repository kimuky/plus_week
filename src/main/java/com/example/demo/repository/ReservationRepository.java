package com.example.demo.repository;

import com.example.demo.dto.ReservationResponseDto;
import com.example.demo.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>, ReservationQueryDslRepository {

    @Query("SELECT new com.example.demo.dto.ReservationResponseDto(r.id, u.nickname, i.name, r.startAt, r.endAt) " +
            "from Reservation r " +
            "join  r.item i " +
            "join  r.user u")
    List<ReservationResponseDto> findAllCustom();
}
