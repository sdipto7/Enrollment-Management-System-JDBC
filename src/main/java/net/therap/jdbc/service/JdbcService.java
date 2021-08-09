package net.therap.jdbc.service;

import net.therap.jdbc.domain.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author rumi.dipto
 * @since 8/9/21
 */
public class JdbcService {

    public static Connection connectDatabase(Database database) {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(database.getDatabaseUrl(), database.getUsername(), database.getPassword());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static ResultSet queryExecute(Connection connection, String query) {
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static List<EnrollmentDetails> enrollmentDataExtract(ResultSet resultSet) {
        List<EnrollmentDetails> enrollmentDetailsList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                EnrollmentDetails e = new EnrollmentDetails();
                e.setCourseCode(resultSet.getString("course_table.Course_Code"));
                e.setCourseTitle(resultSet.getString("course_table.Course_Title"));
                e.setTraineeName(resultSet.getString("trainee_table.Trainee_Name"));
                e.setTraineeId(resultSet.getString("trainee_table.Trainee_id"));
                enrollmentDetailsList.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollmentDetailsList;
    }

    public static List<Trainee> traineeDataExtract(ResultSet resultSet) {
        List<Trainee> traineeList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Trainee t = new Trainee(resultSet.getString("Trainee_id"), resultSet.getString("Trainee_Name"));
                traineeList.add(t);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return traineeList;
    }

    public static List<Course> courseDataExtract(ResultSet resultSet) {
        List<Course> courseList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Course c = new Course(resultSet.getString("Course_Code"), resultSet.getString("Course_Title"));
                courseList.add(c);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return courseList;
    }

    public static EnrollmentDetailsOutput generateOutput(List<EnrollmentDetails> enrollmentDetailsList) {
        EnrollmentDetailsOutput output = new EnrollmentDetailsOutput();

        for (EnrollmentDetails e : enrollmentDetailsList) {
            String courseCode = e.getCourseCode();
            String courseTitle = e.getCourseTitle();
            String traineeName = e.getTraineeName();
            String traineeId = e.getTraineeId();

            output.getTraineeInfo().put(traineeId, traineeName);
            output.getCourseInfo().put(courseCode, courseTitle);
            if (output.getEnrollmentInfo().containsKey(traineeId)) {
                output.getEnrollmentInfo().get(traineeId).add(courseCode);
            } else {
                output.getEnrollmentInfo().put(traineeId, new HashSet<>());
                output.getEnrollmentInfo().get(traineeId).add(courseCode);
            }
        }
        return output;
    }
}