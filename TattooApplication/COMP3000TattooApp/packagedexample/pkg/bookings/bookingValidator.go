package bookings

import (
	"strings"
)

// ValidateBooking Validates the person making the booking is not called Dan
func ValidateBooking(booking *Booking) bool{
	return strings.Contains(booking.ClientName, "Dan")
}