SELECT T.id,C1.name AS CityDepature,C2.name AS CityArrival ,T.idCrew,T.date,S.name,T.idPlane 
FROM trip T 
INNER JOIN route R ON T.idRoute = R.id 
INNER JOIN status S ON T.idStatus = S.id 
INNER JOIN city C1 ON R.idDepature = C1.id 
INNER JOIN city C2 ON R.idArrival = C2.id;
describe route;


SELECT R.id,R.idCustomer,T.date,FF.name
FROM flightReservation R
INNER JOIN trip T ON R.idTrip = T.id
INNER JOIN flightFare FF ON R.idFlightFare = FF.id
WHERE idTrip = 1;

SELECT P.id,P.capacity,P.fabrication,S.name,A.name,M.name
FROM plane P
INNER JOIN status S ON P.idStatus = S.id
INNER JOIN airline A ON P.idAirline = A.id
INNER JOIN model M ON P.idModel = M.id
;