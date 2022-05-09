CREATE TABLE IF NOT EXISTS user(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username varchar(255) NOT NULL,
    password varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS stock(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name varchar(255) NOT NULL,
    category varchar(255) NOT NULL,
    amount int NOT NULL DEFAULT 0
);


CREATE TABLE IF NOT EXISTS booking(
          id INTEGER PRIMARY KEY AUTOINCREMENT,
          name varchar (255) NOT NULL,
          age int NOT NULL DEFAULT 0,
          email varchar (255) NOT NULL,
          phoneNumber varchar(11) NOT NULL DEFAULT 0,
          brief varchar (255) NOT NULL,
          date datetime NOT NULL
)

