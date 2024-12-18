package com.example.demo.service;

import com.example.demo.dto.ReservationResponseDto;
import com.example.demo.dto.ReservationResponseStepDto;
import com.example.demo.entity.*;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.demo.entity.ReservationStep.*;


@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final RentalLogService rentalLogService;

    public ReservationService(ReservationRepository reservationRepository,
                              ItemRepository itemRepository,
                              UserRepository userRepository,
                              RentalLogService rentalLogService) {
        this.reservationRepository = reservationRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.rentalLogService = rentalLogService;
    }

    // TODO: 1. 트랜잭션 이해
    @Transactional
    public void createReservation(Long itemId, Long userId, LocalDateTime startAt, LocalDateTime endAt) {
        // 쉽게 데이터를 생성하려면 아래 유효성검사 주석 처리
//        List<Reservation> haveReservations = reservationRepository.findConflictingReservations(itemId, startAt, endAt);
//        if(!haveReservations.isEmpty()) {
//            throw new ReservationConflictException("해당 물건은 이미 그 시간에 예약이 있습니다.");
//        }

        Item item = itemRepository.findById(itemId).orElseThrow(() -> new IllegalArgumentException("해당 ID에 맞는 값이 존재하지 않습니다."));
        User user = userRepository.findByIdOrElseThrow(userId);
        Reservation reservation = new Reservation(item, user, ReservationStep.PENDING, startAt, endAt);
        Reservation savedReservation = reservationRepository.save(reservation);

        RentalLog rentalLog = new RentalLog(savedReservation, "로그 메세지", "CREATE");
        rentalLogService.save(rentalLog);
    }

    // TODO: 3. N+1 문제
    public List<ReservationResponseDto> getReservations() {
        return reservationRepository.findAllCustom();
    }

    // TODO: 5. QueryDSL 검색 개선
    public List<ReservationResponseDto> findByUserIdOrItemId(Long userId, Long itemId) {

        return reservationRepository.findByUserIdOrItemId(userId, itemId);
    }

    // TODO: 7. 리팩토링
    @Transactional
    public ReservationResponseStepDto updateReservationStatus(Long reservationId, String status) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new IllegalArgumentException("해당 ID에 맞는 데이터가 존재하지 않습니다."));

        ReservationStep reservationStep = toReservationStep(status);
        reservation.updateStep(reservationStep);

        return new ReservationResponseStepDto(
                reservation.getId(),
                reservation.getUser().getNickname(),
                reservation.getItem().getName(),
                reservation.getStartAt(),
                reservation.getEndAt(),
                reservation.getReservationStep());
    }
}
