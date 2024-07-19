CREATE SCHEMA airport;
USE airport;

CREATE TABLE userRoles(
    id INT PRIMARY KEY AUTO_INCREMENT,
    rol VARCHAR(20) NOT NULL
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
    idDepature INT NOT NULL,
    idArrival INT NOT NULL,
    FOREIGN KEY (idDepature) REFERENCES city(id),
    FOREIGN KEY (idArrival) REFERENCES city(id)
);
CREATE TABLE airport(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    idCity INT NOT NULL,
    FOREIGN KEY (idCity) REFERENCES idCity(id)
);
    CREATE TABLE employee(
        idUser VARCHAR(20) NOT NULL,
        idAirport INT NOT NULL,
        idAirline INT NOT NULL,
        idCountry INT NOT NULL,
        admissionDate DATE NOT NULL,
        PRIMARY KEY(idUser,idAirline,idAirport),
        FOREIGN KEY (idUser) REFERENCES user(id),
        FOREIGN KEY(idAirport) REFERENCES airport(id),
        FOREIGN KEY (idAirline) REFERENCES airline(id),
        FOREIGN KEY (idCountry) REFERENCES country(id)
    );
CREATE TABLE tripStatus(
    id INT PRIMARY AUTO_INCREMENT,
    status VARCHAR(20) NOT NULL --confirmado y pte de tripulacion
);
CREATE TABLE crew(
    id INT PRIMARY KEY AUTO_INCREMENT,
    idPilot VARCHAR(20),
    idCopilot VARCHAR(20),
    idCrewLeader VARCHAR(20),
    idCrewAssistant VARCHAR(20),
    idCrewAssistant2 VARCHAR(20),
    FOREIGN KEY(idPilot) REFERENCES employee(id),
    FOREIGN KEY(idCopilot) REFERENCES employee(id),
    FOREIGN KEY(idCrewLeader) REFERENCES employee(id),
    FOREIGN KEY(idCrewAssistant) REFERENCES employee(id),
    FOREIGN KEY(idCrewAssistant2) REFERENCES employee(id)
)
CREATE TABLE trip(
    id INT PRIMARY KEY AUTO_INCREMENT,
    idRoute INT NOT NULL,
    idCrew INT,
    date DATE NOT NULL,
    idStatus INT NOT NULL,
    idPlane INT NOT NULL,
    FOREIGN KEY(idRoute) REFERENCES route(id),
    FOREIGN KEY (idCrew) REFERENCES crew(id),
    FOREIGN KEY(idStatus) REFERENCES status(id),
    FOREIGN KEY (idPlane) REFERENCES plnae(id)
);