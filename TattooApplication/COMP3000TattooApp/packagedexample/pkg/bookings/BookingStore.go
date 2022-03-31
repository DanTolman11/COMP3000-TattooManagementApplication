package bookings

import "fmt"

type BookingStore struct {
	bookings []*Booking
	nextId   int
}

func (bookingStore *BookingStore) SaveBooking(booking *Booking) error {
	if !ValidateBooking(booking) {
		return fmt.Errorf("Invalid booking")
	}
	booking.Id = bookingStore.nextId
	bookingStore.nextId += 1
	bookingStore.bookings = append(bookingStore.bookings, booking)
	return nil
}

func (bookingStore *BookingStore) GetBookingById(id int) (*Booking, error) {
	for _, booking := range bookingStore.bookings {
		if booking.Id == id {
			return booking, nil
		}
	}
	return nil, fmt.Errorf("No booking for ID")
}

func (bookingStore *BookingStore) DeleteBookingById(id int) error {
	bookingIndex := -1
	for index, booking := range bookingStore.bookings {
		if booking.Id == id {
			bookingIndex = index
		}
	}
	if bookingIndex == -1 {
		return fmt.Errorf("No booking for ID")
	}
	// if bookings contains [1,2,3,4,5]
	// then bookings[:2] = [1,2]
	// then bookings[2+1:] = bookings[3:] = [4,5]
	// then both together would equal [1,2,4,5] thus having removes the second element
	bookingStore.bookings = append(bookingStore.bookings[:bookingIndex], bookingStore.bookings[bookingIndex+1:]...)
	return nil
}

func (bookingStore *BookingStore) UpdateBooking(newBooking *Booking) error {
	if !ValidateBooking(newBooking) {
		return fmt.Errorf("Invalid booking")
	}
	for index, booking := range bookingStore.bookings {
		if booking.Id == newBooking.Id {
			bookingStore.bookings[index] = newBooking
		}
	}
	return nil
}
