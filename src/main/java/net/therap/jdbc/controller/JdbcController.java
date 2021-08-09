package net.therap.jdbc.controller;

import net.therap.jdbc.domain.Database;
import net.therap.jdbc.domain.EnrollmentDetails;
import net.therap.jdbc.domain.EnrollmentDetailsOutput;
import net.therap.jdbc.service.JdbcService;
import net.therap.jdbc.service.JdbcViewService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

/**
 * @author rumi.dipto
 * @since 8/9/21
 */
public class JdbcController {

    public static void main(String[] args) {
        Database database = new Database("jdbc:mysql://localhost:3306/course_enrollment_details",
                "root", "welcome987");

        Connection connection = JdbcService.connectDatabase(database);

        String query = "select trainee_table.Trainee_id, trainee_table.Trainee_Name, course_table.Course_Code, course_table.Course_Title FROM " +
                "((enrollment_table inner join trainee_table on enrollment_table.Trainee_id=trainee_table.Trainee_id) " +
                "inner join course_table on course_table.Course_Code=enrollment_table.Course_Code)";

        ResultSet resultSet = JdbcService.queryExecute(connection, query);

        List<EnrollmentDetails> enrollmentDetailsList = JdbcService.enrollmentDataExtract(resultSet);

        EnrollmentDetailsOutput enrollmentDetailsOutput = JdbcService.generateOutput(enrollmentDetailsList);

        JdbcViewService.print(enrollmentDetailsOutput);
    }
}