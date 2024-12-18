package com.example.demo.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public abstract class ReservationResponseBaseDto {
    private final Long id;
    private final String nickname;
    private final String itemName;
    private final LocalDateTime startAt;
    private final LocalDateTime endAt;

    public ReservationResponseBaseDto(Long id, String nickname, String itemName, LocalDateTime startAt, LocalDateTime endAt) {
        this.id = id;
        this.nickname = nickname;
        this.itemName = itemName;
        this.startAt = startAt;
        this.endAt = endAt;
    }
}
