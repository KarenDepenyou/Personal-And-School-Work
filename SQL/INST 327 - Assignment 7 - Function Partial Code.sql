USE iSchool;

DROP FUNCTION IF EXISTS student_year;

DELIMITER //

CREATE FUNCTION student_year (
    start_date_param datetime
)
RETURNS VARCHAR(250)
DETERMINISTIC READS SQL DATA
BEGIN 
	DECLARE expected_class VARCHAR(250);
	
    IF EXTRACT(YEAR FROM start_date_param) = 2018 THEN 
		SET expected_class = "SENIOR";
	ELSEIF  EXTRACT(YEAR FROM start_date_param) = 2019 THEN
		SET expected_class = "JUNIOR";
	ELSEIF  EXTRACT(YEAR FROM start_date_param) = 2020 THEN
		SET expected_class = "SOPHMORE";
	ELSEIF  EXTRACT(YEAR FROM start_date_param) = 2021 THEN
		SET expected_class = "FRESHMAN";
	END IF;
    RETURN (expected_class);
END //
DELIMITER ;

SELECT CONCAT(fname, " ", lname) as "student_name",
start_date, student_year(start_date) AS "student_class"
FROM people
ORDER BY start_date;
