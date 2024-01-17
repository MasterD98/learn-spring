package com.dasith.learningspring.business;

import com.dasith.learningspring.data.Guest;
import com.dasith.learningspring.data.GuestRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GuestService {
    private GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }
    public List<RoomGuest> getAllGuests(){
        Iterable<Guest> guests = guestRepository.findAll();
        List<RoomGuest> roomGuestList= new ArrayList<>();
        guests.forEach(guest -> {
            RoomGuest roomGuest=new RoomGuest();
            roomGuest.setFirstName(guest.getFirstName());
            roomGuest.setLastName(guest.getLastName());
            roomGuest.setEmailAddress(guest.getEmailAddress());
            roomGuest.setPhoneNumber(guest.getPhoneNumber());
            roomGuestList.add(roomGuest);
        });
        return roomGuestList;
    }
}
