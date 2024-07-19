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