package com.example.demo.dto;

import com.example.demo.entity.ReservationStep;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReservationResponseStepDto extends ReservationResponseBaseDto {

    private final ReservationStep reservationStep;

    public ReservationResponseStepDto(Long id, String nickname, String itemName, LocalDateTime startAt, LocalDateTime endAt, ReservationStep reservationStep) {
        super(id, nickname, itemName, startAt, endAt);
        this.reservationStep = reservationStep;
    }
}
