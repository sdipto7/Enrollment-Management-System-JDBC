CREATE TABLE course(
course_code varchar(7),
course_title varchar(100)
);

CREATE TABLE trainee(
trainee_id int,
trainee_name varchar(50)
);

CREATE TABLE enrollment(
trainee_id int,
course_code varchar(7)
);
