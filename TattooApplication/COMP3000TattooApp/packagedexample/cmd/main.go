package main

import (
	"danexample/packagedexample/pkg/bookings"
	"danexample/packagedexample/pkg/rest"
	"fmt"
	"github.com/gorilla/mux"
	"net/http"
)

func main() {
	store := &bookings.BookingStore{}
	r := mux.NewRouter()
	rest.AddBookingsAPI(r, store)
	error := http.ListenAndServe(":8080", r)

	if error != nil {
		fmt.Println(error)
	}
}
