package com.Project.Bus_Booking_System.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "booking")
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_id")
	private int bookingId;

	@Column(name = "passenger_id")
	private int passengerId;

	@Column(name = "passenger_name")
	private String passengerName;

	@Column(name = "bus_id")
	private int busId;

	@Column(name = "bus_name")
	private String busName;

	@Column(name = "seat_qty")
	private int seatQty;
	
	@Column(name = "booked_price")
	private int price;
	
	private int amount;

	@Column(name = "booked_time")
	private LocalDateTime bookedTime;
}
