package main

import (
	"fmt"
)

func PrintShapeStats(shape Shape) {
	fmt.Printf("Area: %d perimeter: %d", shape.area(), shape.perimeter())
}

func main() {
	var x Shape = Circle{
		diameter: 10,
	}

	PrintShapeStats(x)

}
