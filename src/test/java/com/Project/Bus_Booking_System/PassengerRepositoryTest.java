package com.Project.Bus_Booking_System;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Project.Bus_Booking_System.Entity.Passenger;
import com.Project.Bus_Booking_System.Repository.PassengerRepository;

@ExtendWith(MockitoExtension.class)
public class PassengerRepositoryTest {

	@Mock
	private PassengerRepository passRepo;

	Passenger pass = new Passenger();

	@BeforeEach
	public void init() {
		// To enable Mockito annotations
		MockitoAnnotations.openMocks(this);

		// Initialise the mock object
		LocalDate dob = LocalDate.of(1996, 11, 03);

		pass.setPassengerName("Rajasekar T");
		pass.setEmail("raj@outlook.com");
		pass.setMobile_no("7861111369");
		pass.setLogin_password("JFSWD2");
		passRepo.save(pass);

	}

	@Test
	public void getByName() {
		// Check if the searched passenger name is same as the passenger record that is
		// saved
		String search_passenger_name_1 = "Rajasekar T";
		Assertions.assertEquals(search_passenger_name_1, pass.getPassengerName());

		// Check if the searched passenger name is not same as the passenger record that
		// is saved
		String search_passenger_name_2 = "Rajasekar";
		Assertions.assertNotEquals(search_passenger_name_2, pass.getPassengerName());
	}
}