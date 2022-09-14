/*Answer to question 1*/
CREATE TABLE people_copy LIKE people; -- create a deep copy of people table
INSERT INTO people_copy SELECT * FROM people;
CREATE TABLE enrollments_copy LIKE enrollments; -- create a deep copy of enrollments table
INSERT INTO enrollments_copy SELECT * FROM enrollments;
 -- DROP TABLE people_copy;
 -- DROP TABLE enrollments_copy;
INSERT INTO people_copy (lname,fname,pronoun_id,email,college,department,title,start_date) -- insert a new student in people_copy
VALUES("Smith","Richard","2","rsmith@umd.edu","College of Information Studies","BSIS",NULL,sysdate());
INSERT INTO enrollments_copy (person_id,section_id) -- insert new enrollment for student
VALUES(132466095,55);
INSERT INTO enrollments_copy (person_id,section_id)
VALUES(132466095,46);
SELECT * FROM enrollments_copy;
SELECT * FROM people_copy;
-- SELECT * FROM course_sections

/*Answer to question 2*/
UPDATE enrollments_copy -- update information in enrollment 
SET section_id = 47
WHERE section_id = 46 AND person_id = 132466095; -- change section of student 132466095 from 46 to 47

SELECT concat(fname," ", lname) AS "student_name", -- the column headers for our select statement
concat(course_code,course_number) AS "course",
section_number, concat(semester," ",year) AS "semester_year"
FROM people_copy JOIN enrollments_copy  -- first we join student with their enrollments
	ON people_copy.person_id = enrollments_copy.person_id 
JOIN course_sections USING(section_id) -- join student enrollments to courses
JOIN courses USING(course_id)
WHERE lname = "Smith" AND fname = "Richard";


/*Answer to question 3*/
SELECT concat(fname," ", lname) AS "student_name", -- the column headers for our select statement
concat(course_code,course_number) AS "course",
section_number, section_id ,concat(fname," ", lname) AS "instructor_name"
FROM people_copy cp  JOIN enrollments_copy ec -- first we join students with their enrollment 
	ON cp.person_id = ec.person_id
JOIN course_sections USING(section_id) -- then we find where said students are enrolled
JOIN courses USING(course_id)
-- JOIN course_sections cs ON cs.instructor_id = cp.person_id -- then we find which teacher instructs that course
 WHERE course_number = "327" AND course_code = "INST"; -- limit our selection to INST327

DELETE  -- This section of code will delete student enrolled in INST327. 
FROM enrollments_copy
WHERE section_id = 54 OR section_id = 55; -- INST327 courses have a section id of 54 or 55

SELECT concat(fname," ", lname) AS "student_name", 
concat(course_code,course_number) AS "course",
section_number, section_id ,concat(fname," ", lname) AS "instructor_name"
FROM people_copy cp  JOIN enrollments_copy ec
	ON cp.person_id = ec.person_id
JOIN course_sections USING(section_id)
JOIN courses USING(course_id)
-- JOIN course_sections cs ON cs.instructor_id = cp.person_id
 WHERE course_number = "327" AND course_code = "INST";