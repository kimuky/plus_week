package com.example.demo.entity;

public enum ReservationStep {
    PENDING, APPROVED, CANCELED, EXPIRED;

    public static ReservationStep toReservationStep(String status) {
        for ( ReservationStep reservationStep : ReservationStep.values()) {
            if (reservationStep.name().equals(status)) {
                return reservationStep;
            }
        }
        throw new IllegalArgumentException("올바르지 않은 상태");
    }

}
