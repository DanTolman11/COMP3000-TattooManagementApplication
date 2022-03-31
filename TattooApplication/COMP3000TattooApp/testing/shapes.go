package main

import "math"

type Shape interface {
	area() int
	perimeter() int
	xyz() int
}

type Square struct {
	length, width int
}

func (s Square) area() int {
	return s.length * s.width
}

func (s Square) perimeter() int {
	return (s.width * 2) + (s.length * 2)
}

type Circle struct {
	diameter int
}

func (c Circle) area() int {
	//pi * r squared
	part := int(math.Pi * float64((c.diameter)/2))
	return part * part
}

func (c Circle) xyz() int {
	//pi * r squared
	part := int(math.Pi * float64((c.diameter)/2))
	return part * part
}

func (c Circle) perimeter() int {
	return 0
}
