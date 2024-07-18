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
