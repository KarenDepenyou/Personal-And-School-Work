/*Question 2*/
USE ischool;

DROP PROCEDURE IF EXISTS person_by_state;

DELIMITER //
CREATE PROCEDURE person_by_state (
		state_param VARCHAR(2) -- paramater that will take in the state and return people from that state
	)

BEGIN


SELECT DISTINCT concat(fname," ", lname) AS person, department, street, -- select statement with correct formating and disctint responeses
concat(city,", ",state) AS location, classification
/* These are the join statements that will connect the different tables*/
FROM people p JOIN person_classifications pc 
	ON p.person_id = pc.person_id
JOIN classification c ON c.classification_id = pc.classification_id 
JOIN person_addresses pa ON pa.person_id = p.person_id
JOIN addresses a ON a.address_id = pa.address_id

WHERE state = state_param -- Restrict results to people from the state parameter

ORDER BY person DESC, location, street ; -- oder by


END //

DELIMITER ;
-- test that the procedure is working
CALL person_by_state('VA');

