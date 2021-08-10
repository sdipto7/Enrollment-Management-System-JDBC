CREATE DATABASE course_enrollment_details;
USE course_enrollment_details;

CREATE TABLE course(
course_code varchar(7),
course_title varchar(100)
);

CREATE TABLE trainee(
trainee_id varchar(6),
trainee_name varchar(50)
);

CREATE TABLE enrollment(
trainee_id varchar(6),
course_code varchar(7)
);
