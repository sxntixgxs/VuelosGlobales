SELECT T.id,C1.name AS CityDepature,C2.name AS CityArrival ,T.idCrew,T.date,S.name,T.idPlane 
FROM trip T 
INNER JOIN route R ON T.idRoute = R.id 
INNER JOIN status S ON T.idStatus = S.id 
INNER JOIN city C1 ON R.idDepature = C1.id 
INNER JOIN city C2 ON R.idArrival = C2.id;
describe route;