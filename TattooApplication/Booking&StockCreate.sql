CREATE TABLE Booking
(
    BookingID INT NOT NULL,
    UserID INT NOT NULL,
    UserEmail VARCHAR(100) NOT NULL,
    DesignDetail VARCHAR(255) NOT NULL,
    BookingDate DATETIME NOT NULL,

    PRIMARY KEY (BookingID)
    
)

CREATE TABLE Stock
(
    StockID INT NOT NULL,
    StockName VARCHAR(70) NOT NULL,
    StockAmount INT NOT NULL,
    StockAlert BOOLEAN NOT NULL,

    PRIMARY KEY (StockId)
)


