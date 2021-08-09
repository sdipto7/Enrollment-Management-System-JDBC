package net.therap.jdbc.controller;

import net.therap.jdbc.domain.*;
import net.therap.jdbc.service.JdbcService;
import net.therap.jdbc.service.JdbcViewService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

/**
 * @author rumi.dipto
 * @since 8/9/21
 */
public class JdbcController {

    public static void main(String[] args) {
        Database database = new Database("jdbc:mysql://localhost:3306/course_enrollment_details",
                "root", "welcome987");

        Connection connection = JdbcService.connectDatabase(database);

        Scanner input = new Scanner(System.in);

        System.out.println("Press 1 to view courses");
        System.out.println("Press 2 to view trainees");
        System.out.println("Press 3 to view course enrollment details");
        System.out.println("Press 4 to assign a course to trainee");

        int operation = input.nextInt();
        executeOperation(connection, operation);
    }

    public static void executeOperation(Connection connection, int operation){
        String query = "";
        ResultSet resultSet = null;
        switch (operation){
            case 1:
                query = "SELECT * FROM course_table";
                resultSet = JdbcService.queryExecute(connection, query);
                List<Course> courseList = JdbcService.courseDataExtract(resultSet);
                JdbcViewService.printCourseInformation(courseList);
                break;
            case 2:
                query = "SELECT * FROM trainee_table";
                resultSet = JdbcService.queryExecute(connection, query);
                List<Trainee> traineeList = JdbcService.traineeDataExtract(resultSet);
                JdbcViewService.printTraineeInformation(traineeList);
                break;
            case 3:
                query = "select trainee_table.Trainee_id, trainee_table.Trainee_Name, course_table.Course_Code, course_table.Course_Title FROM " +
                        "((enrollment_table inner join trainee_table on enrollment_table.Trainee_id=trainee_table.Trainee_id) " +
                        "inner join course_table on course_table.Course_Code=enrollment_table.Course_Code)";
                resultSet = JdbcService.queryExecute(connection, query);
                List<EnrollmentDetails> enrollmentDetailsList = JdbcService.enrollmentDataExtract(resultSet);
                EnrollmentDetailsOutput enrollmentDetailsOutput = JdbcService.generateOutput(enrollmentDetailsList);
                JdbcViewService.print(enrollmentDetailsOutput);
                break;
            case 4:
                break;
        }
    }
}