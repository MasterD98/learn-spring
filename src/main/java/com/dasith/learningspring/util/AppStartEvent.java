package com.dasith.learningspring.util;

import com.dasith.learningspring.data.*;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AppStartEvent implements ApplicationListener<ApplicationReadyEvent> {
    private final ReservationRepository reservationRepository;
    private final DateUtils dateUtils;

    public AppStartEvent(ReservationRepository reservationRepository,DateUtils dateUtils){
        this.reservationRepository=reservationRepository;
        this.dateUtils=dateUtils;
    }


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
//        Date date =this.dateUtils.createDateFromDateString("2022-01-01");
//        Iterable<Reservation> reservations=this.reservationRepository.getReservationsByReservationDate(date);
//        reservations.forEach(System.out::println);
    }
}
