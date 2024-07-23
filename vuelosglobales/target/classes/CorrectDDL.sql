DROP SCHEMA IF EXISTS airport;
CREATE SCHEMA airport;
USE airport;

CREATE TABLE userRoles(
    id INT PRIMARY KEY AUTO_INCREMENT,
    rol VARCHAR(60) NOT NULL
);

CREATE TABLE user(
    id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    surname VARCHAR(20) NOT NULL,
    email VARCHAR(20) NOT NULL,
    password VARCHAR(8) NOT NULL,
    idRol INT NOT NULL,
    FOREIGN KEY (idRol) REFERENCES userRoles(id)
);

CREATE TABLE status(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL
);

CREATE TABLE airline(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE manufacturer(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL
);

CREATE TABLE model(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL,
    idManufacturer INT NOT NULL,
    FOREIGN KEY (idManufacturer) REFERENCES manufacturer(id)
);

CREATE TABLE plane(
    id VARCHAR(30) PRIMARY KEY,
    capacity INT NOT NULL,
    fabrication DATE NOT NULL,
    idStatus INT NOT NULL,
    idAirline INT NOT NULL,
    idModel INT NOT NULL,
    FOREIGN KEY (idStatus) REFERENCES status(id),
    FOREIGN KEY (idAirline) REFERENCES airline(id),
    FOREIGN KEY (idModel) REFERENCES model(id)
);

CREATE TABLE country(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL
);

CREATE TABLE city(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    idCountry INT NOT NULL,
    FOREIGN KEY (idCountry) REFERENCES country(id)
);

CREATE TABLE route(
    id INT PRIMARY KEY AUTO_INCREMENT,
    idDepature INT NOT NULL,
    idArrival INT NOT NULL,
    FOREIGN KEY (idDepature) REFERENCES city(id),
    FOREIGN KEY (idArrival) REFERENCES city(id)
);

CREATE TABLE airport(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    idCity INT NOT NULL,
    FOREIGN KEY (idCity) REFERENCES city(id)
);

CREATE TABLE employee(
    idUser VARCHAR(20) NOT NULL,
    idAirport INT NOT NULL,
    idAirline INT NOT NULL,
    idCountry INT NOT NULL,
    admissionDate DATE NOT NULL,
    PRIMARY KEY(idUser, idAirline, idAirport),
    FOREIGN KEY (idUser) REFERENCES user(id),
    FOREIGN KEY (idAirport) REFERENCES airport(id),
    FOREIGN KEY (idAirline) REFERENCES airline(id),
    FOREIGN KEY (idCountry) REFERENCES country(id)
);

CREATE TABLE tripStatus(
    id INT PRIMARY KEY AUTO_INCREMENT,
    status VARCHAR(20) NOT NULL
);

CREATE TABLE crew(
    id INT PRIMARY KEY AUTO_INCREMENT,
    idPilot VARCHAR(20),
    idCopilot VARCHAR(20),
    idCrewLeader VARCHAR(20),
    idCrewAssistant VARCHAR(20),
    idCrewAssistant2 VARCHAR(20),
    FOREIGN KEY (idPilot) REFERENCES employee(idUser),
    FOREIGN KEY (idCopilot) REFERENCES employee(idUser),
    FOREIGN KEY (idCrewLeader) REFERENCES employee(idUser),
    FOREIGN KEY (idCrewAssistant) REFERENCES employee(idUser),
    FOREIGN KEY (idCrewAssistant2) REFERENCES employee(idUser)
);

CREATE TABLE trip(
    id INT PRIMARY KEY AUTO_INCREMENT,
    idRoute INT NOT NULL,
    idCrew INT,
    date DATE NOT NULL,
    idStatus INT NOT NULL,
    idPlane VARCHAR(30) NOT NULL,
    FOREIGN KEY (idRoute) REFERENCES route(id),
    FOREIGN KEY (idCrew) REFERENCES crew(id),
    FOREIGN KEY (idStatus) REFERENCES status(id),
    FOREIGN KEY (idPlane) REFERENCES plane(id)
);

-- Ahora, para más casos de usos hay escalas
-- entonces, trip apunta a otra tabla que se llame vuelos y seria una -- relación n:m porque un trip puede tener varios vuelos 
-- escalas- y los valores de otras columnas, serían cliente, tarifa!
CREATE TABLE typeDocument(
    id INT PRIMARY KEY AUTO_INCREMENT,
    typeDoc VARCHAR(20) NOT NULL
);
-- un cliente no necesariamente tiene que tener usuario, por lo cual, el id va a ser diferente
CREATE TABLE customer( 
    id VARCHAR(20) PRIMARY KEY,
    idTypeDoc INT NOT NULL,
    name VARCHAR(20) NOT NULL,
    surname VARCHAR(20) NOT NULL,
    age INT NOT NULL,
    FOREIGN KEY (idTypeDoc) REFERENCES typeDocument(id)
);
-- el TRIP / TRAYECTO al que pertenece
-- esta fecha debe ser mayor a trip
CREATE TABLE flightScales(
    id INT PRIMARY KEY AUTO_INCREMENT,
    idScaleCity INT NOT NULL,
    idTrip INT NOT NULL, 
    date DATE NOT NULL, 
    FOREIGN KEY (idScaleCity) REFERENCES city(id),
    FOREIGN KEY (idTrip) REFERENCES trip(id)
);

CREATE TABLE flightFare(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    details TEXT,
    amount INT NOT NULL
);
-- economic, plus, bussiness
CREATE TABLE flightReservation(
    id INT PRIMARY KEY AUTO_INCREMENT,
    idCustomer VARCHAR(20) NOT NULL,
    idTrip INT NOT NULL,
    idFlightFare INT NOT NULL,
    FOREIGN KEY (idCustomer) REFERENCES customer(id),
    FOREIGN KEY (idTrip) REFERENCES trip(id),
    FOREIGN KEY (idFlightFare) REFERENCES flightFare(id)
);
