/*Answer to question 1*/
SELECT concat(p.fname," ",p.lname) AS student_name, -- format student name
SUM(c.credits) AS credits, COUNT(e.section_id) AS enrollments, 
TIME_FORMAT(MAX(cs.start_time),"%r") AS latest_start -- get the later start time among a student's classes
FROM people p JOIN enrollments e USING(person_id) -- grab student's enrollments
JOIN course_sections cs USING(section_id)
JOIN courses c USING(course_id)
GROUP BY student_name WITH rollup; -- get the total credit count and enrollments

/*Answer to question 2*/
SELECT concat(c.course_code,c.course_number) AS course, -- format courses
-- get setion count, set up for later enrollment count 
c.credits, count(cs.course_id) AS section_count-- , subqry.enrollment_counts 
FROM courses c -- grab courses and num of sections for each courses
JOIN course_sections cs USING(course_id) 
-- this join clause will allow us to get accurate count of enrollments and join that info to rest of table
/*JOIN(
	SELECT section_id, count(e.section_id) AS enrollment_counts -- select column and count 
    FROM enrollments e
) AS subqry ON subqry.section_id = cs.section_id -- we're joining on section_id. subqry is alias for table*/
 GROUP BY course_id;
 
 /*Bonus point:
	I chose to use a subquery because typically I would've had approached this problem using JOINs.
    From my understanding of subqueries, they are very similar to JOINs. In fact, quite frequently one 
    can be used in place of the other. Using a subquery for this problem seemed like a logical 
    transition from JOINs.
 */