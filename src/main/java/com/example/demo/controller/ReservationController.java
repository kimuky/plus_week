package com.example.demo.controller;

import com.example.demo.dto.ReservationRequestDto;
import com.example.demo.dto.ReservationResponseDto;
import com.example.demo.dto.ReservationResponseStepDto;
import com.example.demo.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public void createReservation(@RequestBody ReservationRequestDto reservationRequestDto) {
        reservationService.createReservation(reservationRequestDto.getItemId(),
                                            reservationRequestDto.getUserId(),
                                            reservationRequestDto.getStartAt(),
                                            reservationRequestDto.getEndAt());
    }

    @PatchMapping("/{id}/update-status")
    public ReservationResponseStepDto updateReservation(@PathVariable Long id, @RequestBody String status) {
        return reservationService.updateReservationStatus(id, status);
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponseDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.getReservations());
    }

    @GetMapping("/search")
    public List<ReservationResponseDto> searchAll(@RequestParam(required = false) Long userId,
                                                  @RequestParam(required = false) Long itemId) {
        return reservationService.findByUserIdOrItemId(userId, itemId);
    }
}
