package com.Project.Bus_Booking_System.Controller;

import java.time.LocalDateTime;

import com.Project.Bus_Booking_System.Entity.Passenger;
import com.Project.Bus_Booking_System.Repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Project.Bus_Booking_System.Entity.Booking;
import com.Project.Bus_Booking_System.Entity.Bus;
import com.Project.Bus_Booking_System.Repository.BookingRepository;
import com.Project.Bus_Booking_System.Repository.PassengerRepository;

@Controller
@RequestMapping("passenger")
public class BookingController {

	@Autowired
	PassengerRepository passRepo;

	@Autowired
	BusRepository busRepo;

	@Autowired
	BookingRepository bookRepo;

	@GetMapping({ "{passengerId}/bookingseat/{busId}" })
	public String busSchedules(@PathVariable("passengerId") int passengerId, @PathVariable("busId") int busId,
			Model model) {
		Passenger p = passRepo.findById(passengerId).get();
		String passengerName = p.getPassengerName();

		Bus bus = busRepo.findById(busId).get();
		String busName = bus.getBusName();
		int price = bus.getPrice();
		int seatsAvailable = bus.getAvailableSeats();
		Booking booking = new Booking();
		booking.setPassengerId(passengerId);
		booking.setPassengerName(passengerName);
		booking.setBusId(busId);
		booking.setBusName(busName);
		booking.setPrice(price);
		model.addAttribute("bookingForm", booking);
		model.addAttribute("passengerId", passengerId);
		model.addAttribute("passengerName", passengerName);
		model.addAttribute("seatsAvailable", seatsAvailable);
		return "booking";
	}

	@PostMapping({ "booking/save" })
	public String postRegister(@ModelAttribute Booking booking, Model model) {
		Bus bus = busRepo.findById(booking.getBusId()).get();

		booking.setBookedTime(LocalDateTime.now());

		int seats = booking.getSeatQty();
		int available_seats = bus.getAvailableSeats();

		if (seats <= available_seats) {
			bookRepo.save(booking);

			bus.setAvailableSeats(available_seats - seats);

			busRepo.save(bus);

			model.addAttribute("message", "Booking Confirmed");
		}

		else {

			model.addAttribute("message", "Booking Failed");
		}

		return "redirect:/passenger/welcome/" + booking.getPassengerId();
	}

	@GetMapping({ "booking/{passengerId}" })
	public String busSchedules(@PathVariable("passengerId") int passengerId, Model model) {

		int size = bookRepo.findByPassengerId(passengerId).size();

		if (size >= 1) {
			Booking busbooked = bookRepo.findByPassengerId(passengerId).get(size - 1);
			model.addAttribute("booked", busbooked);
			model.addAttribute("bookings", bookRepo.findByPassengerId(passengerId));

		} else {
			model.addAttribute("booked", null);

			model.addAttribute("bookings", null);
		}

		return "booking_history";
	}
}
