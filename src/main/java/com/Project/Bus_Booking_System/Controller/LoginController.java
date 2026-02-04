package com.Project.Bus_Booking_System.Controller;

import com.Project.Bus_Booking_System.Entity.Passenger;
import com.Project.Bus_Booking_System.Repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@Autowired
    PassengerRepository passRepo;


	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@GetMapping({ "/" })
	public String loggedIn(@AuthenticationPrincipal User user, Model model) {

		String username = user.getUsername();
		String url = "";
		if (passRepo.findByEmail(username) != null) {
			Passenger p = passRepo.findByEmail(username);
			int passengerId = p.getPassengerId();
			url = "redirect:/passenger/welcome/" + passengerId;
		}

		return url;
	}

}