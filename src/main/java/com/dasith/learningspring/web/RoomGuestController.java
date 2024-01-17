package com.dasith.learningspring.web;

import com.dasith.learningspring.business.GuestService;
import com.dasith.learningspring.business.RoomGuest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/guests")
public class RoomGuestController {
    private GuestService guestService;

    public RoomGuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAllGuests(Model model){
        List<RoomGuest> roomGuestList=this.guestService.getAllGuests();
        model.addAttribute("roomGuestList",roomGuestList);
        return "resguests";
    }
}
