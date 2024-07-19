INSERT INTO userRoles(
    id,rol
)VALUES(
    1,'admin'
);

INSERT INTO user(
    id,name,surname,email,password,idRol
) VALUES(
    '1005153789','Santiago','Sandoval','sandoval@santiago.tt','123',1
);
-- RESTART 
INSERT INTO status (name) VALUES ('Available');
INSERT INTO status (name) VALUES ('In Maintenance');
INSERT INTO status (name) VALUES ('Out of Service');
INSERT INTO status (name) VALUES ('Reserved');


-- Insertar valores en la tabla airline
INSERT INTO airline (name) VALUES ('Avianca');
INSERT INTO airline (name) VALUES ('Emirates');
INSERT INTO airline (name) VALUES ('LATAM');
INSERT INTO airline (name) VALUES ('American Airlines');
INSERT INTO airline (name) VALUES ('Delta Air Lines');
-- Insertar valores en la tabla manufacturer
INSERT INTO manufacturer (name) VALUES ('Boeing');
INSERT INTO manufacturer (name) VALUES ('Airbus');


-- Insertar valores en la tabla model
-- Para Boeing (idManufacturer = 1)
INSERT INTO model (name, idManufacturer) VALUES ('737-800', 1);
INSERT INTO model (name, idManufacturer) VALUES ('787-8', 1);
INSERT INTO model (name, idManufacturer) VALUES ('777-200', 1);
INSERT INTO model (name, idManufacturer) VALUES ('747-8', 1);

-- Para Airbus (idManufacturer = 2)
INSERT INTO model (name, idManufacturer) VALUES ('A320', 2);
INSERT INTO model (name, idManufacturer) VALUES ('A330-300', 2);
INSERT INTO model (name, idManufacturer) VALUES ('A350-900', 2);

-- PAISES
INSERT INTO country (name) VALUES
('Colombia'),
('USA'),
('Canada');

-- CIUDADES
INSERT INTO city (name, idCountry) VALUES
('Bogotá', 1),
('New York', 2),
('Toronto', 3),
('Los Angeles', 2);
-- ROUTES
INSERT INTO route (idDepature, idArrival) VALUES
(1, 2),  -- Bogotá to New York
(1, 3),  -- Bogotá to Toronto
(1, 4),  -- Bogotá to Los Angeles
(2, 1),  -- New York to Bogotá
(2, 3),  -- New York to Toronto
(2, 4),  -- New York to Los Angeles
(3, 1),  -- Toronto to Bogotá
(3, 2),  -- Toronto to New York
(3, 4),  -- Toronto to Los Angeles
(4, 1),  -- Los Angeles to Bogotá
(4, 2),  -- Los Angeles to New York
(4, 3);  -- Los Angeles to Toronto
--AIRPORT
INSERT INTO airport (name, idCity) VALUES
('El Dorado International Airport', 1),
('John F. Kennedy International Airport', 2),
('Toronto Pearson International Airport', 3),
('Los Angeles International Airport', 4);
--TRIP STATUS
INSERT INTO tripStatus (status) VALUES
('Confirmed'),
('Pending Crew');

--LOS DEMAS ROLES
INSERT INTO userRoles(
    id,rol
)VALUES(
    2,'crew member',
    3,'pilot',
    4,'sales',
    5,'customer',
    6,'maintenance technician'
);

-- MAS USUARIOS
INSERT INTO user(
    id,name,surname,email,password,idRol
)VALUES(
    'Y1234Z','Emmanuel','Gazmey','emmma@gazm.pr','123',2
),(
    'Y432Z','Ricardo','Arroyo','arroyo@ricardo.co','123',3
),
(
    'Z123Y','Lewis','Days','lewis@days.co','123',3
),
(
    'Z321Y','Black','Man','black@man.uk','123',4
),
(
    'X123Y','Kevin','Roldan','mr@roldan.kr','123',6
),
(
    'X321Y','Benito','Martinez','benito@bad.pr','123',2
),
(
    'A321X','Hector','Delgado','delgado@father.pr','123',2
)
(
    'A123X','Rafael','Dudamel','dudamel@rafa.bga','123',2
);

-- Empleados en Avianca (idAirline = 1)
INSERT INTO employee (idUser, idAirport, idAirline, idCountry, admissionDate) VALUES
('Y1234Z', 1, 1, 1, '2024-01-10'),  -- Emmanuel Gazmey, Bogotá, Avianca
('A321X', 1, 1, 1, '2024-02-15');   -- Hector Delgado, Bogotá, Avianca

-- Empleados en Emirates (idAirline = 2)
INSERT INTO employee (idUser, idAirport, idAirline, idCountry, admissionDate) VALUES
('Z123Y', 2, 2, 2, '2024-03-20'),  -- Lewis Days, New York, Emirates
('A123X', 2, 2, 2, '2024-04-25');  -- Rafael Dudamel, New York, Emirates

-- Empleados en LATAM (idAirline = 3)
INSERT INTO employee (idUser, idAirport, idAirline, idCountry, admissionDate) VALUES
('Y432Z', 3, 3, 3, '2024-05-30'),  -- Ricardo Arroyo, Toronto, LATAM
('Z321Y', 3, 3, 3, '2024-06-15');  -- Black Man, Toronto, LATAM

-- Empleados en American Airlines (idAirline = 4)
INSERT INTO employee (idUser, idAirport, idAirline, idCountry, admissionDate) VALUES
('X123Y', 4, 4, 2, '2024-07-10');  -- Kevin Roldan, Los Angeles, American Airlines

-- Empleados en Delta Air Lines (idAirline = 5)
INSERT INTO employee (idUser, idAirport, idAirline, idCountry, admissionDate) VALUES
('X321Y', 4, 5, 2, '2024-08-05');  -- Benito Martinez, Los Angeles, Delta Air Lines


-- Insertar tres viajes pendientes de tripulación

-- Viaje 1: Bogotá to New York
INSERT INTO trip (idRoute, date, idStatus) VALUES
(1, '2024-07-20', 2); -- Estado: Pending Crew

-- Viaje 2: New York to Toronto
INSERT INTO trip (idRoute, date, idStatus) VALUES
(2, '2024-07-21', 2); -- Estado: Pending Crew

-- Viaje 3: Toronto to Los Angeles
INSERT INTO trip (idRoute, date, idStatus) VALUES
(3, '2024-07-22', 2); -- Estado: Pending Crew
