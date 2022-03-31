# Project
> ***NOTE*** This tutorial uses git bash, some commands may not work wihtout it.

#Setup
## Creating a module
Go modules are modules of code same as a project in Java or Python. To create a new module first create a directory where
you'd like the project based IE: ~/example and then initilise the module with `go mod init "<MODULE-NAME>"` were 
`<MODULE-NAME>` replcased by the name of your desired module IE: `go mod init example`

## Your first file
Create a file called `main.go` and write a hello world program, once this has been created run it by typing `go run main.go`
into the command line from our directory.

## Adding a new dependecy
If your wanting to use external libaries it's important to add what you desire to the module, this is done by the `go get` 
command. It's basic syntax is `go get -u <MODULE-NAME>` in this example we'll need gorrilla mux this will be added by
`go get -u github.com/gorilla/mux`

## Basic example
The project files sent contain an example of 2 end points, one decodes json and creates a new booking the other retrieves a booking by it's ID

## packages
Packages will work differently to what your used to in go, a package in go is defined by a folder, however as it's functional anything
with a Capital letter (public) is exported to anything using that module, there is no including go files look at the packagedexample
folder which is the same as the example but has a better degree of seperattion

the pkg folder contains all application code, the cmd folder contains the main.go file which ties all the packages together

> ***NOTE*** The packages contain a go file for each handler as an example of how a package is all go files, this wouldn't be best practice as it would cause too
> many files to be used

> ***Extra feature*** If you run this application and the ClientName contains "Dan" then it will fail to make the booking

## Other things to note
Keep all business logic seperate from the end point, you should be able to write unit tests for all the business logic without touching the end point.
One example of this would be your bookings endpoint would decode the request. it would then go to a service which would validate the booking slot, ensure it's ok and return weather it is or not
the end point would then be in charge of taking this boolean value and telling the user if the booking was successful

Doing this will make your life alot easier with writing tests, and make it clearer what's going on. Think of it as:

The rest service is a UI, you wouldn't write validation and booking logic within a button click, you'd call a function 
or service that does it for you

## Use Postman
Postman will make your life so much easier, you can't test REST API's directly with chrome anyway, and it's the easiest 
way to test it

## Code formatting
Golang has built in standards for code formatting, goland will automatically do this for you but manual documentation can
be found [here](https://go.dev/blog/gofmt)