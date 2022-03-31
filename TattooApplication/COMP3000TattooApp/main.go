package main

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"net/http"
	"strconv"
)
import "github.com/gorilla/mux"

type Booking struct {
	Id         int
	ClientName string
}

var (
	bookings []Booking
)

func main() {
	r := mux.NewRouter()
	r.HandleFunc("/booking", createBookingHandler).Methods("POST")
	r.HandleFunc("/booking/{bookingid}", viewBookingHandler).Methods("GET")
	error := http.ListenAndServe(":8080", r)

	if error != nil {
		fmt.Println(error)
	}
}

func createBookingHandler(w http.ResponseWriter, r *http.Request) {
	w.WriteHeader(http.StatusOK)
	booking, err := unmarshalBooking(w, r)
	if err != nil {
		//we already set our response within the unmarshalBooking function
		return
	}

	booking.Id = len(bookings)
	bookings = append(bookings, booking)
	json.NewEncoder(w).Encode(booking)
}

func viewBookingHandler(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	w.WriteHeader(http.StatusOK)
	bookingId, err := strconv.Atoi(vars["bookingid"])

	//if the left side of an || is true the right side will not be evaluated
	//as the if statement would be garenteed at that point to be true
	if err != nil || bookingId >= len(bookings) {
		http.Error(w, http.StatusText(http.StatusInternalServerError), http.StatusInternalServerError)
		return
	}

	json.NewEncoder(w).Encode(bookings[bookingId])
}

func unmarshalBooking(w http.ResponseWriter, r *http.Request) (Booking, error) {
	var newBooking = Booking{}

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
