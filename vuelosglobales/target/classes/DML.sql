USE airport;
-- Insertar valores en la tabla userRoles
INSERT INTO userRoles (rol) VALUES ('admin'),
 ('crew member'),
  ('pilot'),
   ('sales'),
    ('customer'),
     ('maintenance technician');

-- Insertar valores en la tabla user
INSERT INTO user (id, name, surname, email, password, idRol) VALUES
('1005153789', 'Santiago', 'Sandoval', 'sandoval@santiago.tt', '123', 1),
('Y1234Z', 'Emmanuel', 'Gazmey', 'emma@gazm.pr', '12345678', 2),
('A321X', 'Hector', 'Delgado', 'delgado@father.pr', '12345678', 2),
('Z123Y', 'Lewis', 'Days', 'lewis@days.co', '12345678', 3),
('A123X', 'Rafael', 'Dudamel', 'dudamel@rafa.bga', '12345678', 3),
('Y432Z', 'Ricardo', 'Arroyo', 'arroyo@ricardo.co', '12345678', 3),
('Z321Y', 'Black', 'Man', 'black@man.uk', '12345678', 4),
('X123Y', 'Kevin', 'Roldan', 'mr@roldan.kr', '12345678', 6),
('X321Y', 'Benito', 'Martinez', 'benito@bad.pr', '12345678', 2);

-- Insertar valores en la tabla status
INSERT INTO status (name) VALUES ('Available'), ('In Maintenance'), ('Out of Service'), ('Reserved');

-- Insertar valores en la tabla airline
INSERT INTO airline (name) VALUES ('Avianca'), ('Emirates'), ('LATAM'), ('American Airlines'), ('Delta Air Lines');

-- Insertar valores en la tabla manufacturer
INSERT INTO manufacturer (name) VALUES ('Boeing'), ('Airbus');

-- Insertar valores en la tabla model
INSERT INTO model (name, idManufacturer) VALUES 
('737-800', 1), 
('787-8', 1), 
('777-200', 1), 
('747-8', 1), 
('A320', 2), 
('A330-300', 2), 
('A350-900', 2);

-- Insertar valores en la tabla country
INSERT INTO country (name) VALUES ('Colombia'), ('USA'), ('Canada');

-- Insertar valores en la tabla city
INSERT INTO city (name, idCountry) VALUES 
('Bogotá', 1), 
('New York', 2), 
('Toronto', 3), 
('Los Angeles', 2);

-- Insertar valores en la tabla route
INSERT INTO route (idDepature, idArrival) VALUES 
(1, 2), -- Bogotá to New York
(1, 3), -- Bogotá to Toronto
(1, 4), -- Bogotá to Los Angeles
(2, 1), -- New York to Bogotá
(2, 3), -- New York to Toronto
(2, 4), -- New York to Los Angeles
(3, 1), -- Toronto to Bogotá
(3, 2), -- Toronto to New York
(3, 4), -- Toronto to Los Angeles
(4, 1), -- Los Angeles to Bogotá
(4, 2), -- Los Angeles to New York
(4, 3); -- Los Angeles to Toronto

-- Insertar valores en la tabla airport
INSERT INTO airport (name, idCity) VALUES 
('El Dorado International Airport', 1), 
('John F. Kennedy International Airport', 2), 
('Toronto Pearson International Airport', 3), 
('Los Angeles International Airport', 4);

-- Insertar valores en la tabla tripStatus
INSERT INTO tripStatus (status) VALUES ('Confirmed'), ('Pending Crew');

-- Insertar valores en la tabla plane
INSERT INTO plane (id, capacity, fabrication, idStatus, idAirline, idModel) VALUES 
('PL001', 180, '2015-05-20', 1, 1, 1), -- Boeing 737-800, Avianca, Available
('PL002', 242, '2016-06-15', 1, 1, 2), -- Boeing 787-8, Avianca, Available
('PL003', 396, '2017-07-25', 2, 2, 3), -- Boeing 777-200, Emirates, In Maintenance
('PL004', 467, '2018-08-30', 1, 2, 4), -- Boeing 747-8, Emirates, Available
('PL005', 150, '2019-09-10', 1, 3, 5), -- Airbus A320, LATAM, Available
('PL006', 335, '2020-10-12', 1, 3, 6), -- Airbus A330-300, LATAM, Available
('PL007', 325, '2021-11-05', 1, 4, 7); -- Airbus A350-900, American Airlines, Available

-- Insertar valores en la tabla trip
INSERT INTO trip (idRoute, idCrew, date, idStatus, idPlane) VALUES 
(1, NULL, '2024-07-20', 2, 'PL001'), -- Bogotá to New York, Pending Crew, PL001
(2, NULL, '2024-07-21', 2, 'PL002'), -- New York to Toronto, Pending Crew, PL002
(3, NULL, '2024-07-22', 2, 'PL005'), -- Toronto to Los Angeles, Pending Crew, PL005
(4, NULL, '2024-07-23', 2, 'PL007'); -- Los Angeles to Bogotá, Pending Crew, PL007

-- Insertar empleados en diferentes aerolíneas y aeropuertos
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


-- Insertar valores en la tabla crew
INSERT INTO crew (idPilot, idCopilot, idCrewLeader, idCrewAssistant, idCrewAssistant2) VALUES
('Z123Y', 'Y432Z', 'Z321Y', 'A321X', 'X123Y'), -- Crew 1
('X321Y', 'Z321Y', 'Y432Z', 'A123X', 'Y1234Z'), -- Crew 2
('A123X', 'A321X', 'X123Y', 'Z123Y', 'Y432Z'); -- Crew 3
-- Tipos de documento
INSERT INTO typeDocument (typeDoc) VALUES ('Passport');
INSERT INTO typeDocument (typeDoc) VALUES ('Foreign ID');
INSERT INTO typeDocument (typeDoc) VALUES ('National ID');


-- TARIFAS DE VUELO
INSERT INTO flightFare(name,details,amount) VALUES
("Economy","No luggage",300),
("Plus","Hand luggage",800),
("Business","Hold and hand luggage",1200);

-- Insertar datos en flightScales
-- Insertar nuevas ciudades
INSERT INTO city (name, idCountry) VALUES
('Mexico City', 1),  
('San Francisco', 2), 
('Vancouver', 3);        

-- Insertar más viajes en la tabla trip
INSERT INTO trip (idRoute, idCrew, date, idStatus, idPlane) VALUES
(4, NULL, '2024-08-01', 2, 'PL003'),  -- Nuevo viaje (ID 4)
(5, NULL, '2024-08-02', 1, 'PL004');  -- Nuevo viaje (ID 5)

-- Insertar datos en flightScales para los nuevos viajes
-- Insertar datos en flightScales para los nuevos viajes
INSERT INTO flightScales (idScaleCity, idTrip, date) VALUES
-- Viaje 4: (Bogotá a San Francisco con escala en Mexico City)
(5, 4, '2024-08-01'),  -- Mexico City para el viaje 4
(1, 4, '2024-08-01'),  -- Bogotá para el viaje 4
(6, 4, '2024-08-01'),  -- San Francisco para el viaje 4

-- Viaje 5: (New York a Vancouver con escalas en Chicago y Toronto)
(7, 5, '2024-08-02'),  -- Vancouver para el viaje 5
(3, 5, '2024-08-02'),  -- Toronto para el viaje 5
(2, 5, '2024-08-02');  -- New York para el viaje 5


