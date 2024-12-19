package com.example.demo.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class ReservationRequestDto {
    private Long itemId;
    private Long userId;
    private LocalDateTime startAt;
    private LocalDateTime endAt;

    public ReservationRequestDto (long itemId, long userId, String startAt, String endAt) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        this.itemId =itemId;
        this.userId = userId;
        this.startAt = LocalDateTime.parse(startAt, formatter);
        this.endAt = LocalDateTime.parse(endAt, formatter);
    }
}
