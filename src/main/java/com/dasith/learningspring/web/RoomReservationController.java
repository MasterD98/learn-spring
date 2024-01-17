package com.dasith.learningspring.web;

import com.dasith.learningspring.business.ReservationService;
import com.dasith.learningspring.business.RoomReservation;
import com.dasith.learningspring.util.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/reservation")
public class RoomReservationController {
    private final DateUtils dateUtils;
    private final ReservationService reservationService;

    public RoomReservationController(DateUtils dateUtils, ReservationService reservationService) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
    }
    @RequestMapping(method = RequestMethod.GET)
    public  String getReservation(@RequestParam(name = "date",required = false)String dateString, Model model){
        Date date = this.dateUtils.createDateFromDateString(dateString);
        List<RoomReservation> roomReservationsList=this.reservationService.getRoomReservationsForDate(date);
        model.addAttribute("roomReservations",roomReservationsList);
        return "roomres";
    }
}
