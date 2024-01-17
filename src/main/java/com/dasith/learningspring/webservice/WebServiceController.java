package com.dasith.learningspring.webservice;

import com.dasith.learningspring.business.*;
import com.dasith.learningspring.data.Guest;
import com.dasith.learningspring.util.DateUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WebServiceController {
    private final DateUtils dateUtils;
    private final ReservationService reservationService;

    public WebServiceController(DateUtils dateUtils, ReservationService reservationService) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
    }

    @RequestMapping(path = "/reservations" ,method = RequestMethod.GET)
    public List<RoomReservation> getReservations(@RequestParam(name = "date",required = false) String dateString){
        Date date=this.dateUtils.createDateFromDateString(dateString);
        return this.reservationService.getRoomReservationsForDate(date);
    }

    @RequestMapping(path = "/guests" ,method = RequestMethod.GET)
    public List<GuestDTO> getGuests(){
        return this.reservationService.getAllGuests();
    }
    @RequestMapping(path = "/guests" ,method = RequestMethod.POST)
    public GuestDTO addGuests(@RequestBody()GuestDTO guest){
        Guest  guestRes = this.reservationService.addGuest(guest);
        GuestDTO guestResDTO=new GuestDTO();
        guestResDTO.setId(guestRes.getId());
        guestResDTO.setFirstName(guestRes.getFirstName());
        guestResDTO.setLastName(guestRes.getLastName());
        guestResDTO.setPhoneNumber(guestRes.getPhoneNumber());
        guestResDTO.setEmailAddress(guestRes.getEmailAddress());
        guestResDTO.setState(guestRes.getState());
        guestResDTO.setCountry(guestRes.getCountry());
        guestResDTO.setAddress(guestRes.getAddress());
        return guestResDTO;

    }

    @RequestMapping(path = "/rooms" ,method = RequestMethod.GET)
    public List<RoomDTO> getRooms(){
        return this.reservationService.getAllRooms();
    }
}
