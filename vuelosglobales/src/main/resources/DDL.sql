 DROP SCHEMA airport;

CREATE SCHEMA airport;
USE airport;
CREATE TABLE documentTypes(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL
);
CREATE TABLE customers(
    id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    age INT NOT NULL,
    idDocType INT NOT NULL,
    FOREIGN KEY (idDocType) REFERENCES documentTypes(id)
);
CREATE TABLE flightFares(
    id INT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(20) NOT NULL,
    details TEXT NOT NULL,
    value DOUBLE(7,3) NOT NULL
);
CREATE TABLE trips(
    id INT PRIMARY KEY AUTO_INCREMENT,
    date DATE NOT NULL,
    price DOUBLE
);
CREATE TABLE tripBooking(
    id INT PRIMARY KEY AUTO_INCREMENT,
    date DATE NOT NULL,
    idTrips INT NOT NULL,
    FOREIGN KEY (idTrips) REFERENCES trips(id)
);
CREATE TABLE tripBookingDetails(
    id INT PRIMARY KEY AUTO_INCREMENT,
    idTripBooking INT NOT NULL,
    idCustomers VARCHAR(20) NOT NULL,
    idFlightFares INT NOT NULL,
    FOREIGN KEY (idTripBooking) REFERENCES tripBooking(id),
    FOREIGN KEY (idCustomers) REFERENCES customers(id),
    FOREIGN KEY (idFlightFares) REFERENCES flightFares(id)
);


CREATE TABLE airlines(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL
);

CREATE TABLE tripulationRoles(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL
);
CREATE TABLE countries(
    id VARCHAR(5) PRIMARY KEY,
    name VARCHAR(30) NOT NULL
);
CREATE TABLE cities(
    id VARCHAR(5) PRIMARY KEY,
    name VARCHAR(40) NOT NULL,
    idCountry VARCHAR(5) NOT NULL,
    FOREIGN KEY (idCountry) REFERENCES countries(id)
);
CREATE TABLE airports(
    id VARCHAR(5) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    idCity VARCHAR(5) NOT NULL,
    FOREIGN KEY (idCity) REFERENCES cities(id)
);
CREATE TABLE employees(
    id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(40) NOT NULL,
    idRol INT NOT NULL,
    ingressDate DATE NOT NULL,
    idAirline INT NOT NULL,
    idAirport VARCHAR(5) NOT NULL,
    FOREIGN KEY (idRol) REFERENCES tripulationRoles(id),
    FOREIGN KEY (idAirline) REFERENCES airlines(id),
    FOREIGN KEY (idAirport) REFERENCES airports(id)
);
CREATE TABLE revision_details(
    id VARCHAR(20) PRIMARY KEY,
    description TEXT NOT NULL,
    idEmployee VARCHAR(20) NOT NULL,
    FOREIGN KEY (idEmployee) REFERENCES employees(id)
);

CREATE TABLE manufacturers(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL
);

CREATE TABLE models(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    manufacturerID INT NOT NULL,
    FOREIGN KEY (manufacturerID) REFERENCES manufacturers(id)
);

CREATE TABLE status(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL
);
CREATE TABLE planes(
    id INT PRIMARY KEY AUTO_INCREMENT,
    plates VARCHAR(30) NOT NULL,
    capacity INT NOT NULL,
    fabricationDate DATE NOT NULL,
    idStatus INT NOT NULL,
    idModel INT NOT NULL,
    FOREIGN KEY(idStatus)REFERENCES status(id),
    FOREIGN KEY (idModel)REFERENCES models(id)
);
CREATE TABLE revisions(
    id INT PRIMARY KEY AUTO_INCREMENT,
    revisionDate DATE NOT NULL,
    idPlane INT NOT NULL,
    FOREIGN KEY(idPlane)REFERENCES planes(id)
);
CREATE TABLE revEmployee(
    idEmployee VARCHAR(20) NOT NULL,
    idRevision INT NOT NULL,
    PRIMARY KEY(idEmployee,idRevision),
    FOREIGN KEY (idEmployee) REFERENCES employees(id),
    FOREIGN KEY (idRevision) REFERENCES revisions(id)
);
CREATE TABLE gates(
    id INT PRIMARY KEY AUTO_INCREMENT,
    number VARCHAR(10) NOT NULL,
    idAirport VARCHAR(5) NOT NULL,
    FOREIGN KEY (idAirport) REFERENCES airports(id)
);

CREATE TABLE flightConnections(
    id INT PRIMARY KEY AUTO_INCREMENT,
    numConnection VARCHAR(20) NOT NULL,
    idTrip INT NOT NULL,
    idPlan INT NOT NULL,
    idAirport VARCHAR(5) NOT NULL,
    FOREIGN KEY (idTrip) REFERENCES trips(id),
    FOREIGN KEY (idPlan) REFERENCES planes(id),
    FOREIGN KEY (idAirport) REFERENCES airports(id)
);
CREATE TABLE tripCrews(
    idEmployee VARCHAR(20) NOT NULL,
    idConnection INT NOT NULL,
    PRIMARY KEY(idEmployee,idConnection),
    FOREIGN KEY (idEmployee) REFERENCES employees(id),
    FOREIGN KEY (idConnection) REFERENCES flightConnections(id)
);

-- CREATE ROLE 'Administrator';
-- GRANT ALL PRIVILEGES ON *.* TO 'Administrator';
-- GRANT 'Administrator' TO 'root'@'localhost';
-- FLUSH PRIVILEGES;
-- SELECT * FROM mysql.user;
