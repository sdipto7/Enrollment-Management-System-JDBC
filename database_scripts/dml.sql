INSERT INTO course VALUES
("CS201", "Object-Oriented Programming"),
("CS202", "Data Structures"),
("CS203", "Introduction to Java EE"),
("CS204", "Introduction to JDBC with MySql");

INSERT INTO trainee VALUES
("TR0001", "SHAHRIAR"),
("TR0002", "AYON"),
("TR0003", "FAHIM"),
("TR0004", "NAVEED"),
("TR0005", "SUMON");

INSERT INTO enrollment VALUES
("TR0001", "CS204"),
("TR0001", "CS203"),
("TR0001", "CS202"),
("TR0002", "CS204"),
("TR0002", "CS202"),
("TR0003", "CS203"),
("TR0003", "CS201"),
("TR0003", "CS202"),
("TR0004", "CS202"),
("TR0004", "CS203"),
("TR0004", "CS204"),
("TR0005", "CS204"),
("TR0005", "CS201");


SELECT * FROM course;

SELECT * FROM trainee;

SELECT trainee.trainee_id, trainee.trainee_name, course.course_code, course.course_title FROM ((enrollment INNER JOIN trainee ON enrollment.trainee_id=trainee.trainee_id) INNER JOIN course ON course.course_code=enrollment.course_code);

INSERT INTO enrollment VALUES (?, ?);
