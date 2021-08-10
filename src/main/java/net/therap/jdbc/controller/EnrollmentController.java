package net.therap.jdbc.controller;

import net.therap.jdbc.domain.Course;
import net.therap.jdbc.domain.Enrollment;
import net.therap.jdbc.domain.Trainee;
import net.therap.jdbc.service.EnrollmentService;
import net.therap.jdbc.service.EnrollmentViewService;
import net.therap.jdbc.util.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * @author rumi.dipto
 * @since 8/9/21
 */
public class EnrollmentController {

    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        Connection connection = ConnectionManager.getConnection();

        while (true) {
            System.out.println("Press 0 to exit");
            System.out.println("Press 1 to view courses");
            System.out.println("Press 2 to view trainees");
            System.out.println("Press 3 to view course enrollment details");
            System.out.println("Press 4 to assign a course to trainee");

            int operation = input.nextInt();
            if (operation == 0) {
                break;
            }
            executeOperation(connection, operation);
            System.out.println("\n============\n");
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void executeOperation(Connection connection, int operation) {
        String query = "";
        ResultSet resultSet = null;
        switch (operation) {
            case 1:
                query = "SELECT * FROM course";
                resultSet = EnrollmentService.queryExecute(connection, query);
                List<Course> courseList = EnrollmentService.courseDataExtract(resultSet);
                EnrollmentViewService.printCourseInformation(courseList);
                break;

            case 2:
                query = "SELECT * FROM trainee";
                resultSet = EnrollmentService.queryExecute(connection, query);
                List<Trainee> traineeList = EnrollmentService.traineeDataExtract(resultSet);
                EnrollmentViewService.printTraineeInformation(traineeList);
                break;

            case 3:
                query = "select trainee.trainee_id, trainee.trainee_name, course.course_code, course.course_title FROM " +
                        "((enrollment inner join trainee on enrollment.trainee_id=trainee.trainee_id) " +
                        "inner join course on course.course_code=enrollment.course_code)";
                resultSet = EnrollmentService.queryExecute(connection, query);
                List<Enrollment> enrollmentList = EnrollmentService.extractEnrollmentData(resultSet);
                EnrollmentViewService.printEnrollmentInformation(enrollmentList);
                break;

            case 4:
                System.out.println("Enter trainee id");
                String id = input.next();
                System.out.println("Enter course code");
                String code = input.next();

                query = "INSERT INTO enrollment VALUES (?,?)";
                EnrollmentService.updateExecute(connection, query, id, code);
                break;

            default:
                System.out.println("Wrong input! Please enter a valid input");
                break;
        }
    }
}