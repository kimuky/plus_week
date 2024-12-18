package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.example.demo.entity.ReservationStep.*;

@Entity
@Getter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    @Enumerated(EnumType.STRING)
    private ReservationStep reservationStep; // PENDING, APPROVED, CANCELED, EXPIRED

    public Reservation(Item item, User user, ReservationStep reservationStep, LocalDateTime startAt, LocalDateTime endAt) {
        this.item = item;
        this.user = user;
        this.reservationStep = reservationStep;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public Reservation() {}

    public void updateStep(ReservationStep reservationStep) {
        switch (reservationStep) {
            case APPROVED, EXPIRED:
                if (!this.getReservationStep().equals(PENDING)){
                    throw new IllegalArgumentException("올바르지 않은 상태");
                }
                break;
            case CANCELED:
                if (this.getReservationStep().equals(EXPIRED)){
                    throw new IllegalArgumentException("올바르지 않은 상태");
                }
                break;
        }
        this.reservationStep = reservationStep;
    }
}
