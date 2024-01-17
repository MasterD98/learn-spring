package com.dasith.learningspring.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    public Iterable<Reservation> getReservationsByReservationDate(Date reservationDate);
}
