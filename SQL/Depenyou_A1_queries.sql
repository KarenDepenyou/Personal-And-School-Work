/*Answer to Question 1*/
USE iSchool; -- Make sure you are in the right database
SELECT concat(street,", ",city) AS "Stree/City",state,zipcode,country,
	SUBSTRING(main_phone,9,4) AS "Last 4 Digits of Phone" -- get a substring of the last 4 digits of the number
FROM addresses
WHERE country > "J" -- don't want countries alphabetically below japan
ORDER BY state; -- data should be ordered by country

/*Answer to Question 2*/
SELECT concat(lname,", ", fname) AS "person_name",start_date,department, 
"Student" -- There is no student row in database so we're adding one
FROM people 
WHERE title IS NULL -- in the title column students are NULL and they're ones we're trying to pull
AND start_date >= '2020-03-12' AND start_date <= '2021-08-01' -- Restricting the start date from 03/12/2020 - 08/01/2021
ORDER BY start_date DESC, person_name; -- data should be ordered bt start date and person name.