package com.example.demo.repository;

import com.example.demo.dto.ReservationResponseDto;

import java.util.List;

public interface ReservationQueryDslRepository {
    List<ReservationResponseDto> findByUserIdOrItemId(Long userId, Long itemId);
}
