package com.Project.Bus_Booking_System.Controller;

import java.time.LocalDate;
import java.util.List;

import com.Project.Bus_Booking_System.Entity.Bus;
import com.Project.Bus_Booking_System.Repository.BusRepository;
import com.Project.Bus_Booking_System.Repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("passenger")
public class BusController {

	@Autowired
    PassengerRepository passRepo;
	
	@Autowired
    BusRepository busRepo;

	@GetMapping({ "busschedules/{passengerId}" })
	public String busSchedules(@PathVariable("passengerId") int passengerId, Model model) {
		List<Bus> buses = busRepo.findAll();
		String name = passRepo.findById(passengerId).get().getPassengerName();
		model.addAttribute("buses", buses);
		model.addAttribute("id", passengerId);
		model.addAttribute("name", name);
		return "bus_schedules";
	}
	
	
	@GetMapping({ "busschedules" })
	public String busSchedulesSearch(@RequestParam("passengerId") int passengerId, @RequestParam("sDate") LocalDate sDate, @RequestParam("fromLoc") String fromLoc, @RequestParam("toLoc") String toLoc,  Model model) {
		List<Bus> buses = busRepo.findByFromLocAndToLocAndStartDate(fromLoc, toLoc, sDate);
		model.addAttribute("buses", buses);
		model.addAttribute("passengerId", passengerId);
		
		return "bus_schedules";
	}

}
