package com.example.demo.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReservationResponseDto extends ReservationResponseBaseDto {

    public ReservationResponseDto(Long id, String nickname, String itemName, LocalDateTime startAt, LocalDateTime endAt) {
        super(id, nickname, itemName, startAt, endAt);
    }
}
