package com.dasith.learningspring.business;

import com.dasith.learningspring.data.*;
import com.dasith.learningspring.data.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationService {
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;
    @Autowired
    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }
    public List<RoomReservation> getRoomReservationsForDate(Date date){
        Iterable<Room> rooms=this.roomRepository.findAll();
        Map<Long,RoomReservation> roomReservationMap=new HashMap<>();
        rooms.forEach(room -> {
            RoomReservation roomReservation=new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservationMap.put(room.getId(),roomReservation);
        });

        Iterable<Reservation> reservations=this.reservationRepository.getReservationsByReservationDate(date);
        reservations.forEach(reservation -> {
            RoomReservation roomReservation =roomReservationMap.get(reservation.getRoomId());
            roomReservation.setDate(date);
            Guest guest=guestRepository.getById(reservation.getGuestId());
            roomReservation.setFirstName(guest.getFirstName());
            roomReservation.setLastName(guest.getLastName());
            roomReservation.setGuestId(guest.getId());
        });
        List<RoomReservation> roomReservations = new ArrayList<>();
        for (Long id : roomReservationMap.keySet()) {
            roomReservations.add(roomReservationMap.get(id));
        }
        roomReservations.sort(new Comparator<RoomReservation>() {
            @Override
            public int compare(RoomReservation o1, RoomReservation o2) {
                if (o1.getRoomName().equals(o2.getRoomName())) {
                    return o1.getRoomNumber().compareTo(o2.getRoomNumber());
                }
                return o1.getRoomName().compareTo(o2.getRoomName());
            }
        });
        return roomReservations;
    }

    public List<RoomDTO> getAllRooms(){
        Iterable<Room> rooms=this.roomRepository.findAll();
        List<RoomDTO> roomDTOList =new ArrayList<>();
        rooms.forEach(room -> {
            RoomDTO newRoomDTO =new RoomDTO();
            newRoomDTO.setId(room.getId());
            newRoomDTO.setName(room.getName());
            newRoomDTO.setBedInfo(room.getBedInfo());
            newRoomDTO.setRoomNumber(room.getRoomNumber());
            roomDTOList.add(newRoomDTO);
        });
        return roomDTOList;
    }
    public List<GuestDTO> getAllGuests(){
        Iterable<Guest> guests=this.guestRepository.findAll();
        List<GuestDTO> guestDTOList =new ArrayList<>();
        guests.forEach(guest -> {
            GuestDTO newGuest =new GuestDTO();
            newGuest.setId(guest.getId());
            newGuest.setCountry(guest.getCountry());
            newGuest.setAddress(guest.getAddress());
            newGuest.setEmailAddress(guest.getEmailAddress());
            newGuest.setState(guest.getState());
            newGuest.setPhoneNumber(guest.getPhoneNumber());
            newGuest.setFirstName(guest.getFirstName());
            newGuest.setLastName(guest.getLastName());
            guestDTOList.add(newGuest);
        });
        return guestDTOList;
    }

    public Guest addGuest(GuestDTO guestDTO){
        Guest guest=new Guest();
        guest.setId(guestDTO.getId());
        guest.setAddress(guestDTO.getAddress());
        guest.setCountry(guestDTO.getCountry());
        guest.setState(guestDTO.getState());
        guest.setEmailAddress(guestDTO.getEmailAddress());
        guest.setFirstName(guestDTO.getFirstName());
        guest.setLastName(guestDTO.getLastName());
        guest.setPhoneNumber(guestDTO.getPhoneNumber());
        return this.guestRepository.save(guest);
    }
}
