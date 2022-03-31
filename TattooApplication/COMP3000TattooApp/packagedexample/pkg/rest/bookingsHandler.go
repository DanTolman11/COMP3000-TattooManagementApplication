package rest

import (
	"danexample/packagedexample/pkg/bookings"
	"encoding/json"
	"fmt"
	"github.com/gorilla/mux"
	"io/ioutil"
	"net/http"
	"strconv"
)

//notice how this is private
type BookingsController struct {
	//Persisten storage and anything else related specifically to this set of end points
	bookingStore *bookings.BookingStore
}

func AddBookingsAPI(router *mux.Router, store *bookings.BookingStore) {
	bookingsController := BookingsController{bookingStore: store}

	router.HandleFunc("/booking", bookingsController.createBookingHandler).Methods("POST")
	router.HandleFunc("/booking/{bookingid}", bookingsController.viewBookingHandler).Methods("GET")
	router.HandleFunc("/booking/{bookingid}", bookingsController.deleteBookingHandler).Methods("DELETE")
	router.HandleFunc("/booking/{bookingid}", bookingsController.updateBookingHandler).Methods("PUT")
}

func (bookingsController *BookingsController) createBookingHandler(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	w.WriteHeader(http.StatusOK)
	fmt.Fprintf(w, "Category: %v\n", vars["category"])
	booking, err := unmarshalBooking(w, r)
	if err != nil {
		//we already set our response within the unmarshalBooking function
		return
	}

	error := bookingsController.bookingStore.SaveBooking(&booking)

	if error != nil {
		http.Error(w, http.StatusText(http.StatusBadRequest), http.StatusBadRequest)
	}
}

func (bookingsController *BookingsController) viewBookingHandler(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	bookingId, err := strconv.Atoi(vars["bookingid"])

	if err != nil {
		http.Error(w, http.StatusText(http.StatusInternalServerError), http.StatusInternalServerError)
		return
	}

	booking, err := bookingsController.bookingStore.GetBookingById(bookingId)
	if err != nil {
		http.Error(w, http.StatusText(http.StatusBadRequest), http.StatusBadRequest)
	}
	json.NewEncoder(w).Encode(booking)
}

func (bookingsController *BookingsController) deleteBookingHandler(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	bookingId, err := strconv.Atoi(vars["bookingid"])

	if err != nil {
		http.Error(w, http.StatusText(http.StatusInternalServerError), http.StatusInternalServerError)
		return
	}

	err2 := bookingsController.bookingStore.DeleteBookingById(bookingId)
	if err2 != nil {
		http.Error(w, http.StatusText(http.StatusBadRequest), http.StatusBadRequest)
	}
}

func (bookingsController *BookingsController) updateBookingHandler(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	_, err := strconv.Atoi(vars["bookingid"])

	if err != nil {
		http.Error(w, http.StatusText(http.StatusInternalServerError), http.StatusInternalServerError)
		return
	}

	fmt.Fprintf(w, "Category: %v\n", vars["category"])
	booking, err := unmarshalBooking(w, r)
	if err != nil {
		//we already set our response within the unmarshalBooking function
		return
	}

	err2 := bookingsController.bookingStore.UpdateBooking(&booking)

	if err2 != nil {
		http.Error(w, http.StatusText(http.StatusBadRequest), http.StatusBadRequest)
	}
}

func unmarshalBooking(w http.ResponseWriter, r *http.Request) (bookings.Booking, error) {
	var newBooking = bookings.Booking{}

	reqBody, err := ioutil.ReadAll(r.Body)

	if err != nil {
		http.Error(w, http.StatusText(http.StatusInternalServerError), http.StatusInternalServerError)
		return newBooking, err
	}

	err = json.Unmarshal(reqBody, &newBooking)

	if err != nil {
		http.Error(w, http.StatusText(http.StatusInternalServerError), http.StatusInternalServerError)
		return newBooking, err
	}

	return newBooking, nil
}
