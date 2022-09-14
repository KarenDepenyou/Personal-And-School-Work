/*Answer to Question 1*/
USE iSchool; -- Make sure you are in the right database
SELECT concat(lname,", ", fname) AS "person_name", person_id,department,title, pronoun AS preffered_pronoun, race
	FROM people JOIN pronouns 
    On people.pronoun_id = pronouns.pronoun_id
	JOIN person_race USING(person_id)
    JOIN race USING(race_id)
	WHERE title IS NOT NULL

UNION
SELECT concat(lname,", ", fname) AS "person_name", person_id,department,"Student" title, pronoun AS preffered_pronoun,race
	FROM people JOIN pronouns 
    On people.pronoun_id = pronouns.pronoun_id
	JOIN person_race USING(person_id)
    JOIN race USING(race_id)
WHERE title IS NULL;

/*Answer to Question 2*/
SELECT concat(course_code,course_number) AS "courses", 
concat(semester," ",year,"-"," ",section_number) AS "section",
concat(fname," ", lname) AS "student_name",course_description
FROM courses JOIN course_sections USING(course_id)
JOIN enrollments USING(section_id)
JOIN people USING(person_id)
ORDER BY courses DESC,year,section_number
;



