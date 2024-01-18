package com.dasith.learningspring.async;

import com.dasith.learningspring.business.ReservationService;
import com.dasith.learningspring.data.Room;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RoomCleanerListener {
    private static final Logger LOGGER= LoggerFactory.getLogger(RoomCleanerListener.class);
    private final ObjectMapper objectMapper;
    private final ReservationService reservationService;

    public RoomCleanerListener(ObjectMapper objectMapper, ReservationService reservationService) {
        this.objectMapper = objectMapper;
        this.reservationService = reservationService;
    }

    public void receiveMessage(String message){
        try {
            AsyncPayload payload= objectMapper.readValue(message,AsyncPayload.class);
            if("ROOM".equals(payload.getModel())){
                Room room= reservationService.getRoomById(payload.getId());
                LOGGER.info("ROOM {}:{} needs to be cleaned",room.getRoomNumber(),room.getName());
            }else {
                LOGGER.warn("");
            }

        }catch (JsonProcessingException ex){
            ex.printStackTrace();
        }
    }
}
