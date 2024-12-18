package com.example.demo.repository;

import com.example.demo.dto.ReservationResponseDto;
import com.example.demo.entity.QReservation;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.demo.entity.QReservation.reservation;

@Repository
public class ReservationRepositoryImpl implements ReservationQueryDslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public ReservationRepositoryImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<ReservationResponseDto> findByUserIdOrItemId(Long userId, Long itemId) {

        return jpaQueryFactory.select(
                Projections.constructor(ReservationResponseDto.class,
                        reservation.id,
                        reservation.user.nickname,
                        reservation.item.name,
                        reservation.startAt,
                        reservation.endAt))
                .from(reservation).where(userIdEq(userId), itemIdEq(itemId)).orderBy(reservation.startAt.desc()).fetch();
    }

    private BooleanExpression userIdEq(Long userId) {
        if (userId == null) {
            return null;
        }
        return reservation.user.id.eq(userId);
    }

    private BooleanExpression itemIdEq(Long itemId) {
        if (itemId == null) {
            return null;
        }
        return reservation.item.id.eq(itemId);
    }
}
